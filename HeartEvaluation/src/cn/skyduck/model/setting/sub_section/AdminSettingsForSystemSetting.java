package cn.skyduck.model.setting.sub_section;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AdminSettingsForSystemSetting implements Serializable {
	private String deviceId = "未设置";

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "AdminSettingsForSystemSetting [deviceId=" + deviceId + "]";
	}
}
