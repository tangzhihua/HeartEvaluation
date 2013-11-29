package cn.skyduck.activity.fragment.setting;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import cn.skyduck.activity.R;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.model.setting.sub_section.DeviceInfoForSystemSetting;
import cn.skyduck.toolutils.DebugLog;

/**
 * 设备信息 设置界面
 * 
 * @author hesiming
 * 
 */
public class DeviceInfoSettingFragment extends Fragment {
	private final String TAG = this.getClass().getSimpleName();

	private CheckBox showWifiCheckBox;
	private CheckBox showServerIpCheckBox;
	private CheckBox showDeviceNumberCheckBox;
	private CheckBox showDeviceIpCheckBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugLog.i(TAG, "Fragment-->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DebugLog.i(TAG, "Fragment-->onCreateView");

		View rootView = inflater.inflate(R.layout.fragment_device_info_setting_layout, container, false);

		DeviceInfoForSystemSetting deviceInfoForSystemSetting = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getDeviceInfo();
		// 是否显示 WIF
		showWifiCheckBox = (CheckBox) rootView.findViewById(R.id.show_wifi_checkBox);
		showWifiCheckBox.setChecked(deviceInfoForSystemSetting.isDisplayWifiState());
		// 是否显示 服务器IP
		showServerIpCheckBox = (CheckBox) rootView.findViewById(R.id.show_server_ip_checkBox);
		showServerIpCheckBox.setChecked(deviceInfoForSystemSetting.isDisplayServerIP());
		// 是否显示 本机编号
		showDeviceNumberCheckBox = (CheckBox) rootView.findViewById(R.id.show_device_number_checkBox);
		showDeviceNumberCheckBox.setChecked(deviceInfoForSystemSetting.isDisplayDeviceNumber());
		// 是否显示 本机IP
		showDeviceIpCheckBox = (CheckBox) rootView.findViewById(R.id.show_device_ip_checkBox);
		showDeviceIpCheckBox.setChecked(deviceInfoForSystemSetting.isDisplayDeviceIP());
		//
		return rootView;
	}

	@Override
	public void onPause() {
		super.onPause();
		DebugLog.i(TAG, "Fragment-->onPause");

		DeviceInfoForSystemSetting deviceInfoForSystemSetting = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getDeviceInfo();

		// 是否显示 WIF
		deviceInfoForSystemSetting.setDisplayWifiState(showWifiCheckBox.isChecked());
		// 是否显示 服务器IP
		deviceInfoForSystemSetting.setDisplayServerIP(showServerIpCheckBox.isChecked());
		// 是否显示 本机编号
		deviceInfoForSystemSetting.setDisplayDeviceNumber(showDeviceNumberCheckBox.isChecked());
		// 是否显示 本机IP
		deviceInfoForSystemSetting.setDisplayDeviceIP(showDeviceIpCheckBox.isChecked());
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
}
