package com.codigo.aplios.repository.core;

public interface Auditable {

    AuditSection getAuditSection();

    void setAuditSection(AuditSection audit);

}
