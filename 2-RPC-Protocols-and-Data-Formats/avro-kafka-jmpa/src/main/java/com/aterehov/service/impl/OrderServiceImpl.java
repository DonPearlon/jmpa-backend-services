package com.aterehov.service.impl;

import com.aterehov.dto.OrderDto;
import com.aterehov.dto.OrderItemDto;
import com.aterehov.mapper.OrderItemMapper;
import com.aterehov.mapper.OrderMapper;
import com.aterehov.schema.Order;
import com.aterehov.schema.OrderItem;
import com.aterehov.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final StreamBridge streamBridge;
    @Override
    public void saveOrder(OrderDto orderDto) {
        List<OrderItem>  list = OrderItemMapper.INSTANCE.toEntity(orderDto.getOrderItems());
        //Order order = OrderMapper.INSTANCE.toEntity(orderDto);
        //order.setOrderItems(list);
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomer(orderDto.getCustomer());
        order.setOrderItems(list);
        System.out.println("Dong");
        streamBridge.send("orderProducer-out-0", order);
    }
}
