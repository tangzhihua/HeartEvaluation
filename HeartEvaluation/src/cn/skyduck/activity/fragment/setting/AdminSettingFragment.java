package cn.skyduck.activity.fragment.setting;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import cn.skyduck.activity.MyApplication;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.model.setting.sub_section.AdminSettingsForSystemSetting;
import cn.skyduck.toolutils.DebugLog;

/**
 * 管理员设置界面
 * 
 * @author hesiming
 * 
 */
public class AdminSettingFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	private EditText deviceIdEditText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");
		View rootView = inflater.inflate(R.layout.fragment_admin_setting_layout, container, false);

		AdminSettingsForSystemSetting adminSettings = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getAdminSettings();

		this.deviceIdEditText = (EditText) rootView.findViewById(R.id.device_id_editText);
		this.deviceIdEditText.setEnabled(false);
		this.deviceIdEditText.setText(adminSettings.getDeviceId());

		showInputAdminPasswordDialog();

		return rootView;
	}

	@Override
	public void onPause() {
		super.onPause();
		DebugLog.i(TAG, "Fragment-->onPause");

		AdminSettingsForSystemSetting adminSettings = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getAdminSettings();
		adminSettings.setDeviceId(deviceIdEditText.getText().toString());
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

	private void showInputAdminPasswordDialog() {
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

					if (!GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getPasswordSetting().getAdminPassword().equals(adminPasswordOfUserInput)) {
						errorMessageString = getResources().getString(R.string.wrong_password);
						adminPasswordErrorImageView.setVisibility(View.VISIBLE);
						break;
					}

					// 密码输正确
					deviceIdEditText.setEnabled(true);

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
}
