package com.manhpd.validation;

import org.junit.jupiter.api.Test;

class BeanValidationTest {

    private BeanValidation validator;

    public BeanValidationTest() {
        this.validator = new BeanValidation();
    }

    @Test
    void checkNullParam() {
        Object validatee = null;
        this.validator.validate(validatee);
    }

    @Test
    void checkSuccessfulCase() {
        RequestDto dto = new RequestDto(null, "IWA-01");
        try {
            this.validator.validate(dto);
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
}