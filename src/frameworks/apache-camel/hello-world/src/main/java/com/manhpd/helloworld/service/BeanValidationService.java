package com.manhpd.helloworld.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manhpd.helloworld.dto.ResponseDto;
import com.manhpd.helloworld.dto.ValidationErrorDto;
import org.apache.camel.Exchange;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.sql.Timestamp;
import java.util.*;

@Component
public class BeanValidationService {

    private static final String NULL_STRING = "null";

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
            ValidationErrorDto validationErrorDto = new ValidationErrorDto();
            System.out.println("constraintViolations::" + constraintViolations);

            Timestamp ts = new Timestamp(new Date().getTime());
            validationErrorDto.setTimeStamp(String.valueOf(ts));
            validationErrorDto.setStatusCode(HttpStatus.SC_BAD_REQUEST);

            Iterator<ConstraintViolation<Object>> iterator = constraintViolations.iterator();
            List<ValidationErrorDto.FieldErrorDto> fieldErrorsList = new ArrayList<>();
            while(iterator.hasNext()){
                ConstraintViolation<Object> cv = iterator.next();
                ValidationErrorDto.FieldErrorDto fieldErrorDTO = new ValidationErrorDto.FieldErrorDto(String.valueOf(cv.getPropertyPath()), cv.getMessage());
                fieldErrorsList.add(fieldErrorDTO);
            }

            validationErrorDto.setFieldErrors(fieldErrorsList);
            throw new ValidationException(" Validation fail for bean : "+ validatee.getClass());
        }
    }

    public void getErrorValidation(Exchange exchange) {
        ValidationErrorDto dto = exchange.getProperty("validationErrorDto", ValidationErrorDto.class);
        final ObjectMapper objectMapper = new ObjectMapper();
        String validationResult = "";
        try {
            validationResult = objectMapper.writeValueAsString(dto);
            if (NULL_STRING.equals(validationResult) || StringUtils.isEmpty(validationResult)) {
                validationResult = "Invalid fields";
            }
        } catch (JsonProcessingException ex) {
            System.out.println("Error: " + ex);
        }

        exchange.getOut().setBody(new ResponseDto(HttpStatus.SC_BAD_REQUEST,
                                    validationResult));
    }

}
