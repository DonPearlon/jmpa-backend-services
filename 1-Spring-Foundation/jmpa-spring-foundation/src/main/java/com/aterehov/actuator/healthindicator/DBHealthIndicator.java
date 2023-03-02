package com.aterehov.actuator.healthindicator;

import com.aterehov.service.DBHealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DBHealthIndicator implements HealthIndicator {
    private final DBHealthCheckService dbHealthCheckService;

    @Override
    public Health health() {
        if (dbHealthCheckService.isHealthy()) {
            return Health.up().withDetail("Database ", "is Healthy!").build();
        }

        return Health.up().withDetail("Database ", "is NOT Healthy!").build();
    }
}
