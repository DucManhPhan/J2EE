package com.manhpd.service;

import com.manhpd.utils.ConverterUtils;
import javassist.CannotCompileException;
import javassist.CtMethod;

public class GeneratingMethods {

    public static CtMethod generate(MethodType type, GenMethodParams params) throws CannotCompileException {
        switch (type) {
            case GETTER_METHOD:
                return CtMethod.make(ConverterUtils.createGetterMethod(params.getFieldName(), params.getFieldClass()),
                                     params.getDeclaringClass());

            case SETTER_METHOD:
                return CtMethod.make(ConverterUtils.createSetterMethod(params.getFieldName(), params.getFieldClass()),
                                     params.getDeclaringClass());

            default:
                throw new RuntimeException("Do not exist the other method's type");
        }
    }

}
