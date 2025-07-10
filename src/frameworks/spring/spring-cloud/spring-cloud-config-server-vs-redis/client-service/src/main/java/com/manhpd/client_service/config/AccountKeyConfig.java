package com.manhpd.client_service.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "acc")
@Data
public class AccountKeyConfig {
    private String key1;
    private String key2;
}
