package com.manhpd.usingvavr.exceptionHandling.secondExample;

import io.vavr.control.Try;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamExceptionHandling {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private static final DateTimeFormatter alternateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public static void main(String[] args) {
//        processUsingOptional();
        processUsingTry();
    }

    private static void processUsingOptional() {
        Stream.of("12/31/2014", "01-01-2015",
                "12/31/2015", "not a date",
                "01/01/2016")
                .map(StreamExceptionHandling::parseDateWithOptional)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(DayOfWeek::from)
                .forEach(System.out::println);
    }

    private static void processUsingTry() {
        Stream.of("12/31/2014", "01-01-2015",
                "12/31/2015", "not a date",
                "01/01/2016")
                .map(StreamExceptionHandling::parseDateWithTry)
                .peek(v -> v.onFailure(t -> System.out.println("Failed due to " + t.getMessage())))
                .filter(Try::isSuccess)
                .map(Try::get)
                .map(DayOfWeek::from)
                .forEach(System.out::println);
    }

    private static void processUsingRecovery() {
        Stream.of("12/31/2014", "01-01-2015",
                "12/31/2015", "not a date",
                "01/01/2016")
                .map(StreamExceptionHandling::parseDateWithTry)
                .map(v -> v.recoverWith(e -> parseDateAlternate(((DateTimeParseException)e).getParsedString())))
                .peek(v -> v.onFailure(t -> System.out.println("Failed due to " + t.getMessage())))
                .filter(Try::isSuccess)
                .map(Try::get)
                .map(DayOfWeek::from)
                .forEach(System.out::println);

    }

    private LocalDate parseDateOrigin(String dateString) {
        return LocalDate.from(formatter.parse(dateString));
    }

    private static Optional<LocalDate> parseDateWithOptional(String dateString) {
        LocalDate localDate = null;
        try {
            localDate = LocalDate.from(formatter.parse(dateString));
        } catch (DateTimeParseException ex) {
            System.out.println(ex.getMessage());
        }

        return Optional.ofNullable(localDate);
    }

    private static Try<LocalDate> parseDateWithTry(String dateString) {
        return Try.of(() -> LocalDate.from(formatter.parse(dateString)));
    }

    private static Try<LocalDate> parseDateAlternate(String dateString) {
        return Try.of(() -> LocalDate.from(alternateFormatter.parse(dateString)));
    }

}
