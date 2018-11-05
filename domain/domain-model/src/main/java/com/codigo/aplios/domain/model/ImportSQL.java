package com.codigo.aplios.domain.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;
import org.eclipse.persistence.sessions.UnitOfWork;

//opis UNITAPI https://wiki.eclipse.org/Using_Advanced_Unit_of_Work_API_(ELUG)

public class ImportSQL implements SessionCustomizer {

	private void importSql(final UnitOfWork unitOfWork, final String fileName) {

		// Open file
		// Execute each line, e.g.,
		final Path path = Paths.get("src/main/resources/" + fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {

			String currentLine = null;
			while ((currentLine = reader.readLine()) != null)
				System.out.println(currentLine);
			/// unitOfWork.executeNonSelectingSQL(currentLine);
		}
		catch (final IOException ex) {

			ex.printStackTrace();
		}
		finally {
			// unitOfWork.commit();
		}
	}

	@Override
	public void customize(final Session session) throws Exception {

		((AbstractSession) session).setIsConcurrent(true);

		session.getEventManager()
				.addListener(new SessionEventAdapter() {

					@Override
					public void postAcquireClientSession(final SessionEvent event) {

						final String fileName = (String) event.getSession()
								.getProperty("import.sql.file");

						final UnitOfWork unitOfWork = event.getSession()
								.acquireUnitOfWork();
						importSql(unitOfWork, fileName);
						unitOfWork.commit();
					}
				});
	}
}