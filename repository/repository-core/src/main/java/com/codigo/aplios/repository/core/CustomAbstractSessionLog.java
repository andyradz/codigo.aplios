package com.codigo.aplios.repository.core;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;

public class CustomAbstractSessionLog extends AbstractSessionLog implements SessionLog {

	/*
	 * @see org.eclipse.persistence.logging.AbstractSessionLog#log(org.eclipse.
	 * persistence.logging.SessionLogEntry)
	 */
	@Override
	public void log(final SessionLogEntry sessionLogEntry) {

		System.out.println("CUSTOM: " + sessionLogEntry.getMessage()); // untranslated/undecoded message_id
	}
}