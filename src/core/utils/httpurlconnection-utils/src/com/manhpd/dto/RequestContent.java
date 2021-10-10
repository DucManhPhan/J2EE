package com.manhpd.dto;

import com.manhpd.utils.Constant;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.Map;

@Data
@Builder
public class RequestContent {

    private Map<String, String> keyValueHeaders;

    @Builder.Default
    private String accessToken = Constant.ACCESS_TOKEN;

    @Builder.Default
    private String tokenType = Constant.DEFAULT_TOKEN_TYPE;

    private File uploadedFile;

    private String bodyData;

    private RequestType requestType;

    @Builder.Default
    private String contentType = Constant.JSON_CONTENT_TYPE;

}
