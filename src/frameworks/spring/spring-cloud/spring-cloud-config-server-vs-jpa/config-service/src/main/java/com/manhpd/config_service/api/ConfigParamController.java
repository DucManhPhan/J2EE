package com.manhpd.config_service.api;

import com.manhpd.config_service.persistence.entity.ConfigParameter;
import com.manhpd.config_service.persistence.repository.ConfigParameterRepository;
import com.manhpd.config_service.persistence.repository.JpaEnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.BusBridge;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/config")
public class ConfigParamController {

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

        System.out.println("Refresh data in memory of config-service");
        PropertySource newPropertySource =
                this.environmentRepository.findOne(serviceName, null, null)
                        .getPropertySources().stream()
                        .filter(ps -> ps.getName().equals(serviceName))
                        .map(ps -> new MapPropertySource(serviceName, (Map<String, Object>) ps.getSource()))
                        .findFirst().orElseThrow();
        MutablePropertySources propertySources = this.environment.getPropertySources();
        if (propertySources.contains(serviceName)) {
            propertySources.replace(serviceName, newPropertySource);
        } else {
            propertySources.addFirst(newPropertySource);
        }
        this.refreshEndpoint.refresh();

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
}
