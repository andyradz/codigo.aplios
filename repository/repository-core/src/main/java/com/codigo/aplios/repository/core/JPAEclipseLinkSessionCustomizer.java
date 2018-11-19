package com.codigo.aplios.repository.core;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.sessions.Session;

/**
 * <properties> <property name="eclipselink.session.customizer"
 * value="org.eclipse.persistence.example.unified.integration.JPAEclipseLinkSessionCustomizer"/
 *
 * @author dp0470
 *
 */
public class JPAEclipseLinkSessionCustomizer implements SessionCustomizer {

	@Override
	public void customize(final Session aSession) throws Exception {

		// create a custom logger
		final SessionLog aCustomLogger = new CustomAbstractSessionLog();
		aCustomLogger.setLevel(1); // Logging level finest
		aSession.setSessionLog(aCustomLogger);
	}
}