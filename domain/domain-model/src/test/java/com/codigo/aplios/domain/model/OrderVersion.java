package com.codigo.aplios.domain.model;

import java.io.Serializable;
import java.util.Date;

public class OrderVersion implements Serializable {

	private static final long	serialVersionUID	= 3228157029250029216L;
	private Long				id;
	private String				customId;
	private String				name;
	private Double				price;
	private Date				date;

	public OrderVersion() {

		// this.date = Utils.getNow();
	}
}
// gettery i settery
