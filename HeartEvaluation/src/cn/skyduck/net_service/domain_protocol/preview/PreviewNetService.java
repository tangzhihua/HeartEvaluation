package cn.skyduck.net_service.domain_protocol.preview;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import cn.skyduck.activity.MyApplication;
import cn.skyduck.net_service.CommsThread;
import cn.skyduck.net_service.IDomainNetRespondCallback;
import cn.skyduck.net_service.INetServiceOptions;
import cn.skyduck.net_service.ISocketReadDataCallback;
import cn.skyduck.net_service.NetServiceOptionsAbstract;
import cn.skyduck.toolutils.DebugLog;

public final class PreviewNetService extends NetServiceOptionsAbstract {

	private final String TAG = this.getClass().getSimpleName();

	// 已经握过手了(就不需要再握了)
	private boolean isHandshaked = false;

	public static enum NetEventEnum {
		// 跟服务器建立连接成功
		NET_CONNECT_SUCCESS,
		// 下载图片 - 开始
		DOWNLOAD_IMAGE_BEGIN,
		// 下载图片 - 进度
		DOWNLOAD_IMAGE_PROGRESS,
		// 下载图片 - 结束
		DOWNLOAD_IMAGE_END,
		// 网络请求过程中发生了错误(可能是各种错误)
		NET_ERROR
	};

	private static enum ServerRespondDataEnum {
		// 无效item
		none((byte) 0),
		// 协议匹配, 计算机应答正确，可以传输数据
		protocol_match((byte) 0x30),
		// 发送 删除平板上结果命令
		delete_the_record_of_printed((byte) 0xfe),
		// 下载进度
		download_progress((byte) -1),
		// 下载完成
		download_complete((byte) -1);

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
		one_set_data_sent_successfully((byte) 0xff),
		// 循环
		// 表示所有数据传输结束
		all_data_sent_successfully((byte) 0xfd),
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

	private Handler handler = new Handler();

	// 读取图片的 socket
	private Socket socketOfReadReportImage;
	//
	private CommsThread commsThreadOfReadReportImage;

	@Override
	public boolean start(final IDomainNetRespondCallback callback) {
		do {
			if (!super.start(callback)) {
				break;
			}

			if (!(super.netRequestBean instanceof PreviewNetRequestBean)) {
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
		//
		super.stop();
		//
		if (commsThreadOfReadReportImage != null) {
			try {
				commsThreadOfReadReportImage.cancel();
			} catch (Exception e) {
				e.printStackTrace();
			}
			commsThreadOfReadReportImage = null;
		}
		socketOfReadReportImage = null;
	}

	// 发送数据到服务器 - 异步方式(不会阻塞)
	private void sendToServerByAsynchronous(byte[] data) {
		new WriteToServerTask().execute(data);
	}

	// 发送数据到服务器 - 同步方式(会阻塞)
	private void sendToServerBySynchronous(byte[] buffer) throws Exception {
		commsThread.write(buffer);
	}

	// 发送数据到服务器 - 同步方式(会阻塞)
	private void sendToServerBySynchronous(int oneByte) throws Exception {
		commsThread.write(oneByte);
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

				// ---create a socket---
				serverAddress = new InetSocketAddress(host, 12345);
				socket = new Socket();
				socket.connect(serverAddress, 30 * 1000);
				if (commsThread != null) {
					commsThread.cancel();
				}
				// 创建 socket 线程
				commsThread = new CommsThread(socket, new ISocketReadDataCallback() {

					@Override
					public void socketReadDataCallback(Exception exception, int dataLength, byte[] dataBuffer) {

						netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;
						NetEventEnum netEventEnum = NetEventEnum.NET_ERROR;

						// 是否需要回调通知 控制器
						boolean isNeedCallbackController = true;

						do {
							if (exception != null) {
								netErrorMessage = "读取数据发生异常.exception=" + exception.getLocalizedMessage();
								break;
							}
							if (dataLength <= 0 || dataBuffer == null) {
								netErrorMessage = "服务器返回的数据异常.dataLength<=0 || dataBuffer == null";
								break;
							}

							// 服务器向客户端发送的 "命令"
							byte serverRespondData = dataBuffer[0];
							switch (ServerRespondDataEnum.getItemByValue(serverRespondData)) {
							case protocol_match:// 协议匹配, 计算机应答正确，可以传输数据
								if (!isHandshaked) {
									isHandshaked = true;
									netEventEnum = NetEventEnum.NET_CONNECT_SUCCESS;
									// 发送问卷数据到服务器
									handler.post(new Runnable() {

										@Override
										public void run() {
											new SendQuestionnaireDataToServerTask().execute();
										}
									});

								} else {
									// 每次发送完数据体之后, 都会收到服务器正确反馈 0x30
									isNeedCallbackController = false;
								}

								break;
							case delete_the_record_of_printed:// 打印完成, 服务器发送给客户端,
																								// 删除平板上已打印完成的记录的命令
								isNeedCallbackController = false;
								break;
							default:
								// 服务器反馈的协议字段客户端不能识别
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
		protected void onPostExecute(Boolean isConnectServerSuccessful) {
			if (isConnectServerSuccessful) {
				isHandshaked = false;

				// 向服务器发送 "握手协议"
				final byte[] dataOfHandshakeToServer = new byte[1];
				dataOfHandshakeToServer[0] = ClientRequestDataEnum.protocol_match.getValue();
				sendToServerByAsynchronous(dataOfHandshakeToServer);
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
	private class WriteToServerTask extends AsyncTask<byte[], Void, Void> {
		protected Void doInBackground(byte[]... data) {
			netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;
			try {
				commsThread.write(data[0]);
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

	private class SendQuestionnaireDataToServerTask extends AsyncTask<Void, Void, Boolean> {
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
				final PreviewNetRequestBean previewNetRequestBean = (PreviewNetRequestBean) netRequestBean;

				int oneByte = 0;

				// 告知服务器本次需要打印多少张问卷
				sendToServerBySynchronous(1);

				// 告知服务器, 当前正在打印的是第几套问卷
				oneByte = previewNetRequestBean.getQuestionnaireModel().getQuestionnaireCodeEnum().getQuestionnaireNumber();
				sendToServerBySynchronous(oneByte);

				// 发送问卷答案到服务器
				// 这里发送给服务器的协议代码是 : 5:普通预览 6:详细预览 7:全面预览
				final byte[] dataOfQuestionnaireEntity = previewNetRequestBean.getQuestionnaireModel().getResultsOfQuestionnaire(previewNetRequestBean.getOptionsEnum().getValue());
				sendToServerBySynchronous(dataOfQuestionnaireEntity);

				// 一组数据发送完成
				oneByte = ClientRequestDataEnum.one_set_data_sent_successfully.getValue();
				sendToServerBySynchronous(oneByte);

				// 全部数据发送完成
				oneByte = ClientRequestDataEnum.all_data_sent_successfully.getValue();
				sendToServerBySynchronous(oneByte);

				// ///////////////////////////////////////////////
				if (commsThread != null) {
					try {
						commsThread.cancel();
					} catch (Exception e) {
						DebugLog.e(TAG, e.getLocalizedMessage());
					}
					commsThread = null;
				}

				isConnectServerSuccessful = true;

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

			return Boolean.valueOf(isConnectServerSuccessful);
		}

		@Override
		protected void onPostExecute(Boolean isConnectServerSuccessful) {
			if (isConnectServerSuccessful) {
				// 启动图片下载分线程
				new DownloadResportImageThreadTask().execute();
			}
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

	}

	private int dataLengthOfReportImage = 0;
	private String pathOfReportImageString = "";

	private class DownloadResportImageThreadTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			netErrorMessage = INetServiceOptions.NET_ERROR_MESSAGE_DEFAULT_STRING;

			try {

				// 现在发现在 connect 时, 很容易报出ConnectException异常, 是否延迟一段时间就可以解决这个问题呢
				Thread.sleep(1000 * 2);

				// ---create a socket---
				InetSocketAddress serverAddress = new InetSocketAddress(host, 12344);
				socketOfReadReportImage = new Socket();
				socketOfReadReportImage.connect(serverAddress, 30 * 1000);

				// 判断SDCARD是否存在于手机上，并且可以进行读写访问
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
					pathOfReportImageString = Environment.getExternalStorageDirectory().getPath() + "/" + "report_image";
				} else {
					pathOfReportImageString = MyApplication.getApplication().getApplicationContext().getFilesDir().getPath() + "/" + "report_image";
				}
				final File fileOfTmp = new File(pathOfReportImageString);
				if (fileOfTmp.exists()) {
					fileOfTmp.delete();
				}
				fileOfTmp.createNewFile();
				final FileOutputStream fileOutputStream = new FileOutputStream(fileOfTmp);

				// 创建 socket 线程
				dataLengthOfReportImage = 0;
				if (commsThreadOfReadReportImage != null) {
					commsThreadOfReadReportImage.cancel();
				}
				commsThreadOfReadReportImage = new CommsThread(socketOfReadReportImage, new ISocketReadDataCallback() {

					@Override
					public void socketReadDataCallback(Exception exception, int dataLength, byte[] dataBuffer) {

						if (!isBusy) {
							// 操作已经终止
							return;
						}
						if (dataLength > dataBuffer.length) {
							assert false : "入参异常!dataLength > dataBuffer.length";
							return;
						}
						if (dataLength <= 0) {// 数据读取完成
							DebugLog.i(TAG, "本次下载的文件大小 = " + dataLengthOfReportImage);
							dataLengthOfReportImage = 0;
							try {
								fileOutputStream.close();
							} catch (IOException e) {
								DebugLog.e(TAG, e.getLocalizedMessage());
							} finally {
							}

							try {
								commsThreadOfReadReportImage.cancel();
							} catch (Exception e) {
								DebugLog.e(TAG, e.getLocalizedMessage());
							}

							if (callback != null) {
								Bitmap bitmap = BitmapFactory.decodeFile(pathOfReportImageString);
								if (bitmap != null) {
									callback.domainNetRespondHandleInNonUIThread(NetEventEnum.DOWNLOAD_IMAGE_END, bitmap);
								} else {
									netErrorMessage = "图片文件下载不完整.";
									callback.domainNetRespondHandleInNonUIThread(NetEventEnum.NET_ERROR, null);
								}

							}

						} else {

							try {
								if (dataLengthOfReportImage <= 0) {
									// 前4个字节不是图片数据, 是图片尺寸
									if (dataLength > 4) {
										fileOutputStream.write(dataBuffer, 4, dataLength - 4);
										dataLengthOfReportImage += (dataLength - 4);
									} else {
										DebugLog.e(TAG, "读取第一段数据有问题, dataLength < 4");
									}
								} else {
									fileOutputStream.write(dataBuffer, 0, dataLength);
									dataLengthOfReportImage += dataLength;
								}

								// DebugLog.i(TAG, "本次读取到的数据是-->" +
								// Arrays.toString(dataBuffer));
							} catch (IOException e) {
								DebugLog.e(TAG, e.getLocalizedMessage());
							} catch (Exception e) {
								DebugLog.e(TAG, e.getLocalizedMessage());
							}
						}

					}
				});

				// 连接服务器成功, 开始进行通信
				commsThreadOfReadReportImage.start();

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

			return null;
		}
	}

}
