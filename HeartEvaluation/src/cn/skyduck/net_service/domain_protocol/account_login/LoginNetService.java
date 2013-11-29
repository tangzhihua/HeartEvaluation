package cn.skyduck.net_service.domain_protocol.account_login;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import cn.skyduck.net_service.CommsThread;
import cn.skyduck.net_service.IDomainNetRespondCallback;
import cn.skyduck.net_service.INetServiceOptions;
import cn.skyduck.net_service.ISocketReadDataCallback;
import cn.skyduck.net_service.NetServiceOptionsAbstract;
import cn.skyduck.toolutils.DebugLog;
import cn.skyduck.toolutils.MD5Util;

public final class LoginNetService extends NetServiceOptionsAbstract {

	private final String TAG = this.getClass().getSimpleName();

	public static enum NetEventEnum {
		// 登录成功
		NET_REQUEST_SUCCESS,
		// 网络请求失败
		NET_REQUEST_FAIL
	};

	private static enum ServerRespondDataEnum {
		// 无效item
		none((byte) 0),
		// 协议匹配
		protocol_match((byte) 0x40),
		// 用户名密码错误
		user_or_password_error((byte) 0x50);

		public static ServerRespondDataEnum getItemByValue(byte value) {
			if (value == protocol_match.getValue()) {
				return protocol_match;
			} else if (value == user_or_password_error.getValue()) {
				return user_or_password_error;
			} else {
				return none;
			}
		}

		private final byte value;

		private byte getValue() {
			return value;
		}

		private ServerRespondDataEnum(byte value) {
			this.value = value;
		}
	}

	@Override
	public boolean start(final IDomainNetRespondCallback callback) {
		do {
			if (!super.start(callback)) {
				break;
			}

			if (!(super.netRequestBean instanceof LogonNetRequestBean)) {
				break;
			}

			// 启动和服务器建立连接的任务
			new CreateCommThreadTask().execute();
			return true;
		} while (false);

		return false;
	}

	@Override
	public void stop() {
		super.stop();
	}

	/**
	 * 异步方式发送登录信息到服务器
	 * 
	 * @param message
	 */
	private void sendLoginInfoToServerByAsynchronous(String message) {
		byte[] theByteArray = message.getBytes();

		byte[] data = new byte[30];
		for (int i = 0; i < data.length; i++) {
			if (i < theByteArray.length) {
				data[i] = theByteArray[i];
			} else {
				data[i] = 0x20;
			}
		}
		new WriteToServerTask().execute(data);
	}

	private class CreateCommThreadTask extends AsyncTask<Void, Integer, Boolean> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			boolean isConnectServerSuccessful = false;

			netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;

			try {

				DebugLog.i(TAG, "---> login connect: 创建 \"登录\" socket连接!");
				// ---create a socket---
				serverAddress = new InetSocketAddress(host, 12345);
				socket = new Socket();
				DebugLog.i(TAG, "---> login connect: 开始和服务器建立连接......");
				socket.connect(serverAddress, 30 * 1000);
				DebugLog.i(TAG, "---> login connect: 和服务器建立连接成功!");
				if (commsThread != null) {
					commsThread.cancel();
				}
				commsThread = new CommsThread(socket, new ISocketReadDataCallback() {

					@Override
					public void socketReadDataCallback(Exception exception, int dataLength, byte[] dataBuffer) {
						DebugLog.i(TAG, "---> login respond: socketReadDataCallback callback");

						netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;
						NetEventEnum netEventEnum = NetEventEnum.NET_REQUEST_FAIL;
						LogonNetRespondBean logonNetRespondBean = null;
						do {
							if (exception != null) {
								DebugLog.e(TAG, "---> login respond: socketReadDataCallback : 读取数据异常 : 原因[ " + exception.getLocalizedMessage() + "]");
								netErrorMessage = "读取数据异常.";
								break;
							}
							if (dataLength <= 0 || dataBuffer == null || dataLength > dataBuffer.length) {
								DebugLog.e(TAG, "---> login respond: socketReadDataCallback : 服务器返回的数据异常.dataLength <= 0 || dataBuffer == null || dataLength > dataBuffer.length");
								netErrorMessage = "服务器返回的数据异常.dataLength <= 0 || dataBuffer == null || dataLength > dataBuffer.length";
								break;
							}

							byte serverRespondData = dataBuffer[0];
							switch (ServerRespondDataEnum.getItemByValue(serverRespondData)) {
							case protocol_match:// 协议匹配, 登录成功
								DebugLog.i(TAG, "---> login respond: 协议匹配, 登录成功!");
								netEventEnum = NetEventEnum.NET_REQUEST_SUCCESS;
								LogonNetRequestBean logonNetRequestBean = (LogonNetRequestBean) netRequestBean;
								logonNetRespondBean = new LogonNetRespondBean("登录成功", MD5Util.getMD5String(logonNetRequestBean.getLoginName()), logonNetRequestBean.getLoginName(), "sessionId");
								break;
							case user_or_password_error:// 用户名/密码错误
								DebugLog.e(TAG, "---> login respond: 用户名/密码错误!");
								netErrorMessage = "用户名/密码 错误.";
								break;
							default:
								// 服务器反馈的协议字段客户端不能识别
								DebugLog.e(TAG, "---> login respond: 服务器返回的协议数据客户端不能识别.");
								netErrorMessage = "服务器返回的协议数据客户端不能识别.";
								break;
							}
						} while (false);

						if (callback != null) {
							callback.domainNetRespondHandleInNonUIThread(netEventEnum, logonNetRespondBean);
						}
					}
				});
				commsThread.start();

				// 连接服务器成功, 可以进行通信了
				isConnectServerSuccessful = true;

			} catch (NoRouteToHostException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "NoRouteToHostException->防火墙或是路由无法找到主机.";
			} catch (ProtocolException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "ProtocolException->由于不明原因, TCP/IP的数据包被破坏了";
			} catch (SocketTimeoutException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "SocketTimeoutException->服务器连接超时.";
			} catch (ConnectException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "ConnectException->服务器繁忙或者服务器响应的监听端口未打开.";
			} catch (UnknownHostException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "UnknownHostException->服务器地址不正常.";
			} catch (SocketException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "SocketException 错误.";
			} catch (Exception e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "Exception->未知错误.";
			} finally {
				if (!netErrorMessage.equals(INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING)) {
					// 出现异常
					if (callback != null) {
						callback.domainNetRespondHandleInNonUIThread(NetEventEnum.NET_REQUEST_FAIL, null);
					}
				}
			}

			return Boolean.valueOf(isConnectServerSuccessful);
		}

		@Override
		protected void onPostExecute(Boolean isConnectServerSuccessful) {
			//
			if (isConnectServerSuccessful) {
				// 向服务器异步发送登录信息
				LogonNetRequestBean logonNetRequestBean = (LogonNetRequestBean) netRequestBean;
				sendLoginInfoToServerByAsynchronous("@" + logonNetRequestBean.getLoginName() + "@" + logonNetRequestBean.getPassword());
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}
	}

	private class WriteToServerTask extends AsyncTask<byte[], Void, Void> {
		protected Void doInBackground(byte[]... data) {
			netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;
			try {
				DebugLog.i(TAG, "---> login request: 向服务器发送数据......");
				commsThread.write(data[0]);
				DebugLog.i(TAG, "---> login request: 向服务器发送数据完成.");
			} catch (NoRouteToHostException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "防火墙或是路由无法找到主机.";
			} catch (ProtocolException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "由于不明原因, TCP/IP的数据包被破坏了";
			} catch (SocketTimeoutException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "服务器连接超时.";
			} catch (ConnectException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "服务器繁忙或者服务器响应的监听端口未打开.";
			} catch (SocketException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "SocketException 错误.";
			} catch (Exception e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "未知错误.";
			} finally {
				if (!netErrorMessage.equals(INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING)) {
					// 出现异常
					if (callback != null) {
						callback.domainNetRespondHandleInNonUIThread(NetEventEnum.NET_REQUEST_FAIL, null);
					}
				}
			}
			return null;
		}
	}
}
