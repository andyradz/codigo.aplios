package com.codigo.aplios.group.timeline.common;

import java.time.LocalDateTime;

import com.codigo.aplios.group.sdk.core.datetime.TimeSpan;

public interface IDurationProvider {

	TimeSpan getDuration(LocalDateTime start, LocalDateTime end);
}
