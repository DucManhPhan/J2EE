package com.manhpd.helloworld.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonPropertyOrder({ "timeStamp", "statusCode", "fieldErrors"})
public class ValidationErrorDto {

    @JsonProperty("fieldErrors")
    private List<FieldErrorDto> fieldErrors;

    @JsonProperty("timeStamp")
    private String timeStamp;

    @JsonProperty("statusCode")
    private Integer statusCode;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldErrorDto{

        private String field;

        private String message;
    }

}