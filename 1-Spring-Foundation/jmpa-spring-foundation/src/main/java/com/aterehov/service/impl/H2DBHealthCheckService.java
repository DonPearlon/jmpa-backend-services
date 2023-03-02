package com.aterehov.service.impl;

import com.aterehov.service.DBHealthCheckService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class H2DBHealthCheckService implements DBHealthCheckService {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2DBHealthCheckService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isHealthy() {
        try {
            entityManager.createNativeQuery("SELECT 1").getSingleResult();
            return true;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }

    }

}
