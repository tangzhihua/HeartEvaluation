package cn.skyduck.model.setting.sub_section;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PasswordForSystemSetting implements Serializable {
	// 终端管理员密码 (默认是 1)
	private String adminPassword = "1";
	// 进入 "继续未完成" 界面是否需要 系统管理员密码
	private boolean isNeedAdminPasswordToEnterContinueTestActivity = false;
	// 进入 "系统设置" 界面是否需要 系统管理员密码
	private boolean isNeedAdminPasswordToEnterSystemSettingActivity = false;

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public boolean isNeedAdminPasswordToEnterContinueTestActivity() {
		return isNeedAdminPasswordToEnterContinueTestActivity;
	}

	public void setNeedAdminPasswordToEnterContinueTestActivity(boolean isNeedAdminPasswordToEnterContinueTestActivity) {
		this.isNeedAdminPasswordToEnterContinueTestActivity = isNeedAdminPasswordToEnterContinueTestActivity;
	}

	public boolean isNeedAdminPasswordToEnterSystemSettingActivity() {
		return isNeedAdminPasswordToEnterSystemSettingActivity;
	}

	public void setNeedAdminPasswordToEnterSystemSettingActivity(boolean isNeedAdminPasswordToEnterSystemSettingActivity) {
		this.isNeedAdminPasswordToEnterSystemSettingActivity = isNeedAdminPasswordToEnterSystemSettingActivity;
	}

	@Override
	public String toString() {
		return "PasswordForSystemSetting [adminPassword=" + adminPassword + ", isNeedAdminPasswordToEnterContinueTestActivity=" + isNeedAdminPasswordToEnterContinueTestActivity + ", isNeedAdminPasswordToEnterSystemSettingActivity=" + isNeedAdminPasswordToEnterSystemSettingActivity + "]";
	}

}
