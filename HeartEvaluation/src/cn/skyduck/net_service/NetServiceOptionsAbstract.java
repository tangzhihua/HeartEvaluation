package cn.skyduck.net_service;

import java.net.InetSocketAddress;
import java.net.Socket;

import android.text.TextUtils;
import cn.skyduck.global_data_cache.GlobalDataCacheForMemorySingleton;
import cn.skyduck.model.setting.sub_section.ServerIPForSystemSetting;
import cn.skyduck.toolutils.DebugLog;

public abstract class NetServiceOptionsAbstract implements INetServiceOptions {

	private final String TAG = this.getClass().getSimpleName();

	protected IDomainNetRespondCallback callback;

	protected Object netRequestBean;

	public void setNetRequestBean(final Object netRequestBean) {
		this.netRequestBean = netRequestBean;
	}

	protected String netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;

	public String getNetErrorMessage() {
		return netErrorMessage;
	}

	protected volatile boolean isBusy = false;

	//
	protected String host;
	// ---socket---
	protected InetSocketAddress serverAddress;
	protected Socket socket;

	// ---thread for communicating on the socket---
	protected CommsThread commsThread;

	@Override
	public boolean start(IDomainNetRespondCallback callback) {
		do {
			if (!(callback instanceof IDomainNetRespondCallback)) {
				assert false : "入参为null.";
				break;
			}
			if (isBusy) {
				DebugLog.e(TAG, "当前网络服务已在运行.");
				break;
			}
			isBusy = true;

			this.callback = callback;

			final int defaultServerIPIndex = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getDefaultServerIPIndex();
			final ServerIPForSystemSetting serverIP = GlobalDataCacheForMemorySingleton.getInstance.getSystemSetting().getServerIPCloneByIndex(defaultServerIPIndex);
			if (serverIP == null) {
				DebugLog.e(TAG, "端口无效.");
				break;
			}
			host = serverIP.getServerIP();
			if (TextUtils.isEmpty(host)) {
				DebugLog.e(TAG, "端口无效.");
				break;
			}

			return true;
		} while (false);

		return false;
	}

	@Override
	public void stop() {
		isBusy = false;
		callback = null;
		//
		if (commsThread != null) {
			try {
				commsThread.cancel();
			} catch (Exception e) {
				e.printStackTrace();
			}
			commsThread = null;
		}
		//
		host = null;
		socket = null;
		serverAddress = null;
	}

}
