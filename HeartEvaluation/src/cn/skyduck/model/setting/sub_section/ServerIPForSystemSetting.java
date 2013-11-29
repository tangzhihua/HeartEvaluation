package cn.skyduck.model.setting.sub_section;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ServerIPForSystemSetting implements Serializable {
	public ServerIPForSystemSetting(String serverName, String serverIP) {
		this.serverName = serverName;
		this.serverIP = serverIP;
	}

	private String serverName;
	private String serverIP;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	@Override
	public Object clone() {
		return new ServerIPForSystemSetting(serverName, serverIP);
	}

	@Override
	public String toString() {
		return "ServerIPForSystemSetting [serverName=" + serverName + ", serverIP=" + serverIP + "]";
	}

}
