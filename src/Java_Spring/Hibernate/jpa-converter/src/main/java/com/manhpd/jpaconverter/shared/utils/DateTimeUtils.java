package com.manhpd.jpaconverter.shared.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";

    public static String currentDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now(); //get current date time
        System.out.println("Current Time " + localDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String formatDateTime = localDateTime.format(formatter);

        return formatDateTime;
    }

    public static LocalDateTime fromDateTime(String now) {
        LocalDateTime formatDateTime = LocalDateTime.parse(now, DateTimeFormatter.ISO_DATE_TIME);
        return formatDateTime;
    }

    public static String fromLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String formatDateTime = localDateTime.format(formatter);

        return formatDateTime;
    }

    public static boolean inRange(LocalDateTime current, LocalDateTime beforeDt, LocalDateTime afterDt) {
        boolean isGreater = current.isAfter(beforeDt);
        boolean isLesser = current.isBefore(afterDt);

        return isGreater && isLesser;
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        Instant timestamp = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(timestamp,
                ZoneId.of(ZoneId.SHORT_IDS.get("PST")));

        return localDateTime;
    }
}
