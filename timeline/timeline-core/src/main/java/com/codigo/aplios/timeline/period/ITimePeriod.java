package com.codigo.aplios.timeline.period;

import com.codigo.aplios.timeline.common.IDurationProvider;
import com.codigo.aplios.timeline.common.ITimeFormatter;
import com.codigo.aplios.timeline.common.ITimePeriodComparer;
import java.time.LocalDateTime;
import java.time.Period;

public interface ITimePeriod {

    boolean hasStart();

    LocalDateTime getStart();

    void setStart(LocalDateTime start);

    boolean hasEnd();

    LocalDateTime getEnd();

    void setEnd(LocalDateTime end);

    Period getDuration();

    String getDurationDescription();

    boolean isMoment();

    boolean isAnytime();

    boolean isReadOnly();

    Period getDuration(IDurationProvider provider);

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
