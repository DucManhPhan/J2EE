package com.manhpd.config_service.api;

import com.manhpd.config_service.persistence.entity.ConfigParameter;
import com.manhpd.config_service.persistence.repository.ConfigParameterRepository;
import com.manhpd.config_service.persistence.repository.JpaEnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.bus.BusBridge;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cloud.config.environment.Environment;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/config")
public class ConfigParamController {

    private static final String CACHE_NAME = "config-environments";

    @Autowired
    private ConfigParameterRepository configParameterRepository;

    @Autowired
    private RefreshEndpoint refreshEndpoint;

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private JpaEnvironmentRepository environmentRepository;

    @Autowired
    private BusBridge busBridge;

    @Autowired
    private CacheManager cacheManager;

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

        System.out.println("Send refresh events to client-services through Spring Cloud Bus");
        this.busBridge.send(
                new RefreshRemoteApplicationEvent(this, "config-service", serviceName)
        );

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
        
        Cache cache = cacheManager.getCache(CACHE_NAME);
        if (cache != null) {
            result.append("Cache: config-environments\n");
            result.append("Cache implementation: ").append(cache.getClass().getSimpleName()).append("\n");
            
            if (cache instanceof ConcurrentMapCache) {
                ConcurrentMapCache mapCache = (ConcurrentMapCache) cache;
                ConcurrentMap<Object, Object> nativeCache = mapCache.getNativeCache();
                result.append("Cache size: ").append(nativeCache.size()).append("\n");
                
                nativeCache.forEach((key, value) -> {
                    result.append("Cache Key: ").append(key).append("\n");
                    
                    if (value instanceof Environment) {
                        Environment env = (Environment) value;
                        result.append("Application: ").append(env.getName()).append("\n");
                        result.append("Profiles: ").append(env.getProfiles()).append("\n");
                        result.append("Label: ").append(env.getLabel()).append("\n");
                        result.append("Properties:\n");
                        
                        env.getPropertySources().forEach(ps -> {
                            result.append("  PropertySource: ").append(ps.getName()).append("\n");
                            if (ps.getSource() instanceof Map) {
                                Map<String, Object> props = (Map<String, Object>) ps.getSource();
                                props.forEach((propKey, propValue) -> {
                                    result.append("    ").append(propKey).append(" = ").append(propValue).append("\n");
                                });
                            }
                        });
                    } else {
                        result.append("Value: ").append(value).append("\n");
                    }
                    result.append("---\n");
                });
            }
        } else {
            result.append("Cache '" + CACHE_NAME + "' not found\n");
        }
        
        return ResponseEntity.ok(result.toString());
    }

    @GetMapping("/cache/test/{application}")
    public ResponseEntity<String> testCache(@PathVariable String application) {
        // Trigger cache population
        environmentRepository.findOne(application, "default", null);
        return ResponseEntity.ok("Cache populated for: " + application);
    }
}
