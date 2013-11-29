package cn.skyduck.net_service.domain_protocol.print_report;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;

import android.os.AsyncTask;
import cn.skyduck.global_data_cache.GlobalConstant;
import cn.skyduck.net_service.CommsThread;
import cn.skyduck.net_service.IDomainNetRespondCallback;
import cn.skyduck.net_service.INetServiceOptions;
import cn.skyduck.net_service.ISocketReadDataCallback;
import cn.skyduck.net_service.NetServiceOptionsAbstract;
import cn.skyduck.questionnaire.model.FullQuestionnaireModel;
import cn.skyduck.toolutils.DebugLog;

public final class PrintReportNetService extends NetServiceOptionsAbstract {

	private final String TAG = this.getClass().getSimpleName();

	// 握手是否成功
	private boolean isHandshaked = false;

	public static enum NetEventEnum {
		// 跟服务器建立连接成功
		NET_CONNECT_SUCCESS,
		// 打印一张问卷 - 开始
		PRINT_ONE_QUESTIONNAIRE_BEGIN,
		// 打印一张问卷 - 结束
		PRINT_ONE_QUESTIONNAIRE_END,
		// 打印全部问卷完成
		PRINT_ALL_QUESTIONNAIRE_COMPLETED,
		// 网络请求过程中发生了错误(可能是各种错误)
		NET_ERROR
	};

	private static enum ServerRespondDataEnum {
		// 无效item
		none((byte) 0),
		// 协议匹配, 计算机应答正确，可以传输数据
		protocol_match((byte) 0x30),
		// 发送 删除平板上结果命令
		delete_the_record_of_printed((byte) 0xfe);

		public static ServerRespondDataEnum getItemByValue(byte value) {
			if (value == protocol_match.getValue()) {
				return protocol_match;
			} else if (value == delete_the_record_of_printed.getValue()) {
				return delete_the_record_of_printed;
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

	private static enum ClientRequestDataEnum {
		// 无效item
		none((byte) 0),
		// 协议匹配, 平板电脑向计算机发出发送数据请求
		protocol_match((byte) 0x30),
		// 表示一共有几组数据 计算机可根据几组数据开辟单元
		// 具体是哪一组的数据
		// 这部分用来发送内容
		// 表示传输一组数据结束
		one_set_data_sent_successfully((byte) 0xff), // 0xff 等于 255
		// 循环
		// 表示所有数据传输结束
		all_data_sent_successfully((byte) 0xfd), //
		// 发送 删除结果完成平板反馈信息 给服务器, 服务器收到后断开连接
		delete_the_record_of_printed((byte) 0xfe);

		private final byte value;

		private byte getValue() {
			return value;
		}

		private ClientRequestDataEnum(byte value) {
			this.value = value;
		}
	}

	@Override
	public boolean start(final IDomainNetRespondCallback callback) {
		do {
			if (!super.start(callback)) {
				break;
			}

			if (!(super.netRequestBean instanceof PrintReportNetRequestBean)) {
				break;
			}

			new CreateCommThreadTask().execute();
			return true;
		} while (false);

		return false;
	}

	@Override
	public void stop() {
		isHandshaked = false;
		super.stop();
	}

	// 发送数据到服务器 - 异步方式(不会阻塞)
	private void sendToServerByAsynchronous(int oneByte) {
		new WriteToServerTask().execute(oneByte);
	}

	// 发送数据到服务器 - 同步方式(会阻塞)
	private void sendToServerBySynchronous(byte[] buffer) throws Exception {
		if (commsThread != null) {
			commsThread.write(buffer);
		}
	}

	// 发送数据到服务器 - 同步方式(会阻塞)
	private void sendToServerBySynchronous(int oneByte) throws Exception {
		if (commsThread != null) {
			commsThread.write(oneByte);
		}
	}

	private class CreateCommThreadTask extends AsyncTask<Void, Integer, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean isConnectServerSuccessful = false;

			netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;

			try {

				DebugLog.i(TAG, "---> print connect: 创建 \"打印\" socket连接!");
				// ---create a socket---
				serverAddress = new InetSocketAddress(host, 12345);
				//
				socket = new Socket();
				// 将此套接字连接到具有指定超时值的服务器。超时值零被解释为无限超时。在建立连接或者发生错误之前，连接一直处于阻塞状态。
				DebugLog.i(TAG, "---> print connect: 开始和服务器建立连接......");
				socket.connect(serverAddress, 30 * 1000);
				DebugLog.i(TAG, "---> print connect: 和服务器建立连接成功!");

				if (commsThread != null) {
					commsThread.cancel();
				}
				commsThread = new CommsThread(socket, new ISocketReadDataCallback() {

					@Override
					public void socketReadDataCallback(Exception exception, int dataLength, byte[] dataBuffer) {
						DebugLog.i(TAG, "---> print respond: socketReadDataCallback callback");

						netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;
						NetEventEnum netEventEnum = NetEventEnum.NET_ERROR;

						// 是否需要回调通知 控制器
						boolean isNeedCallbackController = true;

						do {
							if (exception != null) {
								DebugLog.e(TAG, "---> print respond: socketReadDataCallback : 读取数据异常 : 原因[ " + exception.getLocalizedMessage() + "]");
								netErrorMessage = "读取数据发生异常.exception=" + exception.getLocalizedMessage();
								break;
							}
							if (dataLength <= 0 || dataBuffer == null || dataLength > dataBuffer.length) {
								DebugLog.e(TAG, "---> print respond: socketReadDataCallback : 服务器返回的数据异常.dataLength <= 0 || dataBuffer == null || dataLength > dataBuffer.length");
								netErrorMessage = "服务器返回的数据异常.dataLength<=0 || dataBuffer==null";
								break;
							}

							// 服务器向客户端发送的 "命令"
							byte serverRespondData = dataBuffer[0];
							switch (ServerRespondDataEnum.getItemByValue(serverRespondData)) {
							case protocol_match:// 协议匹配, 计算机应答正确，可以传输数据
								if (!isHandshaked) {
									DebugLog.i(TAG, "---> print respond: 协议匹配, 登录成功!");
									isHandshaked = true;
									netEventEnum = NetEventEnum.NET_CONNECT_SUCCESS;
									// 发送问卷数据到服务器
									new SendQuestionnaireDataToServerTask().execute();
								} else {
									// 每次发送完数据体之后, 都会收到服务器正确反馈 0x30
									// 这个消息不需要回调通知 控制器
									isNeedCallbackController = false;
								}

								break;
							case delete_the_record_of_printed:// 打印完成, 服务器发送给客户端
																								// 删除平板上已打印完成的记录的命令
								DebugLog.i(TAG, "---> print respond: 打印完成, 服务器发送给客户端, 删除平板上已打印完成的记录的命令");
								netEventEnum = NetEventEnum.PRINT_ALL_QUESTIONNAIRE_COMPLETED;
								break;
							default:
								// 服务器反馈的协议字段客户端不能识别
								DebugLog.e(TAG, "---> print respond: 服务器返回的协议数据客户端不能识别.");
								netErrorMessage = "服务器返回的协议数据客户端不能识别.";
								break;
							}
						} while (false);

						// 通知控制层
						if (isNeedCallbackController) {
							if (callback != null) {
								callback.domainNetRespondHandleInNonUIThread(netEventEnum, null);
							}
						}
					}
				});

				// 连接服务器成功, 开始进行通信
				commsThread.start();

				isConnectServerSuccessful = true;

				//
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
				netErrorMessage = "SocketException->SocketException 错误.";
			} catch (Exception e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "Exception->未知错误.";
			} finally {
				if (!netErrorMessage.equals(INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING)) {
					// 出现异常
					if (callback != null) {
						callback.domainNetRespondHandleInNonUIThread(NetEventEnum.NET_ERROR, null);
					}
				}
			}

			return Boolean.valueOf(isConnectServerSuccessful);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Boolean isConnectServerSuccessful) {
			if (isConnectServerSuccessful) {
				// 用过异步的方式, 向服务器发送 "握手协议"
				DebugLog.i(TAG, "---> print respond: 向服务器发送握手协议");
				sendToServerByAsynchronous(ClientRequestDataEnum.protocol_match.getValue());
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

	/**
	 * 
	 * @author skyduck
	 * 
	 */
	private class WriteToServerTask extends AsyncTask<Integer, Void, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;
			try {
				commsThread.write(params[0]);
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
			} catch (SocketException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "SocketException->SocketException 错误.";
			} catch (Exception e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "Exception->未知错误.";
			} finally {
				if (!netErrorMessage.equals(INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING)) {
					// 出现异常
					if (callback != null) {
						callback.domainNetRespondHandleInNonUIThread(NetEventEnum.NET_ERROR, null);
					}
				}
			}
			return null;
		}
	}

	private class SendQuestionnaireDataToServerTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {

			netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;

			try {
				do {
					final PrintReportNetRequestBean printReportNetRequestBean = (PrintReportNetRequestBean) netRequestBean;

					DebugLog.i(TAG, "---> print respond: 开始向服务器发送打印量表的数据 --->");
					// 告知服务器本次需要打印多少张问卷
					int oneByte = 0;
					oneByte = printReportNetRequestBean.getQuestionnaireModels().size();
					if (!isBusy) {
						// 用户取消了打印操作
						DebugLog.e(TAG, "用户取消了打印操作!");
						break;
					}
					sendToServerBySynchronous(oneByte);
					DebugLog.i(TAG, "---> print respond: 告知服务器本次需要打印的量表数量=[" + oneByte + "]");

					// 分别发送问卷数据到服务器
					for (int i = 0; i < printReportNetRequestBean.getQuestionnaireModels().size(); i++) {
						if (!isBusy) {
							// 用户取消了打印操作
							DebugLog.e(TAG, "用户取消了打印操作!");
							break;
						}
						final FullQuestionnaireModel questionnaireModel = printReportNetRequestBean.getQuestionnaireModels().get(i);

						try {
							// 通知控制层, 开始打印一个新量表
							if (callback != null) {
								callback.domainNetRespondHandleInNonUIThread(NetEventEnum.PRINT_ONE_QUESTIONNAIRE_BEGIN, questionnaireModel);
							}

							// 告知服务器, 当前正在打印的是第几套问卷
							oneByte = questionnaireModel.getQuestionnaireCodeEnum().getQuestionnaireNumber();
							if (!isBusy) {
								// 用户取消了打印操作
								DebugLog.e(TAG, "用户取消了打印操作!");
								break;
							}
							sendToServerBySynchronous(oneByte);
							DebugLog.i(TAG, "---> print respond: 告知服务器本次需要打印的量表类型=[" + oneByte + "]");

							// 发送问卷答案到服务器
							int opiton = 0;
							if (printReportNetRequestBean.getOptionsEnum() == GlobalConstant.FunctionOptionsEnum.default_print) {
								// "默认打印" : 就是从当前量表的打印类型列表中选择第一个选项作为默认打印的类型
								opiton = questionnaireModel.getQuestionnaireCodeEnum().getDefaultPrintType().getValue();
							} else {
								// 其他类型的打印 : 1:普通 2:详细 3:全面 4:只上传数据不打印
								opiton = printReportNetRequestBean.getOptionsEnum().getValue();
							}
							DebugLog.i(TAG, "---> print respond: 打印方式=[" + printReportNetRequestBean.getOptionsEnum().getName() + ", option=" + opiton + "]");

							final byte[] dataOfQuestionnaireEntity = questionnaireModel.getResultsOfQuestionnaire(opiton);
							if (!isBusy) {
								// 用户取消了打印操作
								DebugLog.e(TAG, "用户取消了打印操作!");
								break;
							}
							sendToServerBySynchronous(dataOfQuestionnaireEntity);
							DebugLog.i(TAG, "---> print respond: 向服务器发送量表实体数据完成=[" + Arrays.toString(dataOfQuestionnaireEntity) + "]");

							// 一组数据发送完成
							oneByte = ClientRequestDataEnum.one_set_data_sent_successfully.getValue();
							if (!isBusy) {
								// 用户取消了打印操作
								DebugLog.e(TAG, "用户取消了打印操作!");
								break;
							}
							sendToServerBySynchronous(oneByte);
							DebugLog.i(TAG, "---> print respond: 一个量表数据发送完成.");

							// 通知控制层, 一个量表发送完成
							if (callback != null) {
								callback.domainNetRespondHandleInNonUIThread(NetEventEnum.PRINT_ONE_QUESTIONNAIRE_END, questionnaireModel);
							}
						} catch (Exception e) {
							DebugLog.e(TAG, "发送量表(" + questionnaireModel.getQuestionnaireCodeEnum().getShortName() + ")失败! 失败原因:" + e.getLocalizedMessage());
						}

						// TODO : 发现发送给后台数据过快的话, 后台打印会丢失一些数据, 所以这里等待1秒
						Thread.sleep(1000);
					}

					// 全部数据发送完成
					oneByte = ClientRequestDataEnum.all_data_sent_successfully.getValue();
					if (!isBusy) {
						// 用户取消了打印操作
						DebugLog.e(TAG, "用户取消了打印操作!");
						break;
					}
					sendToServerBySynchronous(oneByte);

					DebugLog.i(TAG, "---> print respond: 全部量表数据发送完成.");
				} while (false);

			} catch (ProtocolException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "ProtocolException->由于不明原因, TCP/IP的数据包被破坏了";
			} catch (SocketTimeoutException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "SocketTimeoutException->服务器连接超时.";
			} catch (SocketException e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "SocketException->SocketException 错误.";
			} catch (Exception e) {
				DebugLog.e(TAG, e.getLocalizedMessage());
				netErrorMessage = "Exception->未知错误.";
			} finally {
				if (!netErrorMessage.equals(INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING)) {
					// 出现异常
					if (callback != null) {
						callback.domainNetRespondHandleInNonUIThread(NetEventEnum.NET_ERROR, null);
					}
				}
			}

			return null;
		}
	}
}
