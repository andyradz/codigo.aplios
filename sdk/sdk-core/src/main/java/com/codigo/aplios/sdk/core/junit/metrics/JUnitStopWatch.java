package com.codigo.aplios.sdk.core.junit.metrics;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

public class JUnitStopWatch extends Stopwatch {

	private static void logInfo(final Description description, final String status, final long nanos) {

		final String testName = description.getMethodName();
		System.out.println(String.format("Test date %s", LocalDateTime.now()
				.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
		System.out.println(String.format("Test %s %s, spent %d microseconds", testName, status,
				TimeUnit.NANOSECONDS.toMicros(nanos)));
	}

	@Override
	protected void succeeded(final long nanos, final Description description) {

		JUnitStopWatch.logInfo(description, "succeeded", nanos);
	}

	@Override
	protected void failed(final long nanos, final Throwable e, final Description description) {

		JUnitStopWatch.logInfo(description, "failed", nanos);
	}

	@Override
	protected void skipped(final long nanos, final AssumptionViolatedException e, final Description description) {

		JUnitStopWatch.logInfo(description, "skipped", nanos);
	}

	@Override
	protected void finished(final long nanos, final Description description) {

		JUnitStopWatch.logInfo(description, "finished", nanos);
	}

}
