package com.aterehov.actuator.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Service;

import java.util.Map;

@Endpoint(id = "spring-profile")
@Service
public class SpringProfileEndpoint {

    @Value("${spring.profiles.active:unknown}")
    private String activeProfile;

    @ReadOperation
    public Map<String, String> getActiveSpringProfile() {
        return Map.of("Active Spring Profile", activeProfile);
    }
}
