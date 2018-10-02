package com.codigo.aplios.timeline.common;

import java.time.LocalDateTime;
import java.time.Period;

public interface IDurationProvider {

	Period GetDuration(LocalDateTime start, LocalDateTime end);

}