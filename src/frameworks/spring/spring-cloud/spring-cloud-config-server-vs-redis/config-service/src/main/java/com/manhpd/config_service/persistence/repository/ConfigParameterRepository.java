package com.manhpd.config_service.persistence.repository;

import com.manhpd.config_service.persistence.entity.ConfigParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConfigParameterRepository extends JpaRepository<ConfigParameter, Long> {
    List<ConfigParameter> findByServiceName(String serviceName);
    Optional<ConfigParameter> findByServiceNameAndKey(String serviceName, String key);
}
