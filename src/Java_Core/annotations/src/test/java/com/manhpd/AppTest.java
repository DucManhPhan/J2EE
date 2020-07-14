package com.manhpd;

import com.manhpd.week.DateRequest;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldAnswerWithTrue() {
        Date notWeekendDate = new Date();
        LocalDate notWorkingDate = LocalDate.of(2020, 7, 18);
        DateRequest request = new DateRequest(notWeekendDate, notWorkingDate);
        // when
        Set<ConstraintViolation<DateRequest>> violations = validator.validate(request);
        // then
        assertEquals(2, violations.size());
    }
}
