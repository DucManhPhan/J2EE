package com.manhpd.configuration;

import com.manhpd.domain.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean(name = "uploadWithRestTemplate")
    public FileSystemStorageService uploadWithRestTemplate() {
        return new FileSystemStorageService(storageProperties.getUploadDir());
    }

    @Bean(name = "downloadWithRestTemplate")
    public FileSystemStorageService downloadWithRestTemplate() {
        return new FileSystemStorageService(storageProperties.getDownloadDir());
    }

    @Bean(name = "uploadNormal")
    public FileSystemStorageService uploadNormal() {
        return new FileSystemStorageService(storageProperties.getUploadDirOther());
    }

    @Bean(name = "downloadNormal")
    public FileSystemStorageService downloadNormal() {
        return new FileSystemStorageService(storageProperties.getDownloadDirOther());
    }

}
