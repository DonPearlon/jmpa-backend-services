package com.aterehov.mapper;

import com.aterehov.dto.OrderItemDto;
import com.aterehov.schema.OrderItem;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    @Named("mapToOrderItem")
    OrderItem toEntity(OrderItemDto orderItemDto);

    @IterableMapping(qualifiedByName = "mapToOrderItem")
    List<OrderItem> toEntity(List<OrderItemDto> orderItemDtoList);
}
