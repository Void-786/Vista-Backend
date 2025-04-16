package com.project.car_rental_services.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up()
                .withDetail("customCheck", "Railway deployment health check")
                .withDetail("description", "Custom health indicator for Railway deployment")
                .build();
    }
}