package com.manhpd.dto;

public enum RequestType {

    GET("GET"),

    POST("POST"),

    DELETE("DELETE"),

    PUT("PUT"),

    PATCH("PATCH");

    private String requestMethod;

    private RequestType(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

}
