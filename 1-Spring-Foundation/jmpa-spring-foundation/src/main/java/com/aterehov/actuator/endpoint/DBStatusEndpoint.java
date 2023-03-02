package com.aterehov.actuator.endpoint;

import com.aterehov.service.DBHealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Service;

import java.util.Map;

@Endpoint(id = "db-status")
@Service
@RequiredArgsConstructor
public class DBStatusEndpoint {

    private final DBHealthCheckService dbHealthCheckService;

    @ReadOperation
    public Map<String, String> getDBStatusEndpoint() {
        String status = "DOWN";
        if (dbHealthCheckService.isHealthy()) {
            status = "UP";
        }
        return Map.of("Status", status);
    }
}
