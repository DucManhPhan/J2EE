package com.manhpd.config_service.persistence.repository;

import com.manhpd.config_service.persistence.entity.ConfigParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JpaEnvironmentRepository implements EnvironmentRepository {

    @Autowired
    private ConfigParameterRepository configParameterRepository;

    @Override
    @Cacheable(value = "environments", key = "#application")
    public Environment findOne(String application, String profile, String label) {
        System.out.println("=== JpaEnvironmentRepository.findOne() ===");
        System.out.println("application: " + application);
        System.out.println("profile: " + profile);
        System.out.println("label: " + label);
        
        Environment env = new Environment(
            application,
            profile == null ? "default" : profile,
            label,
            null
        );

        List<ConfigParameter> params = configParameterRepository.findByServiceName(application);

        Map<String, String> properties = new HashMap<>();
        params.forEach(param -> {
            properties.put(param.getKey(), param.getValue());
        });

        env.add(new PropertySource(application, properties));
        return env;
    }

    @CacheEvict(value = "environments", key = "#application")
    public void evictCache(String application) {
        // Cache will be evicted automatically
    }
}
