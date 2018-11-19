package com.codigo.aplios.repository.core.descript;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;

public class HistoryCustomizer implements DescriptorCustomizer {

	@Override
	public void customize(final ClassDescriptor descriptor) {

		final HistoryPolicy policy = new HistoryPolicy();
		policy.addHistoryTableName("EMPLOYEE_HIST");
		policy.addStartFieldName("START_DATE");
		policy.addEndFieldName("END_DATE");
		descriptor.setHistoryPolicy(policy);
	}

}
