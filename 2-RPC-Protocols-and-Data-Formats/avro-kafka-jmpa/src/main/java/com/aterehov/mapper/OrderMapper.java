package com.aterehov.mapper;

import com.aterehov.dto.OrderDto;
import com.aterehov.schema.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDto orderDto);

    @AfterMapping
    default void setOrderItems(@MappingTarget Order target, OrderDto source, @Context OrderItemMapper orderItemMapper) {
        target.setOrderItems(orderItemMapper.toEntity(source.getOrderItems()));
    }
}

