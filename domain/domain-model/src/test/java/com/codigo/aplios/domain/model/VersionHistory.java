package com.codigo.aplios.domain.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionHistory implements Serializable {
	/**
	 *
	 */
	private static final long				serialVersionUID	= -4707219892858868420L;
	private final Map<Date, OrderVersion>	orderVersions		= new HashMap<>();
	private final List<OrderVersion>		orderHourMilestones	= new ArrayList<>();

	public OrderVersion findVersion(final Date date) {

		return this.orderVersions.get(date);
	}

	public void addOrderVersion(final Date date, final OrderVersion version) {

		this.orderVersions.put(date, version);
	}

	public void createHourMilestone() {

	}
}
