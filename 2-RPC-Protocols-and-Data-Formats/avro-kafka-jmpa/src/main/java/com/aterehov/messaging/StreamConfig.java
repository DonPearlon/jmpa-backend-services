package com.aterehov.messaging;

import com.aterehov.schema.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class StreamConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamConfig.class);
    @Bean
    public Consumer<Order> orderConsumer() {
        return (order) -> LOGGER.info("!!! Order received: {} !!!", order);
    }
}
