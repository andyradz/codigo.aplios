package com.codigo.aplios.group.timeline.common.period;

import java.time.LocalDateTime;

import com.codigo.aplios.group.sdk.core.datetime.TimeSpan;
import com.codigo.aplios.group.sdk.core.value.Valuable;
import com.codigo.aplios.group.timeline.common.IDurationProvider;
import com.codigo.aplios.group.timeline.common.ITimeFormatter;
import com.codigo.aplios.group.timeline.common.ITimePeriodComparer;

public interface ITimePeriod {

	Valuable<Boolean> hasStart();

	Valuable<LocalDateTime> start();

	Valuable<Boolean> hasEnd();

	LocalDateTime getEnd();

	void setEnd(LocalDateTime end);

	String getDurationDescription();

	Valuable<Boolean> isMoment();

	Valuable<Boolean> isAnytime();

	Valuable<Boolean> isReadOnly();

	TimeSpan getDuration(IDurationProvider provider);

	void setup(LocalDateTime newStart, LocalDateTime newEnd);

	boolean isSamePeriod(ITimePeriod test);

	boolean hasInside(LocalDateTime test);

	boolean hasInside(ITimePeriod test);

	boolean intersectsWith(ITimePeriod test);

	boolean overlapsWith(ITimePeriod test);

	PeriodRelation getRelation(ITimePeriod test);

	int compareTo(ITimePeriod other, ITimePeriodComparer comparer);

	String getDescription(ITimeFormatter formatter);

}
