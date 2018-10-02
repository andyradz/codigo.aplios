package com.codigo.aplios.repository.core;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {

    @PrePersist
    @PreUpdate
    public void onSaveOrUpdate(Object o) {
        if (o instanceof Auditable) {
            Auditable audit = (Auditable)o;
//            AuditSection auditSection = audit.getAuditSection();
//
//            auditSection.setDateModified(new Date());
//            if (auditSection.getDateCreated() == null)
//                auditSection.setDateCreated(new Date());
//            audit.setAuditSection(auditSection);
        }
    }

}
