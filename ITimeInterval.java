package com.codigo.aplios.group.timeline.common.interval;

import java.time.LocalDateTime;

import com.codigo.aplios.group.sdk.core.datetime.TimeSpan;
import com.codigo.aplios.group.sdk.core.value.Valuable;
import com.codigo.aplios.group.timeline.common.IntervalEdge;
import com.codigo.aplios.group.timeline.common.period.ITimePeriod;

public interface ITimeInterval extends ITimePeriod {

	Valuable<Boolean> isStartOpen();

	Valuable<Boolean> isEndOpen();

	Valuable<Boolean> isOpen();

	Valuable<Boolean> isStartClosed();

	Valuable<Boolean> isEndClosed();

	Valuable<Boolean> isClosed();

	Valuable<Boolean> isEmpty();

	Valuable<Boolean> isDegenerate();

	Valuable<Boolean> isIntervalEnabled();

	LocalDateTime startInterval();

	LocalDateTime getEndInterval();

	void setEndInterval(LocalDateTime endInterval);

	IntervalEdge getStartEdge();

	void setStartEdge(IntervalEdge intervaleEdge);

	IntervalEdge getEndEdge();

	void setEndEdge(IntervalEdge intervaleEdge);

	void move(TimeSpan offset);

	void expandStartTo(LocalDateTime moment);

	void expandEndTo(LocalDateTime moment);

	void expandTo(LocalDateTime moment);

	void expandTo(ITimePeriod period);

	void shrinkStartTo(LocalDateTime moment);

	void shrinkEndTo(LocalDateTime moment);

	void shrinkTo(ITimePeriod period);

	ITimeInterval copy(TimeSpan offset);

}
