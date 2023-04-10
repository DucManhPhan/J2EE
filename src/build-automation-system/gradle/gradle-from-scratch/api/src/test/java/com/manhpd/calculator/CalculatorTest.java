package com.manhpd.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void canAdd() {
        assertEquals(3, calculator.add(1, 2));
    }

    @Test
    void canSubtract() {
        assertEquals(1, calculator.add(2, 1));
    }

    @Test
    void canMultiply() {
        assertEquals(6, calculator.add(3, 2));
    }

    @Test
    void canDivide() {
        assertEquals(3, calculator.add(3, 1));
    }
}