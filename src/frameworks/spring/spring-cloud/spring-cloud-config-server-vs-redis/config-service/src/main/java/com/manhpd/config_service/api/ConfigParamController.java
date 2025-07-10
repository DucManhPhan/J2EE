package com.manhpd.config_service.api;

import com.manhpd.config_service.persistence.entity.ConfigParameter;
import com.manhpd.config_service.persistence.repository.ConfigParameterRepository;
import com.manhpd.config_service.persistence.repository.JpaEnvironmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

@RestController
@AllArgsConstructor
@RequestMapping("/config")
public class ConfigParamController {

    private final ConfigParameterRepository configParameterRepository;
    private final ConfigurableEnvironment environment;
    private final JpaEnvironmentRepository environmentRepository;
    private final CacheManager cacheManager;
    private final StringRedisTemplate redisTemplate;

    @PutMapping
    public ResponseEntity<String> updateConfigParameter(
            @RequestParam String serviceName,
            @RequestParam String key,
            @RequestParam String value,
            @RequestParam String status
    ) {
        System.out.println("Query config-parameter based on service-name: " + serviceName + ", and key: " + key);
        Optional<ConfigParameter> optConfigParameter = this.configParameterRepository.findByServiceNameAndKey(
                serviceName,
                key
        );

        System.out.println("Update config parameter in database");
        ConfigParameter updateOrNewConfigParameter = optConfigParameter.map(param -> {
            param.setValue(value);
            param.setStatus(status);
            return param;
        }).orElse(
            newConfigParameter(serviceName, key, value, status)
        );
        this.configParameterRepository.save(updateOrNewConfigParameter);

        System.out.println("Send events to Redis pub/sub");
        Long subscribers = this.redisTemplate.convertAndSend("config-parameters-channel", "refresh");
        System.out.println("Message sent to " + subscribers + " subscribers");
        
        if (subscribers == 0) {
            System.out.println("WARNING: No subscribers listening to config-parameters-channel");
        }

        return ResponseEntity.ok("Successfully update config parameter");
    }

    private ConfigParameter newConfigParameter(
            String serviceName,
            String key,
            String value,
            String status
    ) {
        ConfigParameter configParameter = new ConfigParameter();
        configParameter.setServiceName(serviceName);
        configParameter.setKey(key);
        configParameter.setValue(value);
        configParameter.setStatus(status);

        return configParameter;
    }

    @GetMapping("/cache/debug")
    public ResponseEntity<String> debugCache() {
        StringBuilder result = new StringBuilder("Cache Contents:\n");
        
        Cache cache = cacheManager.getCache("environments");
        if (cache != null) {
            result.append("Cache: environments\n");
            result.append("Cache implementation: ").append(cache.getClass().getSimpleName()).append("\n");
            
            if (cache instanceof ConcurrentMapCache) {
                ConcurrentMapCache mapCache = (ConcurrentMapCache) cache;
                ConcurrentMap<Object, Object> nativeCache = mapCache.getNativeCache();
                result.append("Cache size: ").append(nativeCache.size()).append("\n");
                
                nativeCache.forEach((key, value) -> {
                    result.append("Key: ").append(key).append("\n");
                    result.append("Value type: ").append(value.getClass().getSimpleName()).append("\n");
                    result.append("---\n");
                });
            }
        } else {
            result.append("Cache 'environments' not found\n");
        }
        
        return ResponseEntity.ok(result.toString());
    }

    @GetMapping("/cache/test/{application}")
    public ResponseEntity<String> testCache(@PathVariable String application) {
        // Trigger cache population
        this.environmentRepository.findOne(application, "default", null);
        return ResponseEntity.ok("Cache populated for: " + application);
    }

    @GetMapping("/redis/test")
    public ResponseEntity<String> testRedis() {
        try {
            // Test Redis connection
            redisTemplate.opsForValue().set("test-key", "test-value");
            String value = redisTemplate.opsForValue().get("test-key");
            
            // Test pub/sub
            Long subscribers = redisTemplate.convertAndSend("config-parameters-channel", "test-message");
            
            return ResponseEntity.ok("Redis OK - Value: " + value + ", Subscribers: " + subscribers);
        } catch (Exception e) {
            return ResponseEntity.ok("Redis Error: " + e.getMessage());
        }
    }

    @GetMapping("/redis/channels")
    public ResponseEntity<String> checkChannels() {
        try {
            // Simple approach - just return subscriber count
            Long subscribers = redisTemplate.convertAndSend("config-parameters-channel", "ping");
            return ResponseEntity.ok("Channel subscribers: " + subscribers);
        } catch (Exception e) {
            return ResponseEntity.ok("Error checking channels: " + e.getMessage());
        }
    }
}
