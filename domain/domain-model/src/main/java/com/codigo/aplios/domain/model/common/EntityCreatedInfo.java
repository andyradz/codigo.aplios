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
public class EntityCreatedInfo {

    @Column(name = "CreatedDateUtc")
    @Temporal(TemporalType.DATE)
    @ColumnPosition(position = 91)
    private Date createdDateUtc;

    @Column(name = "CreatedTimeUtc")
    @Temporal(TemporalType.TIME)
    @ColumnPosition(position = 92)
    private Date createdTimeUtc;

    @Column(name = "CreatedByUser")
    @ColumnPosition(position = 93)
    private String createdByUser;

    public Date getCreatedDateUtc() {
        return this.createdDateUtc;
    }

    public void setCreatedDateUtc(Date createdDateUtc) {
        this.createdDateUtc = createdDateUtc;
    }

    public Date getCreatedTimeUtc() {
        return this.createdTimeUtc;
    }

    public void setCreatedTimeUtc(Date createdTimeUtc) {
        this.createdTimeUtc = createdTimeUtc;
    }

    public String getCreatedByUser() {
        return this.createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }
}
