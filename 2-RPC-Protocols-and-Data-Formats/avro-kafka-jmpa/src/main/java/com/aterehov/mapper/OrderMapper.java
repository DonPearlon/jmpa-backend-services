package com.aterehov.mapper;

import com.aterehov.dto.OrderDto;
import com.aterehov.schema.Order;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(target = "orderItems", ignore = true)
    })
    Order toEntity(OrderDto orderDto);

    @AfterMapping
    default void setOrderItems(@MappingTarget Order target, OrderDto source) {
        target.setOrderItems(OrderItemMapper.INSTANCE.toEntity(source.getOrderItems()));
    }
}

