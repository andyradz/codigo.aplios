/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.domain.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;

/**
 *
 * @author andrzej.radziszewski
 */
@Embeddable
@Customizer(EntityColumnPositionCustomizer.class)
public class EntityPublishedInfo {

	@Column(name = "PublishedDateUtc")
	@Temporal(TemporalType.DATE)
	@ColumnPosition(position = 97)
	private Date publishedDateUtc;

	@Column(name = "PublishedTimeUtc")
	@Temporal(TemporalType.TIME)
	@ColumnPosition(position = 98)
	private Date publishedTimeUtc;

	@Column(name = "PublishedByUser")
	@ColumnPosition(position = 99)
	private String publishedByUser;

	public Date getPublishedDateUtc() {

		return this.publishedDateUtc;
	}

	public void setPublishedDateUtc(Date publishedDateUtc) {

		this.publishedDateUtc = publishedDateUtc;
	}

	public Date getPublishedTimeUtc() {

		return this.publishedTimeUtc;
	}

	public void setPublishedTimeUtc(Date publishedTimeUtc) {

		this.publishedTimeUtc = publishedTimeUtc;
	}

	public Date getPublishedDate() {

		return this.publishedDateUtc;
	}

	public void setPublishedDate(Date published) {

		this.publishedDateUtc = published;
	}

	public String getPublishedByUser() {

		return this.publishedByUser;
	}

	public void setPublishedByUser(String publishedByUser) {

		this.publishedByUser = publishedByUser;
	}
}
