package com.manhpd.vendingmachine.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OneOfValidator.class)
public @interface OneOf {

    String message() default "value must match one of the values: 5.0, 10.0, 25.0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    double[] value() default {};

}
