package com.manhpd.config_service.persistence.repository;

import com.manhpd.config_service.persistence.entity.ConfigParameter;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Environment findOne(String application, String profile, String label) {
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
}
