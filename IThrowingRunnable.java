package com.codigo.aplios.group.timeline.core;

@FunctionalInterface
public interface IThrowingRunnable<E extends Throwable> {

	void run()
			throws E;
}