package cn.skyduck.model.setting.sub_section;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DeviceInfoForSystemSetting implements Serializable {
	// 是否显示WIFI状态
	private boolean isDisplayWifiState = true;
	// 是否显示服务器IP
	private boolean isDisplayServerIP = true;
	// 是否显示本机编号
	private boolean isDisplayDeviceNumber = true;
	// 是否显示本机IP
	private boolean isDisplayDeviceIP = true;

	public boolean isDisplayWifiState() {
		return isDisplayWifiState;
	}

	public void setDisplayWifiState(boolean isDisplayWifiState) {
		this.isDisplayWifiState = isDisplayWifiState;
	}

	public boolean isDisplayServerIP() {
		return isDisplayServerIP;
	}

	public void setDisplayServerIP(boolean isDisplayServerIP) {
		this.isDisplayServerIP = isDisplayServerIP;
	}

	public boolean isDisplayDeviceNumber() {
		return isDisplayDeviceNumber;
	}

	public void setDisplayDeviceNumber(boolean isDisplayDeviceNumber) {
		this.isDisplayDeviceNumber = isDisplayDeviceNumber;
	}

	public boolean isDisplayDeviceIP() {
		return isDisplayDeviceIP;
	}

	public void setDisplayDeviceIP(boolean isDisplayDeviceIP) {
		this.isDisplayDeviceIP = isDisplayDeviceIP;
	}

	@Override
	public String toString() {
		return "DeviceInfoForSystemSetting [isDisplayWifiState=" + isDisplayWifiState + ", isDisplayServerIP=" + isDisplayServerIP + ", isDisplayDeviceNumber=" + isDisplayDeviceNumber + ", isDisplayDeviceIP=" + isDisplayDeviceIP + "]";
	}

}
