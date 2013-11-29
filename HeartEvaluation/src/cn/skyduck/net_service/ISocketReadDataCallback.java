package cn.skyduck.net_service;

public interface ISocketReadDataCallback {
	/**
	 * 通过socket读取数据回调(一旦读取到数据, 就会调用这个回调)
	 * 
	 * @param exception
	 *          读取过程中如果发生异常, 就通过这个返回, 如果读取正常, 此参数为null,
	 *          所以外部要先通过判断这个参数是否为null来判断本次读取是否正常
	 * @param dataLength
	 *          数据长度
	 * @param dataBuffer
	 *          数据缓冲区
	 */
	public void socketReadDataCallback(final Exception exception, final int dataLength, final byte[] dataBuffer);
}
