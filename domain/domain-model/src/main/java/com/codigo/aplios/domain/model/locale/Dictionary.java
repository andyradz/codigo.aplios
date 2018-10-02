package com.codigo.aplios.domain.model.locale;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;
import com.codigo.aplios.domain.model.catalog.EntityDateTime;
import com.codigo.aplios.domain.model.catalog.EntityLifeState;
import com.codigo.aplios.domain.model.common.AuditListener;
import com.codigo.aplios.domain.model.common.EntityModel;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Customizer;

@Table(name = "Dictionary")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(value = AuditListener.class)
@Customizer(EntityColumnPositionCustomizer.class)
@MappedSuperclass
public class Dictionary
        extends EntityModel {

    private static final long serialVersionUID = -1118611759382185681L;

    @Column(name = "DisplayOrder")
    @ColumnPosition(position = 87)
    private long displayOrder;

    @Column(name = "Published")
    @ColumnPosition(position = 88)
    private boolean isPublished;

    @ColumnPosition(position = 86)
    @Column(name = "Description", length = 255, nullable = true)
    private String description;

    @Embedded
    @ColumnPosition(position = 90)
    private EntityDateTime entityDateTime;

    @Embedded
    @ColumnPosition(position = 89)
    private EntityLifeState entityLifeState;

    public long getDisplayOrder() {

        return this.displayOrder;
    }

    public void setDisplayOrder(final long displayOrder) {

        this.displayOrder = displayOrder;
    }

    public boolean isPublished() {

        return this.isPublished;
    }

    public void setPublished(final boolean isPublished) {

        this.isPublished = isPublished;
    }

    public EntityDateTime getEntityDateTime() {
        return this.entityDateTime;
    }

    public void setEntityDateTime(final EntityDateTime metainfo) {
        this.entityDateTime = metainfo;
    }

    public EntityLifeState getEntityLifeState() {
        return this.entityLifeState;
    }

    public void setEntityLifeState(final EntityLifeState entityState) {
        this.entityLifeState = entityState;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

}
