package com.manhpd.validation;

import org.apache.commons.httpclient.HttpStatus;

import javax.validation.*;
import java.sql.Timestamp;
import java.util.*;

public class BeanValidation {

    public void validate(Object validatee) {
        System.out.println("BeanValidation requestentityDTO:::"+ validatee);
        Configuration<?> configuration = Validation.byDefaultProvider().providerResolver(
                new HibernateValidationProviderResolver()
        ).configure();
        ValidatorFactory factory = configuration.buildValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(validatee);
        System.out.println("constraintViolations:size:::" + constraintViolations.size());
        if(constraintViolations.size() > 0) {
            ValidationErrorDto validationErrorDTO = new ValidationErrorDto();
            System.out.println("constraintViolations::"+constraintViolations);

            Timestamp ts = new Timestamp(new Date().getTime());
            validationErrorDTO.setTimeStamp(String.valueOf(ts));
            validationErrorDTO.setStatusCode(HttpStatus.SC_BAD_REQUEST);

            Iterator<ConstraintViolation<Object>> iterator = constraintViolations.iterator();
            List<ValidationErrorDto.FieldErrorDto> fieldErrorsList = new ArrayList<>();
            while(iterator.hasNext()){
                ConstraintViolation<Object> cv = iterator.next();
                ValidationErrorDto.FieldErrorDto fieldErrorDTO = new ValidationErrorDto.FieldErrorDto(String.valueOf(cv.getPropertyPath()), cv.getMessage());
                fieldErrorsList.add(fieldErrorDTO);
            }

            validationErrorDTO.setFieldErrors(fieldErrorsList);
            throw new ValidationException(" Validation fail for bean : "+ validatee.getClass());
        }

    }

}
