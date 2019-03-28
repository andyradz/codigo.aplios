package com.codigo.aplios.group.sdk.core.hamcrest;

@FunctionalInterface
public interface IThrowingRunnable<E extends Throwable> {

	void run()
			throws E;
}