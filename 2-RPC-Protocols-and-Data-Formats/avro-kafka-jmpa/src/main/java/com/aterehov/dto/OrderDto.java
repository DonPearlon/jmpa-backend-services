package com.aterehov.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private int id;

    private int customerId;

    private String customer;

    List<OrderItemDto> orderItems;
}
