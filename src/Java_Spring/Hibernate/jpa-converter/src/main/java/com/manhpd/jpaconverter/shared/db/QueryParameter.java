package com.manhpd.jpaconverter.shared.db;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryParameter {

    private String parameterName;

    private String parameterValue;

}
