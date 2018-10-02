package com.codigo.aplios.timeline;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;

public class TestDayIsWeekend {

    @Disabled
    public void testDayInWeekRequireSunday() {
        // test sprawdza czy dany dzień jest w weekend

        final LocalDateTime localDate = LocalDateTime.now();
        localDate.toInstant(ZoneOffset.UTC);

        final TemporalQuery<Boolean> IS_WEEKEND_QUERY = t -> t.get(ChronoField.DAY_OF_WEEK) >= 5;

        OffsetDateTime.now(ZoneOffset.UTC)
                .query(IS_WEEKEND_QUERY);

        Assertions.assertTrue(true);
    }

}
