package com.manhpd.vendingmachine.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationErrorResponseDto {

    private List<Violation> violations = new ArrayList<>();

}
