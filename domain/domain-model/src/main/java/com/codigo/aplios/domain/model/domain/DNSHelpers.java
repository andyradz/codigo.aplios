package com.codigo.aplios.domain.model.domain;

import java.util.regex.Pattern;

public class DNSHelpers {

	private static Pattern pDomainName;

	private static final String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";

	static {
		DNSHelpers.pDomainName = Pattern.compile(DNSHelpers.DOMAIN_NAME_PATTERN);
	}

	// is this a valid domain name?
	public static boolean isValidDomainName(final String domainName) {

		return DNSHelpers.pDomainName.matcher(domainName)
				.find();
	}

}
