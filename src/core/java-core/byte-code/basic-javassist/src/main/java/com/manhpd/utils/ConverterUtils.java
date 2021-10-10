package com.manhpd.utils;

import org.apache.commons.lang3.StringUtils;

public class ConverterUtils {

    public static String createMethodName(String actionMethod, String fieldName) {
        return new StringBuilder(actionMethod)
                        .append(StringUtils.capitalize(fieldName))
                        .toString();
    }

    public static String createSetterMethod(String fieldName, Class clazzArg) {
        String methodName = ConverterUtils.createMethodName(Constants.ACTION_SETTER_METHOD_NAME, fieldName);
        StringBuilder sb = new StringBuilder();
        sb.append("public void ").append(methodName).append("(")
                                 .append(clazzArg.getName()).append(" ").append(fieldName)
                                 .append(")").append("{")
                                 .append("this.").append(fieldName).append("=").append(fieldName).append(";")
                                 .append("}");
        return sb.toString();
    }

    public static String createGetterMethod(String fieldName, Class returnedClazz) {
        String getterName = ConverterUtils.createMethodName(Constants.ACTION_GETTER_METHOD_NAME, fieldName);
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append(returnedClazz.getName()).append(" ")
                            .append(getterName).append("(){")
                            .append("return this.")
                            .append(fieldName).append(";")
                            .append("}");

        return sb.toString();
    }

}
