package com.manhpd.annotations.validation.range;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;


@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class IntegerRangeValueValidator implements ConstraintValidator<IntegerRangeValue, Object[]> {

    private int min;

    private int max;

    @Override
    public void initialize(IntegerRangeValue integerRangeValue) {
        this.min = integerRangeValue.min();
        this.max = integerRangeValue.max();
    }

    @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext constraintValidatorContext) {
        if (!(objects[0] instanceof Integer)) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        if (this.min > this.max) {
            throw new IllegalArgumentException("Invalid range value");
        }

        int value = (Integer) objects[0];
        if (value < this.min || value > this.max) {
            return false;
        }

        return true;
    }

}
