package com.manhpd.vendingmachine.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class OneOfValidator implements ConstraintValidator<OneOf, Double> {

    private static final List<Double> VALID_COIN_TYPES = new ArrayList<Double>() {{
        add(5.0);
        add(10.0);
        add(25.0);
    }};

    @Override
    public boolean isValid(Double coinField, ConstraintValidatorContext cxt) {
        return coinField != null && VALID_COIN_TYPES.contains(coinField);
    }
}
