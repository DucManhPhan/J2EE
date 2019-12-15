package com.manhpd.dto;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;


@Data
public class WorkPeriod {

    private LocalDateTime start;

    private LocalDateTime end;

    public WorkPeriod(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public WorkPeriod(LocalDateTime start, Duration duration) {
        this.start = start;
        this.end = start.plus(duration);
    }

}
