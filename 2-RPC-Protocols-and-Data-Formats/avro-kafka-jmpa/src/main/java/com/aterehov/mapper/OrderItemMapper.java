package com.aterehov.mapper;

import com.aterehov.dto.OrderItemDto;
import com.aterehov.schema.OrderItem;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Named("mapToOrderItem")
    OrderItem toEntity(OrderItemDto orderItemDto);

    @IterableMapping(qualifiedByName = "mapToOrderItem")
    List<OrderItem> toEntity(List<OrderItemDto> orderItemDtoList);
}
