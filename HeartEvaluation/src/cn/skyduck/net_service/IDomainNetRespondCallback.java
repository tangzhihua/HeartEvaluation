package cn.skyduck.net_service;

public interface IDomainNetRespondCallback {

	public void domainNetRespondHandleInNonUIThread(final Enum<?> event, final Object respondData);
}
