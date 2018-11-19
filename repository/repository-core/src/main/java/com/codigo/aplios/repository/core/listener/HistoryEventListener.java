package com.codigo.aplios.repository.core.listener;

import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.eclipse.persistence.internal.sessions.ObjectChangeSet;

public class HistoryEventListener extends DescriptorEventAdapter {

	@Override
	public void postUpdate(final DescriptorEvent event) {

		final ObjectChangeSet changeSet = event.getChangeSet();
		if (changeSet != null)
			System.out.println("ObjectChangeSet not null");
		System.out.println("ObjectChangeSet null");
	}

	@Override
	public void postMerge(final DescriptorEvent event) {

		final ObjectChangeSet changeSet = event.getChangeSet();
		if (changeSet != null)
			System.out.println("ObjectChangeSet not null");
		System.out.println("ObjectChangeSet null");
	}

}
