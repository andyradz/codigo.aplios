package com.codigo.aplios.domain.model.common;

import com.codigo.aplios.domain.model.catalog.EntityDateTime;
import com.codigo.aplios.domain.model.locale.Dictionary;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TimeZone;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {

    @PrePersist
    public void onEntitySave(Object o) {

        if (o instanceof Dictionary) {
            Dictionary audit = (Dictionary)o;
            EntityDateTime entityDtTm = new EntityDateTime();
            final EntityCreatedInfo entityCreatedInfo = new EntityCreatedInfo();
            entityDtTm.setEntityCreatedInfo(entityCreatedInfo);

            LocalDate localNow = LocalDate.now(TimeZone.getTimeZone("UTC").
                    toZoneId());
            LocalTime localTime = LocalTime.now(TimeZone.getTimeZone("UTC").
                    toZoneId());

            entityCreatedInfo.setCreatedDateUtc(Date.valueOf(localNow));
            entityCreatedInfo.setCreatedTimeUtc(Time.valueOf(localTime));

            audit.setEntityDateTime(entityDtTm);
        }
    }

    @PreUpdate
    public void onEntityUpdate(Object o) {

        if (o instanceof Dictionary) {
            Dictionary audit = (Dictionary)o;
            EntityDateTime entityDtTm = audit.getEntityDateTime();
            final EntityUpdatedInfo entityUpdatedInfo = new EntityUpdatedInfo();
            entityDtTm.setEntityUpdatedInfo(entityUpdatedInfo);

            LocalDate localNow = LocalDate.now(TimeZone.getTimeZone("UTC").
                    toZoneId());
            LocalTime localTime = LocalTime.now(TimeZone.getTimeZone("UTC").
                    toZoneId());

            entityUpdatedInfo.setUpdatedDateUtc(Date.valueOf(localNow));
            entityUpdatedInfo.setUpdatedTimeUtc(Time.valueOf(localTime));

            audit.setEntityDateTime(entityDtTm);
        }
    }

}
