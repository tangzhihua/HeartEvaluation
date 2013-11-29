package cn.skyduck.activity.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.skyduck.activity.MyApplication;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.model.setting.sub_section.PasswordForSystemSetting;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.ToolsFunctionForThisProgect;

/**
 * 主界面
 * 
 * @author hesiming
 * 
 */
public class MainActivity extends Activity {
	
	private final String TAG = this.getClass().getSimpleName();

	// 用户登录状态 标签
	private TextView userLoginStateTextView;

	// 用户输入 管理员密码之后要进行的动作
	private static enum NextActionForInputAdminPasswordEnum {
		// 跳转到 "系统设置界面"
		GOTO_SETTING_ACTIVITY,
		// 跳转到 "继续未完成界面"
		GOTO_CONTINUE_TEST_ACTIVITY
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DebugLog.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_layout);

		// 用户登录状态
		userLoginStateTextView = (TextView) findViewById(R.id.user_login_state_textView);
		userLoginStateTextView.setText(ToolsFunctionForThisProgect.getUserLoginState());

		// 开始测试
		final Button startTestButton = (Button) findViewById(R.id.start_test_button);
		startTestButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, QuestionnaireNavigationActivity.class);
				startActivity(intent);

			}
		});

		// 继续未完成测试
		final Button continueTestButton = (Button) findViewById(R.id.continue_test_button);
		continueTestButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				PasswordForSystemSetting passwordForSystemSetting = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getPasswordSetting();
				if (GlobalDataCacheForMemorySingleton.getInstance.isUserLogged() && !TextUtils.isEmpty(passwordForSystemSetting.getAdminPassword())
						&& passwordForSystemSetting.isNeedAdminPasswordToEnterContinueTestActivity()) {
					// 只有 "已登录用户" 才需要做进入权限检查, "未登录用户" 可以直接进入 "继续未完成" 界面
					showInputAdminPasswordDialogWithNextAction(NextActionForInputAdminPasswordEnum.GOTO_CONTINUE_TEST_ACTIVITY);
				} else {
					gotoContinueTestActivity();
				}

			}
		});

		// 系统设置
		final Button systemSettingButton = (Button) findViewById(R.id.system_setting_button);
		systemSettingButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				PasswordForSystemSetting passwordForSystemSetting = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getPasswordSetting();

				if (!TextUtils.isEmpty(passwordForSystemSetting.getAdminPassword()) && passwordForSystemSetting.isNeedAdminPasswordToEnterSystemSettingActivity()) {
					showInputAdminPasswordDialogWithNextAction(NextActionForInputAdminPasswordEnum.GOTO_SETTING_ACTIVITY);
				} else {
					gotoSystemSettingActivity();
				}

			}
		});

		// 退出
		final Button quitButton = (Button) findViewById(R.id.quit_Button);
		quitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showQuitAppDialog();
			}
		});

		// 下面是对 "登录进入/未登录进入" 的设置
		if (GlobalDataCacheForMemorySingleton.getInstance.isUserLogged()) {

			// 登录进入

		} else {

			// 未登录进入

			// 未登录用户不能使用 "系统设置按钮"，为禁用状态
			systemSettingButton.setEnabled(false);
			// 提示 : 使用不登录方式进入,无法上传及打印报告.若想上传或打印,请在 '继续未完成' 功能中操作.
			unloginUserTips();
		}
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
		ToolsFunctionForThisProgect.refreshNetInfoView(this, R.id.net_info_layout);
	}

	@Override
	protected void onPause() {
		DebugLog.i(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		DebugLog.i(TAG, "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		DebugLog.i(TAG, "onDestroy");

		super.onDestroy();
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

	/**
	 * "不登录进入" 时的提示信息
	 */
	private void unloginUserTips() {
		new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.tipe)).setMessage(getResources().getString(R.string.unlogin_enter_tipe_message))
				.setPositiveButton(getResources().getString(R.string.confirm), null).show();
	}

	/**
	 * 跳转到 "系统设置" 界面
	 */
	private void gotoSystemSettingActivity() {
		Intent intent = new Intent(MainActivity.this, SystemSettingActivity.class);
		startActivity(intent);
	}

	/**
	 * 跳转到 "继续未完成" 界面
	 */
	private void gotoContinueTestActivity() {

		//
		Intent intent = new Intent(MainActivity.this, ContinueTestActivity.class);
		startActivity(intent);
	}

	private void showInputAdminPasswordDialogWithNextAction(final NextActionForInputAdminPasswordEnum nextAction) {
		// 创建window
		final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_input_admin_password_layout, null);
		final Dialog dialog = new Dialog(this, R.style.AlertDialog);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(dialogView);

		final EditText adminPasswordEditText = (EditText) dialogView.findViewById(R.id.admin_password_editText);
		final ImageView adminPasswordErrorImageView = (ImageView) dialogView.findViewById(R.id.admin_password_error_imageView);
		final Button okButton = (Button) dialogView.findViewById(R.id.ok_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final String adminPasswordOfUserInput = adminPasswordEditText.getText().toString();
				String errorMessageString = "";

				adminPasswordErrorImageView.setVisibility(View.INVISIBLE);
				do {

					if (!GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getPasswordSetting().getAdminPassword().equals(adminPasswordOfUserInput)) {
						errorMessageString = getResources().getString(R.string.wrong_password);
						adminPasswordErrorImageView.setVisibility(View.VISIBLE);
						break;
					}

					// 密码输入成功
					switch (nextAction) {
					case GOTO_CONTINUE_TEST_ACTIVITY:
						gotoContinueTestActivity();
						break;
					case GOTO_SETTING_ACTIVITY:
						gotoSystemSettingActivity();
						break;
					default:
						break;
					}

					dialog.dismiss();

					return;
				} while (false);

				// 用户输入错误
				Toast.makeText(MyApplication.getApplication(), errorMessageString, Toast.LENGTH_SHORT).show();

			}
		});

		final Button cancelButton = (Button) dialogView.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

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
				ToolsFunctionForThisProgect.quitApp(MainActivity.this);
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
}
