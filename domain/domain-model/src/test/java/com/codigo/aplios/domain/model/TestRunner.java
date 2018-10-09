package com.codigo.aplios.domain.model;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(final String[] args) throws UnsupportedEncodingException {

		final Result result = JUnitCore.runClasses(PrimeNumberCheckerTest.class);
		for (final Failure failure : result.getFailures())
			System.out.println(failure.toString());
		System.out.println(result.wasSuccessful());

		final Charset utf8 = Charset.forName("utf-8");
		final String asB64 = Base64.getEncoder()
				.encodeToString("andrzej marek radziszewski".getBytes(utf8));
		System.out.println(asB64);

		final byte[] asBytes = Base64.getDecoder()
				.decode("YW5kcnplaiBtYXJlayByYWR6aXN6ZXdza2k=");
		System.out.println(new String(
			asBytes, utf8));

	}
}
