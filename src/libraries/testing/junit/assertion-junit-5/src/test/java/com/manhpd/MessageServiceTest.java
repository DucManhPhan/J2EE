package com.manhpd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class MessageServiceTest {

    @DisplayName("Test MessageService.get()")
    @Test
    public void testGetMessage() {
        Assertions.assertEquals("Hello JUnit 5", MessageService.getMessage());
    }

}
