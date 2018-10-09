package com.codigo.aplios.sdk.core.locators;

class Service1 implements Service {
	@Override
	public void execute() {
		System.out.println("Executing Service1");
	}

	@Override
	public String getName() {
		return "Service1";
	}
}
