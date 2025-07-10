package com.manhpd.client_service.api;

import com.manhpd.client_service.config.AccountKeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ClientConfigParamController {

    @Value("${account.prefix.name}")
    private String prefixAccountName;

    @Autowired
    private AccountKeyConfig accountKeyConfig;

    @GetMapping("/prefix-account-name")
    public ResponseEntity<String> getPrefixAccountName() {
        System.out.println("Prefix Acount Name: " + this.prefixAccountName);
        return ResponseEntity.ok(this.prefixAccountName);
    }

    @GetMapping("/account-key")
    public ResponseEntity<String> getAccountKey() {
        return ResponseEntity.ok(
                "Key 1: " + accountKeyConfig.getKey1() +
                        ", Key 2: " + accountKeyConfig.getKey2()
        );
    }
}
