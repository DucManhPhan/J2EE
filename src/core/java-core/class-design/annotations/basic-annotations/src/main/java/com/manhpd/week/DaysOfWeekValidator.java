package com.manhpd.week;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class DaysOfWeekValidator<T> implements ConstraintValidator<DaysOfWeek, T> {

    private Set<DayOfWeek> validDays;

    @Override
    public void initialize(DaysOfWeek constraintAnnotation) {
        this.validDays = Arrays.stream(constraintAnnotation.days()).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(T t, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(t)) {
            return false;
        }

        LocalDate localDate = this.toLocalDate(t);
        boolean isValidDays =  validDays.contains(localDate.getDayOfWeek());

        return isValidDays;
    }

    protected abstract LocalDate toLocalDate(T value);
}
