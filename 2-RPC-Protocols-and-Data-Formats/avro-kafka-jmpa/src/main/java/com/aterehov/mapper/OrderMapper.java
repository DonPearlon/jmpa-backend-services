package com.aterehov.mapper;

import com.aterehov.dto.OrderDto;
import com.aterehov.schema.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntity(OrderDto productDto);
}

