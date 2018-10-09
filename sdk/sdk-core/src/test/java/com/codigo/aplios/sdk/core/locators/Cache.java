package com.codigo.aplios.sdk.core.locators;

//wzorce http://www.tutorialspoint.com/design_pattern/proxy_pattern.htm

import java.util.ArrayList;
import java.util.List;

public class Cache {

	private final List<Service> services;

	public Cache() {
		this.services = new ArrayList<>();
	}

	public Service getService(final String serviceName) {

		for (final Service service : this.services)
			if (service.getName()
					.equalsIgnoreCase(serviceName)) {
				System.out.println("Returning cached  " + serviceName + " object");
				return service;
			}
		return null;
	}

	public void addService(final Service newService) {
		boolean exists = false;

		for (final Service service : this.services)
			if (service.getName()
					.equalsIgnoreCase(newService.getName()))
				exists = true;
		if (!exists)
			this.services.add(newService);
	}
}
