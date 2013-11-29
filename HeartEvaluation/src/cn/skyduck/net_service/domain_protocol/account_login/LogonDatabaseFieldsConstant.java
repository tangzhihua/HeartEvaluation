package cn.skyduck.net_service.domain_protocol.account_login;

public final class LogonDatabaseFieldsConstant {
	private LogonDatabaseFieldsConstant() {
		
	}
	
	public static enum RequestBean {
		// 登录名
		loginName,
		// 密码
		password
	}
	
	public static enum RespondBean {
		// 消息
		message,
		// 用户Id
		userId,
		// 用户名称
		userName,
		// sessionId
		JESSIONID
	}
}
