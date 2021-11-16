package com.manhpd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UpdateEmployeeStatus {

    private String errorCode;

    private String message;

}
