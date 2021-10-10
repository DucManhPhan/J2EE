package com.manhpd.springasyncresttemplate.domain.domain_object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestContent {

    private String url;

    private String bodyData;

    private Map<String, String> headerParams;

    private String accessToken;

    private String tokenType;

    private Object updatedPartOfObject;

    // In order to send file to other server, we need to save that file in our local file system
    private Resource resource;

    private String downloadedFileName;

    // In Spring MVC, use MultipartFile type
    private MultipartFile file;

    // In Spring WebFlux, use FilePart type
//    private FilePart file;

}
