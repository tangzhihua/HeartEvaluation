package cn.skyduck.net_service;

public interface INetServiceOptions {

	public static final String NET_ERROR_MESSAGE_DEFAULT_STRING = "OK";

	public abstract boolean start(final IDomainNetRespondCallback callback);

	public abstract void stop();

	public abstract void setNetRequestBean(final Object netRequestBean);

	public abstract String getNetErrorMessage();
}
