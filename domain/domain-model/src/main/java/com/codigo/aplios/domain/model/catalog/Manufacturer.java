package com.codigo.aplios.domain.model.catalog;

import com.codigo.aplios.domain.model.common.Picture;
import com.codigo.aplios.domain.model.locale.Dictionary;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Customizer;

@Entity
@Table(name = "Manufacturer")
@Customizer(EntityColumnPositionCustomizer.class)
public class Manufacturer extends Dictionary {

    private static final long serialVersionUID = 7430687639926273269L;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "ManufacturerTemplateId", nullable = false)
    private long manufacturerTemplateId;

    @Column(name = "MetaKeywords")
    private String metaKeywords;

    @Column(name = "MetaDescription")
    private String metaDescription;

    @Column(name = "MetaTitle")
    private String metaTitle;

    //@OneToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.REFRESH)
    // @PrimaryKeyJoinColumns({ @PrimaryKeyJoinColumn(name = "id") }, ta)
    // private Picture picture;
    //@JoinColumn(name = "Pictureid")
    //@JoinColumn(name = "PictureId")
    private Picture picture;

    @Column(name = "PageSize")
    private long pageSize;

    @Column(name = "AllowCustomersToSelectPageSize")
    private boolean allowCustomersToSelectPageSize;

    @Column(name = "PageSizeOptions")
    private String pageSizeOptions;

    @Column(name = "PriceRanges")
    private String priceRanges;

    @Column(name = "LimitedToStores", nullable = false)
    private boolean limitedToStores;

    @Column(name = "HasDiscountsApplied", nullable = false)
    private boolean hasDiscountsApplied;

}
