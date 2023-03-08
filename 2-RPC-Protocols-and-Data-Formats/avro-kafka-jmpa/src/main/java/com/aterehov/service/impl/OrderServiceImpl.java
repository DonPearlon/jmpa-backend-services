package com.aterehov.service.impl;

import com.aterehov.dto.OrderDto;
import com.aterehov.mapper.OrderMapper;
import com.aterehov.schema.Order;
import com.aterehov.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final StreamBridge streamBridge;

    private final OrderMapper orderMapper;
    @Override
    public void saveOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Message<Order> message = MessageBuilder.withPayload(order)
                .setHeader(KafkaHeaders.KEY, order.getCustomer())
                .build();
        streamBridge.send("orderProducer-out-0", message);
    }
}
