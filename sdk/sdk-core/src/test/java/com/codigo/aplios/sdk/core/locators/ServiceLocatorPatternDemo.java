package com.codigo.aplios.sdk.core.locators;

class ServiceLocatorPatternDemo {

	public static void main(final String[] args) {

		Service service = ServiceLocator.getService("Service1");
		service.execute();
		service = ServiceLocator.getService("Service2");
		service.execute();
		service = ServiceLocator.getService("Service1");
		service.execute();
		service = ServiceLocator.getService("Service2");
		service.execute();
	}
}
