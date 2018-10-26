package data.mapping;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Session;

public class MySessionCustomizer implements SessionCustomizer {

	public MySessionCustomizer() {

	}

	@Override
	public void customize(Session session) throws Exception {

		DatabaseLogin login = (DatabaseLogin) session.getDatasourceLogin();
		// enable 'dirty' reads
		login.setTransactionIsolation(DatabaseLogin.TRANSACTION_READ_COMMITTED);
		//<property name="orSessionCustomizerClassName">some.java.package.MyORSessionCustomizer</property>
	}
}