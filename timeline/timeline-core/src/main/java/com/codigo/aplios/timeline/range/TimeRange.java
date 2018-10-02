package com.codigo.aplios.timeline.range;

import com.codigo.aplios.core.compare.CompareObject;
import com.codigo.aplios.core.compare.CompareResult;
import com.codigo.aplios.timeline.common.IDurationProvider;
import com.codigo.aplios.timeline.common.ITimeFormatter;
import com.codigo.aplios.timeline.common.ITimePeriodComparer;
import com.codigo.aplios.timeline.common.TimeSpec;
import com.codigo.aplios.timeline.period.ITimePeriod;
import com.codigo.aplios.timeline.period.PeriodRelation;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TimeRange
        implements ITimeRange {

    public static void main(final String[] args) {

        final ITimeRange tmRange = new TimeRange(LocalDateTime.now(), LocalDateTime.now(), false);
        tmRange.setStart(LocalDateTime.now()
                .plusDays(-1L));
    }

    /**
     * Atrybut określa całkowitą możliwą przestrzeń czasową wykorzystywaną do definicji przedział okresu trwania
     */
    public static final TimeRange ANYTIME = new TimeRange(true);

    /**
     * Atrybut określa czy stan obiektu jeste niezmienny (Immutable)
     */
    private final boolean isReadOnly;

    /**
     * Atrybut określa punkt początku okresu trwania
     */
    private LocalDateTime start;

    /**
     * Atrybut określa punkt końca okresu trwania
     */
    private LocalDateTime end;

    /**
     * Podstawowy konstruktor obiektu klasy <code>TimeRange</code>
     *
     * @category constructor
     */
    public TimeRange() {

        this(TimeSpec.MinPeriodDate, TimeSpec.MaxPeriodDate, false);
    }

    /**
     * Podstawowy konstruktor obiektu klasy <code>TimeRange</code>
     *
     * @category constructor
     * @param isReadOnly
     *                   parametr wskazuje czy stan obiektu ma być niezmiennny (Immutable)
     */
    TimeRange(final boolean isReadOnly) {

        this(TimeSpec.MinPeriodDate, TimeSpec.MaxPeriodDate, isReadOnly);
    }

    /**
     * Podstawowy konstruktor obiektu klasy <code>TimeRange</code>
     *
     * @category constructor
     * @param moment
     *                   parametr wskazuje datę, która będzie stanowić okres jednodniowy (początek i koniec okresu bedzie taki sam)
     * @param isReadOnly
     *                   parametr wskazuje czy stan obiektu ma być niezmiennny (Immutable)
     */
    public TimeRange(final LocalDateTime moment, final boolean isReadOnly) {

        this(moment, moment, isReadOnly);
    }

    /**
     * Podstawowy konstruktor obiektu klasy <code>TimeRange</code>
     *
     * @category constructor
     * @param start
     *                   parametr wskazuje początek okresu
     * @param end
     *                   parametr wskazuje koniec okresu
     * @param isReadOnly
     *                   parametr wskazuje czy stan obiektu ma być niezmiennny (Immutable)
     */
    public TimeRange(final LocalDateTime start, final LocalDateTime end, final boolean isReadOnly) {

        this.isReadOnly = isReadOnly;

        if (CompareObject.EqualsGreaterThen.compare(end, start)) {
            this.start = start;
            this.end = end;
        } else {
            this.end = start;
            this.start = end;
        }
    }

    /**
     * Podstawowy konstruktor obiektu klasy <code>TimeRange</code>
     *
     * @category constructor
     * @param start
     *                   parametr wskazuje początek okresu
     * @param duration
     *                   parametr wskzuje okres, który będzie służył do wyznaczenia końca okresu
     * @param isReadOnly
     *                   parametr wskazuje czy stan obiektu ma być niezmiennny (Immutable)
     */
    public TimeRange(final LocalDateTime start, final Duration duration, final boolean isReadOnly) {

        this.isReadOnly = isReadOnly;

        if (CompareObject.EqualsGreaterThen.compare(duration, Duration.ZERO)) {
            this.start = start;
            this.end = this.start.plus(duration);
        } else {
            this.start = (this.start.plus(duration));
            this.end = start;
        }
    }

    /**
     * Podstawowy konstruktor obiektu klasy <code>TimeRange</code>. Konstruktor kopiujący.
     *
     * @category constructor
     * @param copy
     *             parametr wskazuje inny obiekt, z którego kopiujemy stan do naszego obiektu
     */
    public TimeRange(final ITimePeriod copy) {

        this(copy, false);
    }

    /**
     * Podstawowy konstruktor obiektu klasy <code>TimeRange</code>. Konstruktor kopiujący.
     *
     * @category constructor
     * @param copy
     *                   parametr wskazuje inny obiekt, z którego kopiujemy stan do naszego obiektu
     * @param isReadOnly
     *                   parametr wskazuje czy stan obiektu ma być niezmiennny (Immutable)
     */
    protected TimeRange(final ITimePeriod copy, final boolean isReadOnly) {

        if (Objects.isNull(copy))
            throw new NullPointerException("copy");

        this.isReadOnly = isReadOnly;
        this.start = copy.getStart();
        this.end = copy.getEnd();

    }

    protected void checkModification() {

        if (this.isReadOnly())
            throw new UnsupportedOperationException("TimeRange is read-only");
    }

    @Override
    public boolean hasStart() {

        return this.getStart()
                .compareTo(TimeSpec.MinPeriodDate) != CompareResult.EQUALS.result();
    }

    @Override
    public String getDurationDescription() {

        // TimeFormatter.Instance.GetDuration( Duration, DurationFormatType.Detailed );
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isMoment() {

        return this.getStart()
                .equals(this.getEnd());
    }

    @Override
    public boolean isAnytime() {

        return !this.hasStart() && !this.hasEnd();
    }

    @Override
    public boolean isReadOnly() {

        return this.isReadOnly;
    }

    @Override
    public Period getDuration(final IDurationProvider provider) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setup(final LocalDateTime newStart, final LocalDateTime newEnd) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isSamePeriod(@NonNull final ITimePeriod test) {

        return (this.getStart()
                .compareTo(test.getStart()) == CompareResult.EQUALS.result()) &&
                (this.getEnd()
                        .compareTo(test.getEnd()) == CompareResult.EQUALS.result());
    }

    @Override
    public boolean hasInside(final LocalDateTime test) {
        // public virtual bool HasInside( DateTime test )
        // {
        // return TimePeriodCalc.HasInside( this, test );
        // } // HasInside
        //

        return false;
    }

    @Override
    public boolean hasInside(@NonNull final ITimePeriod test) {

        // public virtual bool HasInside( ITimePeriod test )
        // {
        // if ( test == null )
        // {
        // throw new ArgumentNullException( "test" );
        // }
        // return TimePeriodCalc.HasInside( this, test );
        // } // HasInside
        return false;
    }

    @Override
    public boolean intersectsWith(@NonNull final ITimePeriod test) {

        // return TimePeriodCalc.IntersectsWith( this, test );
        return false;
    }

    @Override
    public boolean overlapsWith(final ITimePeriod test) {

        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public PeriodRelation getRelation(final ITimePeriod test) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int compareTo(final ITimePeriod other, final ITimePeriodComparer comparer) {

        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getDescription(final ITimeFormatter formatter) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalDateTime getStart() {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasEnd() {

        return this.getEnd()
                .compareTo(TimeSpec.MaxPeriodDate) != CompareResult.EQUALS.result();
    }

    @Override
    public LocalDateTime getEnd() {

        return this.end;
    }

    @Override
    public Period getDuration() {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void move(final Period offset) {
        // TODO Auto-generated method stub

    }

    @Override
    public void expandStartTo(final LocalDateTime moment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void expandEndTo(final LocalDateTime moment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void expandTo(final LocalDateTime moment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void expandTo(final ITimePeriod period) {
        // TODO Auto-generated method stub

    }

    @Override
    public void shrinkStartTo(final LocalDateTime moment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void shrinkEndTo(final LocalDateTime moment) {

        this.checkModification();
        if (this.hasInside(moment) && (this.end.compareTo(moment) == CompareResult.GREATER.result()))
            this.end = moment;
    }

    @Override
    public void shrinkTo(@NonNull final ITimePeriod period) {

        // ShrinkStartTo( period.Start );
        // ShrinkEndTo( period.End );
    }

    @Override
    public ITimeRange copy(final Period offset) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ITimeRange getIntersection(@NonNull final ITimePeriod period) {

        if (!this.intersectsWith(period))
            return null;

        final LocalDateTime periodStart = period.getStart(), periodEnd = period.getEnd();
        return new TimeRange(
                periodStart.compareTo(this.start) > CompareResult.GREATER.result() ? periodStart : this.start,
                periodEnd.compareTo(this.end) == CompareResult.LESSER.result() ? periodEnd : this.end,
                this.isReadOnly());
    }

    @Override
    public void setStart(final LocalDateTime start) {

        this.checkModification();
        if (CompareObject.GreaterThen.compare(start, this.end))
            throw new IllegalArgumentException("start");

        this.start = start;

    }

    @Override
    public void setEnd(final LocalDateTime end) {

        this.checkModification();
        if (end.isBefore(this.start))
            throw new IllegalArgumentException("end");
        this.end = end;
    }

}

// ------------------------------------------------------------------------
// public class TimeRange : ITimeRange
// {
//
// // ----------------------------------------------------------------------
// public static readonly TimeRange Anytime = new TimeRange( true );
//
// // ----------------------------------------------------------------------
// public TimeRange() :
// this( TimeSpec.MinPeriodDate, TimeSpec.MaxPeriodDate )
// {
// } // TimeRange
//
// // ----------------------------------------------------------------------
// internal TimeRange( bool isReadOnly = false ) :
// this( TimeSpec.MinPeriodDate, TimeSpec.MaxPeriodDate, isReadOnly )
// {
// } // TimeRange
//
// // ----------------------------------------------------------------------
// public TimeRange( DateTime moment, bool isReadOnly = false ) :
// this( moment, moment, isReadOnly )
// {
// } // TimeRange
//
// // ----------------------------------------------------------------------
// public TimeRange( DateTime start, DateTime end, bool isReadOnly = false )
// {
// if ( start <= end )
// {
// this.start = start;
// this.end = end;
// }
// else
// {
// this.end = start;
// this.start = end;
// }
// this.isReadOnly = isReadOnly;
// } // TimeRange
//
// // ----------------------------------------------------------------------
// public TimeRange( DateTime start, TimeSpan duration, bool isReadOnly = false )
// {
// if ( duration >= TimeSpan.Zero )
// {
// this.start = start;
// end = start.Add( duration );
// }
// else
// {
// this.start = start.Add( duration );
// end = start;
// }
// this.isReadOnly = isReadOnly;
// } // TimeRange
//
// // ----------------------------------------------------------------------
// public TimeRange( ITimePeriod copy )
// {
// if ( copy == null )
// {
// throw new ArgumentNullException( "copy" );
// }
// start = copy.Start;
// end = copy.End;
// isReadOnly = copy.IsReadOnly;
// } // TimeRange
//
// // ----------------------------------------------------------------------
// protected TimeRange( ITimePeriod copy, bool isReadOnly )
// {
// if ( copy == null )
// {
// throw new ArgumentNullException( "copy" );
// }
// start = copy.Start;
// end = copy.End;
// this.isReadOnly = isReadOnly;
// } // TimeRange
//
// // ----------------------------------------------------------------------
// public bool IsReadOnly
// {
// get { return isReadOnly; }
// } // IsReadOnly
//
// // ----------------------------------------------------------------------
// public bool IsAnytime
// {
// get { return !HasStart && !HasEnd; }
// } // IsAnytime
//
// // ----------------------------------------------------------------------
// public bool IsMoment
// {
// get { return start.Equals( end ); }
// } // IsMoment
//
// // ----------------------------------------------------------------------
// public bool HasStart
// {
// get { return start != TimeSpec.MinPeriodDate; }
// } // HasStart
//
// // ----------------------------------------------------------------------
// public DateTime Start
// {
// get { return start; }
// set
// {
// CheckModification();
// if ( value > end )
// {
// throw new ArgumentOutOfRangeException( "value" );
// }
// start = value;
// }
// } // Start
//
// // ----------------------------------------------------------------------
// public bool HasEnd
// {
// get { return end != TimeSpec.MaxPeriodDate; }
// } // HasEnd
//
// // ----------------------------------------------------------------------
// public DateTime End
// {
// get { return end; }
// set
// {
// CheckModification();
// if ( value < start )
// {
// throw new ArgumentOutOfRangeException( "value" );
// }
// end = value;
// }
// } // End
//
// // ----------------------------------------------------------------------
// public TimeSpan Duration
// {
// get { return end.Subtract( start ); }
// set
// {
// CheckModification();
// end = start.Add( value );
// }
// } // Duration
//
// // ----------------------------------------------------------------------
// public string DurationDescription
// {
// get { return TimeFormatter.Instance.GetDuration( Duration, DurationFormatType.Detailed ); }
// } // DurationDescription
//
// // ----------------------------------------------------------------------
// public virtual TimeSpan GetDuration( IDurationProvider provider )
// {
// if ( provider == null )
// {
// throw new ArgumentNullException( "provider" );
// }
// return provider.GetDuration( Start, End );
// } // GetDuration
//
// // ----------------------------------------------------------------------
// public virtual void Setup( DateTime newStart, DateTime newEnd )
// {
// CheckModification();
// if ( newStart <= newEnd )
// {
// start = newStart;
// end = newEnd;
// }
// else
// {
// end = newStart;
// start = newEnd;
// }
// } // Setup
//
// // ----------------------------------------------------------------------
// public virtual bool IsSamePeriod( ITimePeriod test )
// {
// if ( test == null )
// {
// throw new ArgumentNullException( "test" );
// }
// return start == test.Start && end == test.End;
// } // IsSamePeriod
//
// // ----------------------------------------------------------------------
// public virtual bool HasInside( DateTime test )
// {
// return TimePeriodCalc.HasInside( this, test );
// } // HasInside
//
// // ----------------------------------------------------------------------
// public virtual bool HasInside( ITimePeriod test )
// {
// if ( test == null )
// {
// throw new ArgumentNullException( "test" );
// }
// return TimePeriodCalc.HasInside( this, test );
// } // HasInside
//
// // ----------------------------------------------------------------------
// public ITimeRange Copy()
// {
// return Copy( TimeSpan.Zero );
// } // Copy
//
// // ----------------------------------------------------------------------
// public virtual ITimeRange Copy( TimeSpan offset )
// {
// return new TimeRange( start.Add( offset ), end.Add( offset ), IsReadOnly );
// } // Copy
//
// // ----------------------------------------------------------------------
// public virtual void Move( TimeSpan offset )
// {
// CheckModification();
// if ( offset == TimeSpan.Zero )
// {
// return;
// }
// start = start.Add( offset );
// end = end.Add( offset );
// } // Move
//
// // ----------------------------------------------------------------------
// public virtual void ExpandStartTo( DateTime moment )
// {
// CheckModification();
// if ( start > moment )
// {
// start = moment;
// }
// } // ExpandStartTo
//
// // ----------------------------------------------------------------------
// public virtual void ExpandEndTo( DateTime moment )
// {
// CheckModification();
// if ( end < moment )
// {
// end = moment;
// }
// } // ExpandEndTo
//
// // ----------------------------------------------------------------------
// public void ExpandTo( DateTime moment )
// {
// ExpandStartTo( moment );
// ExpandEndTo( moment );
// } // ExpandTo
//
// // ----------------------------------------------------------------------
// public void ExpandTo( ITimePeriod period )
// {
// if ( period == null )
// {
// throw new ArgumentNullException( "period" );
// }
// ExpandStartTo( period.Start );
// ExpandEndTo( period.End );
// } // ExpandTo
//
// // ----------------------------------------------------------------------
// public virtual void ShrinkStartTo( DateTime moment )
// {
// CheckModification();
// if ( HasInside( moment ) && start < moment )
// {
// start = moment;
// }
// } // ShrinkStartTo
//
// // ----------------------------------------------------------------------
// public virtual void ShrinkEndTo( DateTime moment )
// {
// CheckModification();
// if ( HasInside( moment ) && end > moment )
// {
// end = moment;
// }
// } // ShrinkEndTo
//
// // ----------------------------------------------------------------------
// public void ShrinkTo( ITimePeriod period )
// {
// if ( period == null )
// {
// throw new ArgumentNullException( "period" );
// }
// ShrinkStartTo( period.Start );
// ShrinkEndTo( period.End );
// } // ShrinkTo
//
// // ----------------------------------------------------------------------
// public virtual bool IntersectsWith( ITimePeriod test )
// {
// if ( test == null )
// {
// throw new ArgumentNullException( "test" );
// }
// return TimePeriodCalc.IntersectsWith( this, test );
// } // IntersectsWith
//
// // ----------------------------------------------------------------------
// public virtual ITimeRange GetIntersection( ITimePeriod period )
// {
// if ( period == null )
// {
// throw new ArgumentNullException( "period" );
// }
// if ( !IntersectsWith( period ) )
// {
// return null;
// }
// DateTime periodStart = period.Start;
// DateTime periodEnd = period.End;
// return new TimeRange(
// periodStart > start ? periodStart : start,
// periodEnd < end ? periodEnd : end,
// IsReadOnly );
// } // GetIntersection
//
// // ----------------------------------------------------------------------
// public virtual bool OverlapsWith( ITimePeriod test )
// {
// if ( test == null )
// {
// throw new ArgumentNullException( "test" );
// }
// return TimePeriodCalc.OverlapsWith( this, test );
// } // OverlapsWith
//
// // ----------------------------------------------------------------------
// public virtual PeriodRelation GetRelation( ITimePeriod test )
// {
// if ( test == null )
// {
// throw new ArgumentNullException( "test" );
// }
// return TimePeriodCalc.GetRelation( this, test );
// } // GetRelation
//
// // ----------------------------------------------------------------------
// public virtual int CompareTo( ITimePeriod other, ITimePeriodComparer comparer )
// {
// if ( other == null )
// {
// throw new ArgumentNullException( "other" );
// }
// if ( comparer == null )
// {
// throw new ArgumentNullException( "comparer" );
// }
// return comparer.Compare( this, other );
// } // CompareTo
//
// // ----------------------------------------------------------------------
// public virtual void Reset()
// {
// CheckModification();
// start = TimeSpec.MinPeriodDate;
// end = TimeSpec.MaxPeriodDate;
// } // Reset
//
// // ----------------------------------------------------------------------
// public string GetDescription( ITimeFormatter formatter = null )
// {
// return Format( formatter ?? TimeFormatter.Instance );
// } // GetDescription
//
// // ----------------------------------------------------------------------
// protected virtual string Format( ITimeFormatter formatter )
// {
// return formatter.GetPeriod( start, end, Duration );
// } // Format
//
// // ----------------------------------------------------------------------
// public override string ToString()
// {
// return GetDescription();
// } // ToString
//
// // ----------------------------------------------------------------------
// public sealed override bool Equals( object obj )
// {
// if ( obj == this )
// {
// return true;
// }
// if ( obj == null || GetType() != obj.GetType() )
// {
// return false;
// }
// return IsEqual( obj );
// } // Equals
//
// // ----------------------------------------------------------------------
// protected virtual bool IsEqual( object obj )
// {
// return HasSameData( obj as TimeRange );
// } // IsEqual
//
// // ----------------------------------------------------------------------
// private bool HasSameData( TimeRange comp )
// {
// return start == comp.start && end == comp.end && isReadOnly == comp.isReadOnly;
// } // HasSameData
//
// // ----------------------------------------------------------------------
// public sealed override int GetHashCode()
// {
// return HashTool.AddHashCode( GetType().GetHashCode(), ComputeHashCode() );
// } // GetHashCode
//
// // ----------------------------------------------------------------------
// protected virtual int ComputeHashCode()
// {
// return HashTool.ComputeHashCode( isReadOnly, start, end );
// } // ComputeHashCode
//
// // ----------------------------------------------------------------------
// protected void CheckModification()
// {
// if ( IsReadOnly )
// {
// throw new NotSupportedException( "TimeRange is read-only" );
// }
// } // CheckModification
//
// // ----------------------------------------------------------------------
// // members
// private readonly bool isReadOnly;
// private DateTime start;
// private DateTime end;
//
// } // class TimeRange
