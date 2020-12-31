package com.manhpd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LstParameter {

    private String paramId;

    private String systemId;

    private String appCode;

    private String paramName;

    private String paramValue;

    private String paramMeaning;

}
