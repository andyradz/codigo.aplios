package com.codigo.aplios.group.timeline.common.range;

import java.time.LocalDateTime;

import com.codigo.aplios.group.sdk.core.datetime.TimeSpan;
import com.codigo.aplios.group.timeline.common.period.ITimePeriod;

public interface ITimeRange extends ITimePeriod {

	void move(TimeSpan offset);

	void expandStartTo(LocalDateTime moment);

	void expandEndTo(LocalDateTime moment);

	void expandTo(LocalDateTime moment);

	void expandTo(ITimePeriod period);

	void shrinkStartTo(LocalDateTime moment);

	void shrinkEndTo(LocalDateTime moment);

	void shrinkTo(ITimePeriod period);

	ITimeRange copy(TimeSpan offset);

	ITimeRange getIntersection(ITimePeriod period);
}
