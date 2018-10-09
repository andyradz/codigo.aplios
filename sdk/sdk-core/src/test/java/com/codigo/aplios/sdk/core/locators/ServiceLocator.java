package com.codigo.aplios.sdk.core.locators;

public class ServiceLocator {
	private static Cache cache;

	static {
		ServiceLocator.cache = new Cache();
	}

	public static Service getService(final String jndiName) {

		final Service service = ServiceLocator.cache.getService(jndiName);

		if (service != null)
			return service;

		final InitialContext context = new InitialContext();
		final Service service1 = (Service) context.lookup(jndiName);
		ServiceLocator.cache.addService(service1);
		return service1;
	}
}
