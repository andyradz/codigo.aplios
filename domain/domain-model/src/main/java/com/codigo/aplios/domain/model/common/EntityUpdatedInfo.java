/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.domain.model.common;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.Customizer;

/**
 *
 * @author andrzej.radziszewski
 */
@Embeddable
@Customizer(EntityColumnPositionCustomizer.class)
public class EntityUpdatedInfo {

    @Column(name = "UpdatedDateUtc")
    @Temporal(TemporalType.DATE)
    @ColumnPosition(position = 94)
    private Date updatedDateUtc;

    @Column(name = "UpdatedTimeUtc")
    @Temporal(TemporalType.TIME)
    @ColumnPosition(position = 95)
    private Date updatedTimeUtc;

    @Column(name = "UpdatedByUser")
    @ColumnPosition(position = 96)
    private String updatedByUser;

    public Date getUpdatedDateUtc() {
        return this.updatedDateUtc;
    }

    public void setUpdatedDateUtc(Date updatedDateUtc) {
        this.updatedDateUtc = updatedDateUtc;
    }

    public Date getUpdatedTimeUtc() {
        return this.updatedTimeUtc;
    }

    public void setUpdatedTimeUtc(Date updatedTimeUtc) {
        this.updatedTimeUtc = updatedTimeUtc;
    }

    public String getUpdatedByUser() {
        return this.updatedByUser;
    }

    public void setUpdatedByUser(String updatedByUser) {
        this.updatedByUser = updatedByUser;
    }
}
