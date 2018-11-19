package com.codigo.aplios.domain.model.orders;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T23:10:29.811+0100")
@StaticMetamodel(VersionHistory.class)
public class VersionHistory_ {
	public static volatile MapAttribute<VersionHistory, Date, OrderVersion> orderVersions;
	public static volatile ListAttribute<VersionHistory, OrderVersion> orderHourMilestones;
}
