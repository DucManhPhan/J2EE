package com.manhpd.utils;


import com.manhpd.dto.WorkPeriod;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Use java.time package in Java 8 or later
 *
 */
public class DateTimeUtils {

    public static List<WorkPeriod> generateWorkPeriods(List<LocalDate> days, LocalTime amStart, Duration amDuration
                                                                           , LocalTime pmStart, Duration pmDuration) {
        List<WorkPeriod> periods = new ArrayList<>();
        for (LocalDate d : days) {
            LocalDateTime thisAmStart = LocalDateTime.of(d, amStart);
            periods.add(new WorkPeriod(thisAmStart, thisAmStart.plus(amDuration)));
            LocalDateTime thisPmStart = LocalDateTime.of(d, pmStart);
            periods.add(new WorkPeriod(thisPmStart, thisPmStart.plus(pmDuration)));
        }

        return periods;
    }

    /**
     * LocalDateTime comparison methods
     * - isAfter(ChronoLocalDateTime<?>)
     * - isBefore(ChronoLocalDateTime<?>)
     * - isEqual(ChronoLocalDateTime<?>)
     * - compareTo(ChronoLocalDateTime<?>)
     *
     * ChronoLocalDateTime is a class that composes a LocalTime with a LocalDate in one of a number different chronologies
     * or calendar system; Japanese, Thai Buddihist and others.
     *
     * @param startTime
     * @param endTime
     * @param splitTime
     * @return
     */
    public static Optional<WorkPeriod> split(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime splitTime) {
        if (startTime.isBefore(splitTime) && splitTime.isBefore(endTime)) {
            WorkPeriod newPeriod = new WorkPeriod(startTime, Duration.between(startTime, splitTime));
            startTime = splitTime;
            return Optional.of(newPeriod);
        } else {
            return Optional.empty();
        }
    }


    /**
     * Conversion methods
     * - LocalDateTime -> LocalDate/Time: toLocalDate() and toLocalTime()
     * - LocalDate -> LocalDateTime: atStartOfDay(); atTime(int hour, int minute, int second, int nanos); and atTime(LocalTime)
     * - LocalTime -> LocalDateTime: atDate(LocalDate)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Optional<WorkPeriod> splitMidnight(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime midnight = startTime.toLocalDate().plusDays(1).atStartOfDay();
        return DateTimeUtils.split(startTime, endTime, midnight);
    }

    /**
     * Interconversion with String
     *
     */

    /**
     * to parse LocalDate in this format: "2018 Aug 23"
     * use a formatter:
     * DateTimeFormatter.ofPattern("yyyy' 'MMM' 'dd");
     *
     */
//    public static LocalDate parse() {
//        DateTimeFormatterBuilder dtfBuilder = new DateTimeFormatterBuilder()
//                .appendValue(YEAR, 4)
//                .appendLiteral(" ")
//                .appendText(MONTH_OF_YEAR, TextStyle.SHORT)
//                .appendLiteral(" ")
//                .appendValue(DAY_OF_MONTH, 2);
//
//        DateTimeFormatter formatter = dtfBuilder.toFormatter();
//    }

    /**
     * Interconversion with other JDK Date/Time classes
     *
     */


    /**
     * Interconversion with Database Persistence
     *
     */

}
