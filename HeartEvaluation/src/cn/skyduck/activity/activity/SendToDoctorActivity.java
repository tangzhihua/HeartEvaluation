package cn.skyduck.activity.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.global_data_cache.GlobalConstant.FunctionOptionsEnum;
import cn.skyduck.global_data_cache.GlobalConstant.PrintTypeEnum;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.NetConnectionManageTools;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

/**
 * 发送给医生界面
 * 
 * @author skyduck
 * 
 */
public class SendToDoctorActivity extends Activity {
	private final String TAG = this.getClass().getSimpleName();

	public static enum IntentExtraTagEnum {
		// 在未完成量表中的问卷索引
		QUESTIONAIRE_INDEX_IN_UNFINISHED_LIST
	};

	// 普通打印
	private Button nomalPrintButton;
	// 详细打印
	private Button detailedPrintButton;
	// 全面打印
	private Button fullPrintButton;
	// 预览
	private Button previewButton;
	// 返回
	private Button quitButton;
	// 当前登录用户状态
	private TextView userLoginStateTextView;
	// 系统管理员密码输入框
	private EditText passwordEditText;

	// 标识当前量表在 "未完成量表集合" 中的索引
	private int questionnaireIndexInUnfinishedList = -1;

	// 当前量表
	private FullQuestionnaireModel fullQuestionnaireModel;

	// 用于 延迟显示软键盘 的timer
	private boolean isActivityOnPause = false;
	private Timer timerForDelayShowKeyboard = new Timer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DebugLog.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_to_doctor_layout);

		// 获取目标量表
		List<FullQuestionnaireModel> unfinishedList = GlobalDataCacheForMemorySingleton.getInstance.getQuestionnaireListOfUnfinished();
		this.questionnaireIndexInUnfinishedList = getIntent().getIntExtra(IntentExtraTagEnum.QUESTIONAIRE_INDEX_IN_UNFINISHED_LIST.name(), -1);
		if (this.questionnaireIndexInUnfinishedList >= 0 && this.questionnaireIndexInUnfinishedList < unfinishedList.size()) {
			this.fullQuestionnaireModel = unfinishedList.get(this.questionnaireIndexInUnfinishedList);
		}

		// 监听设备联网状态, 要根据当前设备的联网状态来设置一些按钮是否可用
		registerBroadcastReceiver();

		// 再输入管理员密码之前, "普通打印", "详细打印", "全面打印", "预览" 功能不能使用
		// 普通打印
		nomalPrintButton = (Button) findViewById(R.id.nomal_print_button);
		nomalPrintButton.setOnClickListener(functionButtonOnClickListener);
		// 详细打印
		detailedPrintButton = (Button) findViewById(R.id.detailed_print_button);
		detailedPrintButton.setOnClickListener(functionButtonOnClickListener);
		// 全面打印
		fullPrintButton = (Button) findViewById(R.id.full_print_button);
		fullPrintButton.setOnClickListener(functionButtonOnClickListener);
		// 预览
		previewButton = (Button) findViewById(R.id.preview_button);
		previewButton.setOnClickListener(functionButtonOnClickListener);

		// 密码输入框
		passwordEditText = (EditText) findViewById(R.id.password_EditText);
		if (GlobalDataCacheForMemorySingleton.getInstance.isUserLogged()) {// 有用户登录

			// 延迟两秒后自动激活软键盘
			timerForDelayShowKeyboard.schedule(new TimerTask() {

				public void run() {
					if (!isActivityOnPause) {
						InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						inputMethodManager.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
					}
				}
			}, 2000);

			passwordEditText.requestFocus();
			passwordEditText.setOnKeyListener(new View.OnKeyListener() {

				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					if (event.getAction() == KeyEvent.ACTION_DOWN) {
						if (keyCode == KeyEvent.KEYCODE_ENTER) {
							if (isAdminPasswordInputRight()) {
								updateFunctionButtonEnableState();
							} else {
								// 提示用户, 输入的管理员密码不正确
								Toast.makeText(SendToDoctorActivity.this, getResources().getString(R.string.wrong_password), Toast.LENGTH_SHORT).show();
							}

							return true;
						}
					}
					return false;
				}
			});
		} else {// 如果使用 "guest" 账户 方式进入此界面, 就设置所有的功能按钮为不可用, 并且隐藏 管理员密码输入框
			passwordEditText.setVisibility(View.INVISIBLE);
		}

		// 用户登录状态
		userLoginStateTextView = (TextView) findViewById(R.id.user_login_state_textView);
		userLoginStateTextView.setText(ToolsFunctionForThisProgect.getUserLoginState());

		// 退出 按钮
		quitButton = (Button) findViewById(R.id.quit_button);
		quitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 返回 主界面
				gotoMainActivity();
			}
		});

		updateFunctionButtonEnableState();
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
		// 刷新网络信息
		ToolsFunctionForThisProgect.refreshNetInfoView(this, R.id.net_info_with_wifi_layout);
	}

	@Override
	protected void onPause() {
		DebugLog.i(TAG, "onPause");
		super.onPause();

		// 取消 延迟显示软键盘
		isActivityOnPause = true;
		timerForDelayShowKeyboard.cancel();
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

	private View.OnClickListener functionButtonOnClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.nomal_print_button:
				gotoPrintActivity(GlobalConstant.FunctionOptionsEnum.normal_print);
				break;
			case R.id.detailed_print_button:
				gotoPrintActivity(GlobalConstant.FunctionOptionsEnum.detail_print);
				break;
			case R.id.full_print_button:
				gotoPrintActivity(GlobalConstant.FunctionOptionsEnum.full_print);
				break;
			case R.id.preview_button:
				gotoPreviewActivity();
				break;
			default:
				break;
			}

		}
	};

	/**
	 * 判断系统管理员密码是否输入正确
	 * 
	 * @return
	 */
	private boolean isAdminPasswordInputRight() {
		String passwordOfUserInput = passwordEditText.getText().toString();
		final String adminPassword = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getPasswordSetting().getAdminPassword();
		if (!adminPassword.equals(passwordOfUserInput)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 更新功能按钮的可用状态
	 */
	private void updateFunctionButtonEnableState() {
		do {

			if (this.fullQuestionnaireModel == null) {
				assert false : "在UnfinishedList中, 取出的目标量表为空.";
				break;
			}

			if (!isAdminPasswordInputRight()) {
				// 用户必须先输入正确的管理员密码, 功能按钮才可以使用
				break;
			}

			NetConnectionManageTools netConnectionManageTools = new NetConnectionManageTools();
			if (!netConnectionManageTools.isNetAvailable()) {
				// 当前没有连接网络, 要设置所有功能按钮不可用, 并且提示用户
				Toast.makeText(SendToDoctorActivity.this, "目前没有连接网络!", Toast.LENGTH_LONG).show();
				break;
			}

			// 普通打印
			if (fullQuestionnaireModel.getQuestionnaireCodeEnum().isSupportThisPrintType(PrintTypeEnum.NORMAL_PRINT)) {
				nomalPrintButton.setEnabled(true);
			} else {
				nomalPrintButton.setEnabled(false);
			}

			// 详细打印
			if (fullQuestionnaireModel.getQuestionnaireCodeEnum().isSupportThisPrintType(PrintTypeEnum.DETAIL_PRINT)) {
				detailedPrintButton.setEnabled(true);
			} else {
				detailedPrintButton.setEnabled(false);
			}

			// 全面打印
			if (fullQuestionnaireModel.getQuestionnaireCodeEnum().isSupportThisPrintType(PrintTypeEnum.FULL_PRINT)) {
				fullPrintButton.setEnabled(true);
			} else {
				fullPrintButton.setEnabled(false);
			}

			// 预览
			previewButton.setEnabled(true);
			return;
		} while (false);

		// 异常, 或者网络不同时, 就设置所有的功能按钮都不可用
		nomalPrintButton.setEnabled(false);
		detailedPrintButton.setEnabled(false);
		fullPrintButton.setEnabled(false);
		previewButton.setEnabled(false);
		return;
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

			// 这个监听wifi的连接状态即是否连上了一个有效无线路由，当上边广播的状态是WifiManager.WIFI_STATE_DISABLING，和WIFI_STATE_DISABLED的时候，根本不会接到这个广播。
			// 在上边广播接到广播是WifiManager.WIFI_STATE_ENABLED状态的同时也会接到这个广播，当然刚打开wifi肯定还没有连接到有效的无线
			if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
				Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
				if (null != parcelableExtra) {
					NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
					switch (networkInfo.getState()) {
					case CONNECTED:// 连接
						updateFunctionButtonEnableState();
						break;
					case DISCONNECTED:// 断开
						updateFunctionButtonEnableState();
						break;
					default:
						break;
					}
				}
			}

		}
	}

	private void registerBroadcastReceiver() {
		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		registerReceiver(broadcastReceiver, intentFilter);
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 去往 打印界面
	 * 
	 * @param optionEnum
	 * @param questionnaireIndexListOfUnfinishedList
	 */
	private void gotoPrintActivity(final FunctionOptionsEnum optionEnum) {
		Intent intent = new Intent(this, PrintReportActivity.class);
		final ArrayList<Integer> questionnaireIndexListOfUnfinishedList = new ArrayList<Integer>();
		questionnaireIndexListOfUnfinishedList.add(questionnaireIndexInUnfinishedList);
		intent.putIntegerArrayListExtra(PrintReportActivity.IntentExtraTagEnum.QUESTIONAIRE_INDEX_LIST_IN_UNFINISHED_LIST.name(), questionnaireIndexListOfUnfinishedList);
		intent.putExtra(PrintReportActivity.IntentExtraTagEnum.OPERATION_COMMAND.name(), optionEnum);
		startActivity(intent);
		finish();
	}

	/**
	 * 去往 预览界面
	 * 
	 * @param optionsEnum
	 * @param indexOfQuestionnaireListOfUnfinished
	 */
	private void gotoPreviewActivity() {
		Intent intent = new Intent(this, PreviewReportActivity.class);
		intent.putExtra(PreviewReportActivity.IntentExtraTagEnum.QUESTIONAIRE_INDEX_IN_UNFINISHED_LIST.name(), questionnaireIndexInUnfinishedList);
		startActivity(intent);
		finish();
	}

	private void gotoMainActivity() {
		Intent intent = new Intent(SendToDoctorActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		DebugLog.i(TAG, "onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			gotoMainActivity();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}
