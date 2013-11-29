package cn.skyduck.activity.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.MyApplication;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.global_data_cache.GlobalDataCacheForNeedSaveToFileSystem;
import cn.skyduck.model.setting.sub_section.ServerIPForSystemSetting;
import cn.skyduck.net_service.IDomainNetRespondCallback;
import cn.skyduck.net_service.INetServiceOptions;
import cn.skyduck.net_service.domain_protocol.account_login.LoginNetService;
import cn.skyduck.net_service.domain_protocol.account_login.LogonNetRequestBean;
import cn.skyduck.net_service.domain_protocol.account_login.LogonNetRespondBean;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.NetConnectionManageTools;
import cn.skyduck.toolutils.SimpleProgressDialog;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

@SuppressLint("HandlerLeak")
/**
 * 用户登录界面
 * 
 * @author hesiming
 *
 */
public class LoginActivity extends Activity {
	private final String TAG = this.getClass().getSimpleName();

	// 登录按钮
	private Button loginButton;
	private EditText userNameEditText;
	private EditText passwordEditText;
	// 是否记住密码
	private CheckBox rememberMeCheckBox;
	// wifi 状态
	private TextView wifiStaTextView;

	// 用于 延迟显示软键盘 的timer
	private boolean isActivityOnPause = false;
	private Timer timerForDelayShowKeyboard = new Timer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DebugLog.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_layout);

		// 注册广播消息(监听网络是否可用的广播)
		registerBroadcastReceiver();

		// 读取本地缓存用户登录信息(最后一次登录成功时的 用户名+密码)
		GlobalDataCacheForNeedSaveToFileSystem.readUserLoginInfo();
		final String usernameForLastSuccessfulLogon = GlobalDataCacheForMemorySingleton.getInstance.getUsernameForLastSuccessfulLogon();
		final String passwordForLastSuccessfulLogon = GlobalDataCacheForMemorySingleton.getInstance.getPasswordForLastSuccessfulLogon();

		// wifi 状态
		wifiStaTextView = (TextView) findViewById(R.id.wifi_state_textView);
		WifiManager wifimanager = (WifiManager) getSystemService(Service.WIFI_SERVICE);
		String wifiStateString = ToolsFunctionForThisProgect.getDescriptionForWifiState(wifimanager.getWifiState());
		wifiStaTextView.setText("WIFI状态:" + wifiStateString);

		// 用户名输入框
		userNameEditText = (EditText) findViewById(R.id.user_name_EditText);
		if (!TextUtils.isEmpty(usernameForLastSuccessfulLogon)) {
			userNameEditText.setText(usernameForLastSuccessfulLogon);
		}
		userNameEditText.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						return true;
					}
				}
				return false;
			}
		});

		// 密码输入框
		passwordEditText = (EditText) findViewById(R.id.password_EditText);
		if (GlobalDataCacheForMemorySingleton.getInstance.isNeedSavePassword()) {
			if (!TextUtils.isEmpty(passwordForLastSuccessfulLogon)) {
				passwordEditText.setText(passwordForLastSuccessfulLogon);
			}
		}

		// 登录 按钮
		loginButton = (Button) findViewById(R.id.login_Button);
		NetConnectionManageTools netConnectionManageTools = new NetConnectionManageTools();
		if (!netConnectionManageTools.isNetAvailable()) {
			// 现在没有可用的网络
			loginButton.setEnabled(false);
			loginButton.setText(getResources().getString(R.string.login_no_net));
			Toast.makeText(this, getResources().getString(R.string.no_network_connection), Toast.LENGTH_LONG).show();
		}
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String errorMessageString = "";
				String username = "";
				String password = "";

				do {
					username = userNameEditText.getText().toString();
					if (TextUtils.isEmpty(username)) {
						errorMessageString = getResources().getString(R.string.ueername_can_not_be_blank);
						break;
					}

					password = passwordEditText.getText().toString();
					if (TextUtils.isEmpty(password)) {
						errorMessageString = getResources().getString(R.string.password_can_not_be_blank);
						break;
					}

					//
					timerForDelayShowKeyboard.cancel();
					requestUserLogonWithUsernameAndPassword(username, password);

					// 一切OK
					return;
				} while (false);

				// 用户输入的信息错误
				Toast.makeText(LoginActivity.this, errorMessageString, Toast.LENGTH_LONG).show();

			}
		});

		// 不登录进入 按钮
		final Button unlogingButton = (Button) findViewById(R.id.unlogin_Button);
		unlogingButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 进入主界面
				gotoMainActivity();
			}
		});

		// 退出 按钮
		final Button quitButton = (Button) findViewById(R.id.quit_Button);
		quitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				saveUsernameAndPassword();
				showQuitAppDialog();
			}
		});
		rememberMeCheckBox = (CheckBox) findViewById(R.id.remember_me_CheckBox);
		rememberMeCheckBox.setChecked(GlobalDataCacheForMemorySingleton.getInstance.isNeedSavePassword());

		// 延迟两秒后自动激活软键盘
		timerForDelayShowKeyboard.schedule(new TimerTask() {

			public void run() {
				if (!isActivityOnPause) {
					InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					inputMethodManager.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
				}
			}
		}, 2000);

	}

	@Override
	protected void onStart() {
		DebugLog.i(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		DebugLog.i(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		DebugLog.i(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		DebugLog.i(TAG, "onPause");
		super.onPause();

		SimpleProgressDialog.resetByThisContext(this);

		// 取消网络请求
		loginNetService.stop();

		// 取消 延迟显示软键盘
		isActivityOnPause = false;
		timerForDelayShowKeyboard.cancel();

		// 保存用户名和密码
		saveUsernameAndPassword();
	}

	@Override
	protected void onStop() {
		DebugLog.i(TAG, "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		DebugLog.i(TAG, "onDestroy");

		// 一定要注销 "广播"
		unregisterReceiver(broadcastReceiver);

		super.onDestroy();
	}

	private void saveUsernameAndPassword() {
		// 保存 "是否需要保存密码 这个mark"
		GlobalDataCacheForMemorySingleton.getInstance.setNeedSavePassword(rememberMeCheckBox.isChecked());

		// TODO : 临时处理, 目前没有开发真正的登录
		GlobalDataCacheForMemorySingleton.getInstance.setUsernameForLastSuccessfulLogon(userNameEditText.getText().toString());
		GlobalDataCacheForMemorySingleton.getInstance.setPasswordForLastSuccessfulLogon(passwordEditText.getText().toString());
	}

	private static enum HandlerMsgTypeEnum {
		//
		SHOW_NET_ERROR_MESSAGE,
		//
		USER_LOGIN_SUCCESSFULLY
	};

	private static enum HandlerExtraDataTypeEnum {
		//
		NET_ERROR_MESSAGE
	};

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			//
			SimpleProgressDialog.dismiss(LoginActivity.this);

			if (msg.what == HandlerMsgTypeEnum.SHOW_NET_ERROR_MESSAGE.ordinal()) {
				final String netErrorMessage = msg.getData().getString(HandlerExtraDataTypeEnum.NET_ERROR_MESSAGE.name());
				Toast.makeText(LoginActivity.this, netErrorMessage, Toast.LENGTH_SHORT).show();
			} else if (msg.what == HandlerMsgTypeEnum.USER_LOGIN_SUCCESSFULLY.ordinal()) {
				gotoMainActivity();
			}
		}
	};

	private String usernameTempBuf = "";
	private String passwordTempBuf = "";

	// 登录网络接口
	private INetServiceOptions loginNetService = new LoginNetService();
	private IDomainNetRespondCallback netRespondCallback = new IDomainNetRespondCallback() {

		@Override
		public void domainNetRespondHandleInNonUIThread(Enum<?> event, Object respondData) {
			loginNetService.stop();

			if (event == LoginNetService.NetEventEnum.NET_REQUEST_SUCCESS) {
				// 保存用户成功登录后的信息
				ToolsFunctionForThisProgect.noteLogonSuccessfulInfo((LogonNetRespondBean) respondData, usernameTempBuf, passwordTempBuf);

				final Message msg = new Message();
				msg.what = HandlerMsgTypeEnum.USER_LOGIN_SUCCESSFULLY.ordinal();
				handler.sendMessage(msg);
			} else {
				final Message msg = new Message();
				msg.what = HandlerMsgTypeEnum.SHOW_NET_ERROR_MESSAGE.ordinal();
				msg.getData().putString(HandlerExtraDataTypeEnum.NET_ERROR_MESSAGE.name(), loginNetService.getNetErrorMessage());
				handler.sendMessage(msg);
			}

		}
	};

	private void requestUserLogonWithUsernameAndPassword(final String username, final String password) {
		if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
			return;
		}

		/******* 测试代码 ********/
//		 LogonNetRespondBean logonRespond = new LogonNetRespondBean("登录成功",
//		 MD5Util.getMD5String(username), username, "sessionId");
//		 DebugLog.i(TAG, logonRespond.toString());
//		 //保存用户成功登录后的信息
//		 ToolsFunctionForThisProgect.noteLogonSuccessfulInfo(logonRespond,
//		 userNameEditText.getText().toString(),
//		 passwordEditText.getText().toString());
//		 //读取本地缓存的未完成的量表集合 - 为当前用户
//		 gotoMainActivity();
//		 return;
		/******* 测试代码 ********/

		// 发起业务接口 "2.2用户登录" 的访问
		final LogonNetRequestBean logonNetRequestBean = new LogonNetRequestBean(username, password);
		loginNetService.setNetRequestBean(logonNetRequestBean);

		if (loginNetService.start(netRespondCallback)) {
			usernameTempBuf = username;
			passwordTempBuf = password;
			SimpleProgressDialog.show(this, progressDialogOnCancelListener);
		}

	}

	/**
	 * ProgressDialog 界面显示时, 按下 back 按键的监听回调方法
	 */
	private DialogInterface.OnCancelListener progressDialogOnCancelListener = new DialogInterface.OnCancelListener() {

		@Override
		public void onCancel(DialogInterface dialog) {
			loginNetService.stop();
		}
	};

	/**
	 * 跳转到 主界面
	 */
	private void gotoMainActivity() {
		// 读取本地缓存的未完成的量表集合 - 为当前用户
		GlobalDataCacheForNeedSaveToFileSystem.readQuestionnaireListOfUnfinished();

		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		DebugLog.i(TAG, "onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ToolsFunctionForThisProgect.quitApp(this);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private BroadcastReceiverForLoginActivity broadcastReceiver = new BroadcastReceiverForLoginActivity();

	// 接收用户成功登录的广播消息
	private class BroadcastReceiverForLoginActivity extends BroadcastReceiver {

		public BroadcastReceiverForLoginActivity() {
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			DebugLog.i(TAG, "BroadcastReceiverForMainNavigationActivity:onReceive");

			if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {// 这个监听wifi的打开与关闭，与wifi的连接无关
				int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
				String wifiStateString = ToolsFunctionForThisProgect.getDescriptionForWifiState(wifiState);
				if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
					wifiStateString = "连接中...";
				}
				wifiStaTextView.setText("WIFI状态:" + wifiStateString);
				return;
			}

			// 这个监听wifi的连接状态即是否连上了一个有效无线路由，当上边广播的状态是WifiManager.WIFI_STATE_DISABLING，和WIFI_STATE_DISABLED的时候，根本不会接到这个广播。
			// 在上边广播接到广播是WifiManager.WIFI_STATE_ENABLED状态的同时也会接到这个广播，当然刚打开wifi肯定还没有连接到有效的无线
			if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
				Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
				if (null != parcelableExtra) {
					NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
					switch (networkInfo.getState()) {
					case CONNECTED:// 连接
						loginButton.setEnabled(true);
						loginButton.setText(getResources().getString(R.string.login));
						WifiManager wifiManager = (WifiManager) MyApplication.getApplication().getSystemService(Context.WIFI_SERVICE);
						wifiStaTextView.setText("WIFI状态:已经连接到" + wifiManager.getConnectionInfo().getSSID());
						break;
					case DISCONNECTED:// 断开
						loginButton.setEnabled(false);
						loginButton.setText(getResources().getString(R.string.login_no_net));
						wifiStaTextView.setText("WIFI状态:网络已经断开.");
						Toast.makeText(LoginActivity.this, getResources().getString(R.string.no_network_connection), Toast.LENGTH_LONG).show();
						break;
					default:
						break;
					}
				}

				return;
			}
		}
	}

	private void registerBroadcastReceiver() {
		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		registerReceiver(broadcastReceiver, intentFilter);
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 显示 退出应用确认提示框
	 */
	private void showQuitAppDialog() {
		final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_quit_app_layout, null);
		final Dialog dialog = new Dialog(this, R.style.AlertDialog);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(dialogView);

		// 结束测试 按钮
		final Button okButton = (Button) dialogView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ToolsFunctionForThisProgect.quitApp(LoginActivity.this);
			}
		});

		// 取消 按钮
		final Button cancelButton = (Button) dialogView.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private static enum OptionsMenuEnum {
		SETTING_SERVER_IP, SYSTEM_INFO, COPYRIGHT
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, OptionsMenuEnum.SETTING_SERVER_IP.ordinal(), OptionsMenuEnum.SETTING_SERVER_IP.ordinal(), "设置服务器IP");
		menu.add(0, OptionsMenuEnum.SYSTEM_INFO.ordinal(), OptionsMenuEnum.SYSTEM_INFO.ordinal(), "系统介绍");
		menu.add(0, OptionsMenuEnum.COPYRIGHT.ordinal(), OptionsMenuEnum.COPYRIGHT.ordinal(), "版权所有");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getOrder() == OptionsMenuEnum.SETTING_SERVER_IP.ordinal()) {
			showServerIPSettingDialog();
		} else if (item.getOrder() == OptionsMenuEnum.SYSTEM_INFO.ordinal()) {

		} else if (item.getOrder() == OptionsMenuEnum.COPYRIGHT.ordinal()) {

		}
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 设置服务器IP 对话框
	 * 
	 * @param serverNameTextView
	 * @param serverIPTextView
	 * @param position
	 */
	private void showServerIPSettingDialog() {
		// 创建window
		final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_setting_server_ip_layout, null);
		final Dialog dialog = new Dialog(this, R.style.AlertDialog);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(dialogView);

		final ServerIPForSystemSetting serverIPOfDefault = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getServerIPByIndex(0);

		// 映射提示框布局中的控件
		final EditText serverNameEditText = (EditText) dialogView.findViewById(R.id.server_name_editText);
		serverNameEditText.setText(serverIPOfDefault.getServerName());
		final EditText serverIPEeitText = (EditText) dialogView.findViewById(R.id.server_ip_editText);
		serverIPEeitText.setText(serverIPOfDefault.getServerIP());
		final ImageView serverNameErrorIcon = (ImageView) dialogView.findViewById(R.id.server_name_error_imageView);
		final ImageView serverIPErrorIcon = (ImageView) dialogView.findViewById(R.id.server_ip_error_imageView);

		// 取消 按钮
		final Button cancelDialogButton = (Button) dialogView.findViewById(R.id.cancel_button);
		cancelDialogButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		// 保存 按钮
		final Button saveServerIPButton = (Button) dialogView.findViewById(R.id.save_button);
		saveServerIPButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final String serverName = serverNameEditText.getText().toString();
				final String serverIP = serverIPEeitText.getText().toString();
				String errorMessageString = "";

				serverNameErrorIcon.setVisibility(View.INVISIBLE);
				serverIPErrorIcon.setVisibility(View.INVISIBLE);
				do {

					if (TextUtils.isEmpty(serverName)) {
						errorMessageString = getResources().getString(R.string.name_can_not_be_blank);
						serverNameErrorIcon.setVisibility(View.VISIBLE);
						break;
					}

					if (TextUtils.isEmpty(serverIP)) {
						errorMessageString = getResources().getString(R.string.IP_can_not_be_blank);
						serverIPErrorIcon.setVisibility(View.VISIBLE);
						break;
					}

					serverIPOfDefault.setServerName(serverName);
					serverIPOfDefault.setServerIP(serverIP);

					dialog.dismiss();
					return;
				} while (false);

				// 用户输入错误
				Toast.makeText(MyApplication.getApplication(), errorMessageString, Toast.LENGTH_SHORT).show();
				return;
			}
		});
	}
}
