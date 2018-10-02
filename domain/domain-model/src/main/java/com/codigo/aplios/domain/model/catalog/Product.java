package com.codigo.aplios.domain.model.catalog;

import com.codigo.aplios.domain.model.common.EntityModel;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.Customizer;

@Entity
// @Customizer(HistoryCustomizer.class)
@Customizer(EntityColumnPositionCustomizer.class)
// @EntityListeners(value = AuditListener.class)
@Table(name = "Products")
public class Product
        extends EntityModel {

    private static final long serialVersionUID = -1399154554678035069L;

    @Column(name = "name", nullable = false, unique = true, length = 150)
    @ColumnPosition(position = 1)
    private String name;

    @ColumnPosition(position = 2)
    @Column(name = "price", nullable = false)
    private Double price;

    @ColumnPosition(position = 3)
    @Column(name = "createdDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date createdDate;

    @ColumnPosition(position = 4)
    @Column(name = "createdTime", nullable = false)
    @Temporal(TemporalType.TIME)
    private java.util.Date createdTime;

    @ElementCollection
    @CollectionTable(name = "dimenssions", joinColumns =
            @JoinColumn(name = "id"))
    Set<Dimension> dimension;

    public Product() {

        super();
    }

    public String getName() {

        return this.name;
    }

    public Double getPrice() {

        return this.price;
    }

    public void setPrice(Double price) {

        this.price = price;
    }

    public void setName(String name) {

        this.name = name;
    }

    public java.util.Date getCreatedDate() {

        return this.createdDate;
    }

    public void setCreatedDate(java.util.Date createdDate) {

        this.createdDate = createdDate;
    }

    public java.util.Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(java.util.Date createdTime) {

        this.createdTime = createdTime;
    }

    public Set<Dimension> getDimension() {
        return this.dimension;
    }

    public void setDimension(Set<Dimension> dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return this.name + " " + this.price;
    }

}
