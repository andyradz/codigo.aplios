package com.codigo.aplios.data.sort;

import com.codigo.aplios.core.junit.metrics.JUnitStopWatch;
import com.codigo.aplios.core.junit.rules.RepeatRule;
import org.junit.Rule;

public abstract class SorterBase {

    protected final static int REPEAT_TIMES = 10;

    protected final static boolean SHOW_LOG = true;

    protected SorterFactory<Object> sortable;

    @Rule
    public final JUnitStopWatch stopwatch;

    @Rule
    public final RepeatRule repeatRule;

    protected SorterBase() {

        this.stopwatch = new JUnitStopWatch();
        this.repeatRule = new RepeatRule();
    }

}
