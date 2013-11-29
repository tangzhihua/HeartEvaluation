package cn.skyduck.net_service.domain_protocol.print_report;

public final class PrintReportDatabaseFieldsConstant {
	private PrintReportDatabaseFieldsConstant() {
		
	}
	
	public static enum RequestBean {
		// 登录名
		loginName,
		// 密码
		password,
		// 客户端应用版本号
		clientVersion,
		// 客户端android版本号
		clientAVersion,
		// 屏幕大小
		screenSize
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
