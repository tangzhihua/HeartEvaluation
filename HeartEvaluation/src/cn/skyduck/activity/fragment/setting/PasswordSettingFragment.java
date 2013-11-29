package cn.skyduck.activity.fragment.setting;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import cn.skyduck.activity.MyApplication;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.model.setting.sub_section.PasswordForSystemSetting;
import cn.skyduck.toolutils.DebugLog;

/**
 * 密码设置界面
 * 
 * @author hesiming
 * 
 */
public class PasswordSettingFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	private CheckBox needAdminPasswordToEnterContinueActivityCheckBox;
	private CheckBox needAdminPasswordToEnterSettingActivityCheckBox;
	private Button settingAdminPasswordButton;
	private String adminPasswordString;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");
		View rootView = inflater.inflate(R.layout.fragment_password_setting_layout, container, false);

		PasswordForSystemSetting passwordForSystemSetting = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getPasswordSetting();
		adminPasswordString = passwordForSystemSetting.getAdminPassword();

		// 设置系统管理员密码 按钮
		settingAdminPasswordButton = (Button) rootView.findViewById(R.id.setting_admin_password_button);
		if (!TextUtils.isEmpty(adminPasswordString)) {
			settingAdminPasswordButton.setText(getResources().getString(R.string.modification));
		} else {
			settingAdminPasswordButton.setText(getResources().getString(R.string.setting));
		}
		settingAdminPasswordButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!TextUtils.isEmpty(adminPasswordString)) {
					// 密码不为空, 弹出 "密码修改 提示框"
					showChangeAdminPasswordDialog();
				} else {
					// 密码为空, 弹出 "密码设置 提示框"
					showSettingAdminPasswordDialog();

				}

			}
		});

		// 进入 继续未完成 需要管理员密码
		needAdminPasswordToEnterContinueActivityCheckBox = (CheckBox) rootView.findViewById(R.id.need_admin_password_to_enter_continue_activity_checkBox);
		needAdminPasswordToEnterContinueActivityCheckBox.setChecked(passwordForSystemSetting.isNeedAdminPasswordToEnterContinueTestActivity());
		// 进入 系统设置 需要管理员密码
		needAdminPasswordToEnterSettingActivityCheckBox = (CheckBox) rootView.findViewById(R.id.need_admin_password_to_enter_setting_activity_checkBox);
		needAdminPasswordToEnterSettingActivityCheckBox.setChecked(passwordForSystemSetting.isNeedAdminPasswordToEnterSystemSettingActivity());
		return rootView;
	}

	@Override
	public void onPause() {
		super.onPause();
		DebugLog.i(TAG, "Fragment-->onPause");

		PasswordForSystemSetting passwordForSystemSetting = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getPasswordSetting();
		passwordForSystemSetting.setAdminPassword(adminPasswordString);
		passwordForSystemSetting.setNeedAdminPasswordToEnterContinueTestActivity(needAdminPasswordToEnterContinueActivityCheckBox.isChecked());
		passwordForSystemSetting.setNeedAdminPasswordToEnterSystemSettingActivity(needAdminPasswordToEnterSettingActivityCheckBox.isChecked());
	}

	@Override
	public void onStop() {
		super.onStop();

		DebugLog.i(TAG, "Fragment-->onStop");
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		DebugLog.i(TAG, "Fragment-->onAttach");
	}

	@Override
	public void onStart() {
		super.onStart();
		DebugLog.i(TAG, "Fragment-->onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		DebugLog.i(TAG, "Fragment-->onResume");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		DebugLog.i(TAG, "Fragment-->onDestroy");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onActivityCreated");
	}

	/**
	 * 显示 设置管理员密码 对话框(用于当前系统管理员密码为空的时候)
	 */
	private void showSettingAdminPasswordDialog() {
		// 创建window
		final View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_input_admin_password_layout, null);
		final Dialog dialog = new Dialog(getActivity(), R.style.AlertDialog);
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

					if (TextUtils.isEmpty(adminPasswordOfUserInput)) {
						errorMessageString = getResources().getString(R.string.password_can_not_be_blank);
						adminPasswordErrorImageView.setVisibility(View.VISIBLE);
						break;
					}

					// 密码设置成功
					PasswordSettingFragment.this.adminPasswordString = adminPasswordOfUserInput;
					PasswordSettingFragment.this.settingAdminPasswordButton.setText(getResources().getString(R.string.modification));

					dialog.dismiss();

					Toast.makeText(MyApplication.getApplication(), getResources().getString(R.string.password_set_success), Toast.LENGTH_SHORT).show();
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
	 * 显示 修改管理员密码 对话框(用于当前系统管理员密码不为空的时候)
	 */
	private void showChangeAdminPasswordDialog() {
		// 创建window
		final View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_setting_admin_password_layout, null);
		final Dialog dialog = new Dialog(getActivity(), R.style.AlertDialog);
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(dialogView);

		final EditText originalPasswordEditText = (EditText) dialogView.findViewById(R.id.original_password_editText);
		final ImageView originalPasswordErrorImageView = (ImageView) dialogView.findViewById(R.id.original_password_error_imageView);
		final EditText inputNewPasswordEditText = (EditText) dialogView.findViewById(R.id.input_new_password_editText);
		final ImageView inputNewPasswordImageView = (ImageView) dialogView.findViewById(R.id.input_new_password_imageView);
		final EditText inputNewPasswordAgainEditText = (EditText) dialogView.findViewById(R.id.input_new_password_again_editText);
		final ImageView inputNewPasswordAgainErrorImageView = (ImageView) dialogView.findViewById(R.id.input_new_password_again_error_imageView);

		final Button okButton = (Button) dialogView.findViewById(R.id.save_button);
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 原始密码
				final String originalPasswordOfUserInput = originalPasswordEditText.getText().toString();
				// 输入的新密码
				final String inputNewPasswordOfUserInput = inputNewPasswordEditText.getText().toString();
				// 再次输入的新密码
				final String inputNewPasswordAgainOfUserInput = inputNewPasswordAgainEditText.getText().toString();
				String errorMessageString = "";

				originalPasswordErrorImageView.setVisibility(View.INVISIBLE);
				inputNewPasswordImageView.setVisibility(View.INVISIBLE);
				inputNewPasswordAgainErrorImageView.setVisibility(View.INVISIBLE);

				do {

					if (!PasswordSettingFragment.this.adminPasswordString.equals(originalPasswordOfUserInput)) {
						errorMessageString = getResources().getString(R.string.original_password_error);
						originalPasswordErrorImageView.setVisibility(View.VISIBLE);
						break;
					}

					if (TextUtils.isEmpty(inputNewPasswordOfUserInput)) {
						errorMessageString = getResources().getString(R.string.new_password_can_not_empty);
						inputNewPasswordImageView.setVisibility(View.VISIBLE);
						break;
					}

					if (!inputNewPasswordOfUserInput.equals(inputNewPasswordAgainOfUserInput)) {
						errorMessageString = getResources().getString(R.string.two_passwords_are_different);
						inputNewPasswordAgainErrorImageView.setVisibility(View.VISIBLE);
						break;
					}

					// 密码设置成功
					PasswordSettingFragment.this.adminPasswordString = inputNewPasswordOfUserInput;

					dialog.dismiss();

					// 用户输入错误
					Toast.makeText(MyApplication.getApplication(), getResources().getString(R.string.password_set_success), Toast.LENGTH_SHORT).show();
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
}
