package com.codigo.aplios.domain.model;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class DomainHelpersTest {

	// private final String domain;
	// private final boolean expected;

	public DomainHelpersTest(final String domain, final boolean expected) {

		// this.domain = domain;
		// this.expected = expected;
	}

	@Parameters(name = "{index}: isValid({0})={1}")
	public static Iterable<Object[]> data() {

		return Arrays.asList(new Object[][] {
				{ "google.com", true },
				{ "mkyong.com", true },
				{ "-mkyong.com", false },
				{ "mkyong-.com", false },
				{ "3423kjk", false },
				{ "mk#$kdo.com", false },
				{ "onet.pl", false } });
	}

	@Test
	public void test_validDomains() {

		// Assert.assertEquals(this.expected, DomainHelpers.isValidDomainName(this.domain));
	}

}
