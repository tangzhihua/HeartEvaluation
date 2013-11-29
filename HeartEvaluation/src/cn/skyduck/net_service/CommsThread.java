package cn.skyduck.net_service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import cn.skyduck.toolutils.DebugLog;

public class CommsThread extends Thread {
	private final String TAG = this.getClass().getSimpleName();

	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private ISocketReadDataCallback socketReadDataCallback;

	public CommsThread(Socket socket, ISocketReadDataCallback socketReadDataCallback) throws Exception {
		if (socket == null || socketReadDataCallback == null) {
			throw new IllegalArgumentException("入参 socket/socketReadDataCallback 为空.");
		}

		this.socketReadDataCallback = socketReadDataCallback;
		this.socket = socket;
		// 关闭Nagle算法, 客户端每发送一次数据, 无论数据包大小都会将这些数据发送出去.
		this.socket.setTcpNoDelay(true);
		// ---creates the inputstream and outputstream objects
		// for reading and writing through the sockets---
		this.inputStream = socket.getInputStream();
		this.outputStream = socket.getOutputStream();
	}

	public void run() {
		// ---buffer store for the stream---
		final byte[] dataBuffer = new byte[1024];

		// ---bytes returned from read()---
		int dataLength = 0;

		// ---keep listening to the InputStream until an
		// exception occurs---
		while (true) {
			if (isInterrupted()) {
				DebugLog.e(TAG, "线程被取消.");
				// 线程已经中断
				break;
			}

			Exception exception = null;
			try {
				// ---read from the inputStream---
				dataLength = inputStream.read(dataBuffer);

			} catch (Exception e) {
				// TODO : 目前因为不能确认, 发生异常时, 是否还可以继续读取数据, 所以我们暂时先认为发生异常时就可以中断读取工作了, 所以这里设置-1, 通知上层结束读取工作.
				dataLength = -1;
				exception = e;
				DebugLog.e(TAG, e.getLocalizedMessage());
			} finally {
				if (socketReadDataCallback != null && !isInterrupted()) {
					socketReadDataCallback.socketReadDataCallback(exception, dataLength, dataBuffer);
				}
			}
		}
	}

	// ---call this from the main activity to
	// send data to the remote device---
	public void write(byte[] buffer) throws Exception {
		outputStream.write(buffer);
		outputStream.flush();
	}

	public void write(int oneByte) throws Exception {
		// 将指定的字节写入此输出流。write 的常规协定是：向输出流写入一个字节。要写入的字节是参数 b 的八个低位。b 的 24 个高位将被忽略。
		outputStream.write(oneByte);
		outputStream.flush();
	}

	public void write(byte[] buffer, int offset, int count) throws Exception {
		outputStream.write(buffer, offset, count);
		outputStream.flush();
	}

	// ---call this from the main activity to
	// shutdown the connection---
	public void cancel() throws Exception {
		//
		socketReadDataCallback = null;
		// 中断线程
		this.interrupt();
		// 关闭socket
		if (socket != null) {
			inputStream.close();
			inputStream = null;
			outputStream.close();
			outputStream = null;
			socket.close();
			socket = null;
		}
	}
}