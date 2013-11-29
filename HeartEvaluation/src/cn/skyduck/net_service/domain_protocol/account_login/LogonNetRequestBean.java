package cn.skyduck.net_service.domain_protocol.account_login;

public final class LogonNetRequestBean {
	// 登录名
	private final String loginName;
	// 密码
	private final String password;

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}

	public LogonNetRequestBean(String loginName, String password) {
		this.loginName = loginName;
		this.password = password;
	}

}
