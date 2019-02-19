package com.codigo.aplios.repository.core.audit;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
@EntityListeners({ AuditListener.class })
public abstract class AuditableEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3077961887990985379L;

	public static ThreadLocal<?> currentUser = new ThreadLocal<>();

	@Column(name = "AUDIT_USER")
	protected String auditUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUDIT_TIMESTAMP")
	protected Calendar auditTimestamp;

	public String getAuditUser() {

		return this.auditUser;
	}

	public void setAuditUser(final String auditUser) {

		this.auditUser = auditUser;
	}

	public Calendar getAuditTimestamp() {

		return this.auditTimestamp;
	}

	public void setAuditTimestamp(final Calendar auditTimestamp) {

		this.auditTimestamp = auditTimestamp;
	}

	@PrePersist
	@PreUpdate
	public void updateAuditInfo() {

		setAuditUser((String) AuditableEntity.currentUser.get());
		setAuditTimestamp(Calendar.getInstance());
	}

	// -----------------------------------------------------------------------//
	// toString
	// -----------------------------------------------------------------------//
	@Override
	public String toString() {

		return "";
	}

}
