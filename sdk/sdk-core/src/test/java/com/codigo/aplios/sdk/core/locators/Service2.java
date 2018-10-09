package com.codigo.aplios.sdk.core.locators;

class Service2 implements Service {
	@Override
	public void execute() {
		System.out.println("Executing Service2");
	}

	@Override
	public String getName() {
		return "Service2";
	}
}
