package cn.skyduck.model.setting;

import java.io.Serializable;
import java.util.ArrayList;

import cn.skyduck.model.setting.sub_section.AdminSettingsForSystemSetting;
import cn.skyduck.model.setting.sub_section.DeviceInfoForSystemSetting;
import cn.skyduck.model.setting.sub_section.PasswordForSystemSetting;
import cn.skyduck.model.setting.sub_section.ServerIPForSystemSetting;

@SuppressWarnings("serial")
public class SystemSetting implements Serializable {

	public SystemSetting() {
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				serverIPList.add(new ServerIPForSystemSetting("自定义名称", "192.168.0.34"));
			} else {
				serverIPList.add(new ServerIPForSystemSetting("自定义名称", "255.255.255.255"));
			}
		}
	}

	// 服务器IP 列表(固定3个)
	private ArrayList<ServerIPForSystemSetting> serverIPList = new ArrayList<ServerIPForSystemSetting>(3);

	// 通过索引查找对应的 服务器IP Bean
	public ServerIPForSystemSetting getServerIPCloneByIndex(final int index) {
		if (index < 0 || index > serverIPList.size()) {
			assert false : "入参 index 超出范围";
			return null;
		}
		return (ServerIPForSystemSetting) serverIPList.get(index).clone();
	}

	// 通过索引查找对应的 服务器IP Bean
	public ServerIPForSystemSetting getServerIPByIndex(final int index) {
		if (index < 0 || index > serverIPList.size()) {
			assert false : "入参 index 超出范围";
			return null;
		}
		return (ServerIPForSystemSetting) serverIPList.get(index);
	}

	// 返回服务器列表的 深拷贝 对象
	public ArrayList<ServerIPForSystemSetting> getServerIPListClone() {
		ArrayList<ServerIPForSystemSetting> cloneObject = new ArrayList<ServerIPForSystemSetting>(3);
		for (ServerIPForSystemSetting serverIPForSystemSetting : serverIPList) {
			cloneObject.add((ServerIPForSystemSetting) serverIPForSystemSetting.clone());
		}
		return cloneObject;
	}

	// 更新 服务器IP 列表
	public void updateServerIPList(ArrayList<ServerIPForSystemSetting> newServerIPList) {
		if (newServerIPList == null || newServerIPList.size() != serverIPList.size()) {
			assert false : "入参 newServerIPList 数据错误";
			return;
		}

		serverIPList.clear();
		for (ServerIPForSystemSetting serverIPForSystemSetting : newServerIPList) {
			serverIPList.add(serverIPForSystemSetting);
		}
	}

	// 当前用户选择的是第几个IP作为默认IP
	private int defaultServerIPIndex = 0;

	public int getDefaultServerIPIndex() {
		return defaultServerIPIndex;
	}

	public void setDefaultServerIPIndex(int defaultServerIPIndex) {
		if (defaultServerIPIndex < 0 || defaultServerIPIndex > serverIPList.size()) {
			assert false : "入参 index 超出范围";
			return;
		}
		this.defaultServerIPIndex = defaultServerIPIndex;
	}

	// 设备信息
	private DeviceInfoForSystemSetting deviceInfo = new DeviceInfoForSystemSetting();

	public DeviceInfoForSystemSetting getDeviceInfo() {
		return deviceInfo;
	}

	// 密码设置
	private PasswordForSystemSetting passwordSetting = new PasswordForSystemSetting();

	public PasswordForSystemSetting getPasswordSetting() {
		return passwordSetting;
	}

	// 管理员设置
	private AdminSettingsForSystemSetting adminSettings = new AdminSettingsForSystemSetting();

	public AdminSettingsForSystemSetting getAdminSettings() {
		return adminSettings;
	}

	@Override
	public String toString() {
		return "SystemSetting [serverIPList=" + serverIPList + ", defaultServerIPIndex=" + defaultServerIPIndex + ", deviceInfo=" + deviceInfo + ", passwordSetting=" + passwordSetting
				+ ", adminSettings=" + adminSettings + "]";
	}
}
