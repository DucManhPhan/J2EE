package com.manhpd.service;

import javassist.CtClass;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenMethodParams {

    private CtClass declaringClass;

    private String fieldName;

    private Class<?> fieldClass;

}
