package com.manhpd.studentdashboard.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DbHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if (this.isDBHealthy()) {
            return Health.up()
                    .withDetail("External DB svc", "Healthy")
                    .build();
        }

        return Health.down()
                .withDetail("External DB svc", "Is Not-Healthy")
                .build();
    }

    // Mimics a call to an external service or DB
    private boolean isDBHealthy() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
