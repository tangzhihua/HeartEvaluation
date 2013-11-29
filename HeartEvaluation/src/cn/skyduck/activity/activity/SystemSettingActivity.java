package cn.skyduck.activity.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import cn.skyduck.activity.R;
import cn.skyduck.activity.fragment.setting.AdminSettingFragment;
import cn.skyduck.activity.fragment.setting.DeviceInfoSettingFragment;
import cn.skyduck.activity.fragment.setting.PasswordSettingFragment;
import cn.skyduck.activity.fragment.setting.ServerIPSettingFragment;
import cn.skyduck.toolutils.DebugLog;

/**
 * 系统设置 界面
 * 
 * @author hesiming
 * 
 */
public class SystemSettingActivity extends FragmentActivity {
	private final String TAG = this.getClass().getSimpleName();

	private final ServerIPSettingFragment serverIPSettingFragment = new ServerIPSettingFragment();
	private final DeviceInfoSettingFragment deviceInfoSettingFragment = new DeviceInfoSettingFragment();
	private final PasswordSettingFragment passwordSettingFragment = new PasswordSettingFragment();
	private final AdminSettingFragment adminSettingFragment = new AdminSettingFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DebugLog.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system_setting_layout);

		final RadioGroup functionRadioGroup = (RadioGroup) findViewById(R.id.function_button_radioGroup);
		functionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				fragmentSwitch(checkedId);
			}
		});

		// 第一个显示的 功能碎片
		final RadioButton firstRadioButton = (RadioButton) findViewById(R.id.server_ip_radioButton);
		firstRadioButton.setChecked(true);

		final Button backButton = (Button) findViewById(R.id.back_button);
		backButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
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

	/**
	 * 切换 "碎片"
	 * 
	 * @param checkedIdOfRadioGroup
	 */
	private void fragmentSwitch(int checkedIdOfRadioGroup) {
		Fragment newFragment = null;
		switch (checkedIdOfRadioGroup) {
		case R.id.server_ip_radioButton:// 服务器IP
			newFragment = serverIPSettingFragment;
			break;

		case R.id.device_info_radioButton:// 终端信息
			newFragment = deviceInfoSettingFragment;
			break;

		case R.id.password_setting_radioButton:// 密码设置
			newFragment = passwordSettingFragment;
			break;

		case R.id.admin_setting_radioButton:// 管理员设置
			newFragment = adminSettingFragment;
			break;

		default:
			assert false : "default : 分支";
			return;
		}

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.fragment_container_layout, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
}
