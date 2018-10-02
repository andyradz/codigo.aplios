package com.codigo.aplios.repository.core.audit;

import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {

    @PrePersist
    @PreUpdate
    public void onSaveOrUpdate(Object o) {

        if (o instanceof Auditable1) {
            Auditable1 audit = (Auditable1)o;
            AuditSection1 auditSection = audit.getAuditSection();

            auditSection.setDateModified(new Date());
            if (auditSection.getDateCreated() == null)
                auditSection.setDateCreated(new Date());
            // auditSection.setCreatedBy("Admin");
            audit.setAuditSection(auditSection);
        }
    }

}
