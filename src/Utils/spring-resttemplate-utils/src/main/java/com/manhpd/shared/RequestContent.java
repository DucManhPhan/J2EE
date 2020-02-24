package com.manhpd.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

import java.util.Map;


@Data
@AllArgsConstructor
public class RequestContent {

    private String bodyData;

    private Map<String, String> headerParams;

    private String accessToken;

    private String tokenType;

    private Object updatedPartOfObject;

    private FilePart file;

}
