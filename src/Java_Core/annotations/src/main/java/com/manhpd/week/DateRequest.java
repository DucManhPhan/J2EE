package com.manhpd.week;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

import static java.time.DayOfWeek.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateRequest {

    @DaysOfWeek(days = {SATURDAY, SUNDAY})
    private Date weekendDay;

    @DaysOfWeek(days = {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY})
    private LocalDate workingDay;

    public String toString() {
        return "Weekend day is: " + this.weekendDay.toString() + ", working day is: " + this.workingDay.toString();
    }

}
