package com.codigo.aplios.timeline.interval;

import com.codigo.aplios.timeline.common.IntervalEdge;
import com.codigo.aplios.timeline.period.ITimePeriod;
import java.time.LocalDateTime;
import java.time.Period;

// ------------------------------------------------------------------------
public interface ITimeInterval
        extends ITimePeriod {

    boolean getIsStartOpen();

    boolean getIsEndOpen();

    boolean getIsOpen();

    boolean getIsStartClosed();

    boolean getIsEndClosed();

    boolean getIsClosed();

    boolean getIsEmpty();

    boolean getIsDegenerate();

    boolean getIsIntervalEnabled();

    LocalDateTime StartInterval();

    LocalDateTime getEndInterval();

    void setEndInterval(LocalDateTime endInterval);

    IntervalEdge getStartEdge();

    void setStartEdge(IntervalEdge intervaleEdge);

    IntervalEdge getEndEdge();

    void setEndEdge(IntervalEdge intervaleEdge);

    void Move(Period offset);

    void expandStartTo(LocalDateTime moment);

    void ExpandEndTo(LocalDateTime moment);

    void ExpandTo(LocalDateTime moment);

    void ExpandTo(ITimePeriod period);

    void ShrinkStartTo(LocalDateTime moment);

    void ShrinkEndTo(LocalDateTime moment);

    void ShrinkTo(ITimePeriod period);

    ITimeInterval Copy(Period offset);

}
