package com.codigo.aplios.repository.core.audit;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.eclipse.persistence.internal.sessions.DirectToFieldChangeRecord;
import org.eclipse.persistence.internal.sessions.UnitOfWorkImpl;
import org.eclipse.persistence.queries.InsertObjectQuery;
import org.eclipse.persistence.queries.WriteObjectQuery;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.changesets.ChangeRecord;

import com.codigo.aplios.domain.model.catalog.Category;

public class AuditListener1 extends DescriptorEventAdapter implements SessionCustomizer, DescriptorCustomizer {

	public static ThreadLocal<?> currentUser = new ThreadLocal<>();

	/**
	 * This will audit a specific class.
	 */
	@Override
	public void customize(final ClassDescriptor descriptor) {

		descriptor.getEventManager()
				.addListener(this);
	}

	/**
	 * This will audit all classes.
	 */
	@Override
	public void customize(final Session session) {

		for (final ClassDescriptor descriptor : session.getDescriptors()
				.values())
			this.customize(descriptor);
	}

	@Override
	public void aboutToInsert(final DescriptorEvent event) {

		processEvent(event, AuditOperation.CREATE_OPERATION);
	}

	@Override
	public void aboutToUpdate(final DescriptorEvent event) {

		processEvent(event, AuditOperation.UPDATE_OPERATION);
	}

	@Override
	public void aboutToDelete(final DescriptorEvent event) {

		processEvent(event, AuditOperation.DELETE_OPERATION);
	}

	@SuppressWarnings("unchecked")
	public void processEvent(final DescriptorEvent event, final AuditOperation operation) {

		final Object object = new Category();

		final UnitOfWorkImpl uow = (UnitOfWorkImpl) event.getSession();
		uow.getDescriptor(object);
		event.getChangeSet();

		// boolean isNew = uow.isObjectNew(object);
		// if (isNew || descriptor.getObjectChangePolicy().
		// shouldCompareExistingObjectForChange(object, uow, descriptor)) {
		// ObjectChangeSet changes = null;
		// if (isNew)
		// changes = descriptor.getObjectChangePolicy().
		// calculateChangesForNewObject(object, changeSet, uow, descriptor, true);
		// else
		// changes = descriptor.getObjectChangePolicy().
		// calculateChangesForExistingObject(object, changeSet, uow, descriptor, true);
		// boolean hasChanges = changes.hasChanges();
		// }
		Calendar calendar = Calendar.getInstance();
		for (final String table : (List<String>) event.getDescriptor()
				.getTableNames()) {
			// event.getRecord().put(table + "." + "CREATED_BY", AuditListener1.currentUser.get());
			event.getRecord()
					.put(table + "." + "AUDIT_USER", event.getSession()
							.getDatasourceLogin()
							.getUserName());
			event.getRecord()
					.put(table + "." + "CREATED_DATE", calendar);
			if (operation == AuditOperation.UPDATE_OPERATION)
				processWriteEvent(event, operation, calendar, table);
			else
				processAuditEvent(event, operation, calendar, table);
		}
		calendar = null;
	}

	protected void processAuditEvent(final DescriptorEvent event, final AuditOperation operation,
			final Calendar calendar, final String tableName) {

		final AuditEntry entry = createAuditEntry(event, operation, calendar, tableName);
		final InsertObjectQuery insertQuery = new InsertObjectQuery(
			entry);
		event.getSession()
				.executeQuery(insertQuery);
	}

	protected void processWriteEvent(final DescriptorEvent event, final AuditOperation operation,
			final Calendar calendar, final String tableName) {

		final AuditEntry entry = createAuditEntry(event, operation, calendar, tableName);

		final Collection<AuditField> fields = new LinkedList<>();
		final WriteObjectQuery query = (WriteObjectQuery) event.getQuery();
		final List<ChangeRecord> changes = query.getObjectChangeSet()
				.getChanges();

		for (final ChangeRecord change : changes)
			if (change instanceof DirectToFieldChangeRecord) {
				final DirectToFieldChangeRecord fieldChange = (DirectToFieldChangeRecord) change;
				final AuditField field = new AuditField();
				field.setAuditEntry(entry);
				field.setFieldName(fieldChange.getAttribute());
				field.setFieldValue(fieldChange.getNewValue()
						.toString());
				fields.add(field);
			}

		entry.setFields(fields);

		InsertObjectQuery insertQuery = new InsertObjectQuery(
			entry);
		event.getSession()
				.executeQuery(insertQuery);

		for (final AuditField field : fields) {
			insertQuery = new InsertObjectQuery(
				field);
			event.getSession()
					.executeQuery(insertQuery);
		}
	}

	protected AuditEntry createAuditEntry(final DescriptorEvent event, final AuditOperation operation,
			final Calendar calendar, final String tableName) {

		final AuditEntry entry = new AuditEntry();
		// entry.setAuditUser((String) AuditListener1.currentUser.get());
		entry.setAuditUser(event.getRecord()
				.get("AUDIT_USER")
				.toString());
		// entry.setOperation(operation);
		entry.setOperationTime(calendar);
		entry.setEventId(Long.valueOf(event.getSource()
				.hashCode()));
		entry.setTableName(tableName);
		return entry;
	}

	// private void test() {
	// final JpaEntityManager jpaEntityManager = (JpaEntityManager) entityManager.getDelegate();
	// final UnitOfWorkChangeSet changeSet = jpaEntityManager.getUnitOfWork()
	// .getCurrentChanges();
	// final ObjectChangeSet objectChangeSet = changeSet.getObjectChangeSetForClone(bean);
	//
	// // Get a list of changed propertys and do something with that.
	// final List<String> changedProperties = objectChangeSet.getChanges();
	// for (final String property : changedProperties)
	// System.out.println("Changed property: '" + property);
	//
	// // Check if a property called "coolProperty" has changed.
	// final ChangeRecord coolPropertyChanges =
	// objectChangeSet.getChangesForAttributeNamed("coolProperty");
	// if (coolPropertyChanges != null)
	// System.out.println("Property 'coolProperty' has changed from '" +
	// coolPropertyChanges.getOldValue()
	// + "' to '" + bean.getCoolProperty() + "'");
	// }

}
