/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.domain.model.stores;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author andrzej.radziszewski
 */
@Entity
@Table(name = "Store")
public class Store
        extends com.codigo.aplios.domain.model.locale.Dictionary {

    private static final long serialVersionUID = 1182919069421994037L;

    @ColumnPosition(position = 1)
    @Column(name = "Name", length = 400, unique = true, nullable = false)
    private String name;

    @ColumnPosition(position = 2)
    @Column(name = "Url", length = 400, nullable = false)
    private String url;

    @ColumnPosition(position = 3)
    @Column(name = "ContentDeliveryNetwork", length = 400, nullable = true)
    private String contentDeliveryNetwork;

    @ColumnPosition(position = 4)
    @Column(name = "SecureUrl", length = 400, nullable = true)
    private String secureUrl;

    @ColumnPosition(position = 5)
    @Column(name = "Hosts", length = 1000, nullable = true)
    private String hosts;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getContentDeliveryNetwork() {
        return this.contentDeliveryNetwork;
    }

    public void setContentDeliveryNetwork(final String contentDeliveryNetwork) {
        this.contentDeliveryNetwork = contentDeliveryNetwork;
    }

    public String getSecureUrl() {
        return this.secureUrl;
    }

    public void setSecureUrl(final String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public String getHosts() {
        return this.hosts;
    }

    public void setHosts(final String hosts) {
        this.hosts = hosts;
    }

}
