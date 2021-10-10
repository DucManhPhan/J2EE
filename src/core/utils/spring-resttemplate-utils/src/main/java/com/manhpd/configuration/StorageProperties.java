package com.manhpd.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("storage")
@Data
public class StorageProperties {

    // upload directory for using with RestTemplate
    private String uploadDir;

    // download directory for using with RestTemplate
    private String downloadDir;

    private String uploadDirOther;

    private String downloadDirOther;

    private String downloadedFileName;

}