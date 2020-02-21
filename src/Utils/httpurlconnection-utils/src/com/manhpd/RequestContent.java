package com.manhpd;

import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.Map;

@Data
@Builder
public class RequestContent {

    private Map<String, String> keyValueHeaders;

    private String accessToken;

    private String tokenType;

    private File uploadedFile;

    private String bodyData;

}
