package com.manhpd.validation;

import org.junit.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationHelperTest {

    @Test
    public void isValidDateTest() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;

        assertTrue(ValidationHelper.isValidDate("20190228", dateFormatter));
        assertFalse(ValidationHelper.isValidDate("20190230", dateFormatter));
    }

}
