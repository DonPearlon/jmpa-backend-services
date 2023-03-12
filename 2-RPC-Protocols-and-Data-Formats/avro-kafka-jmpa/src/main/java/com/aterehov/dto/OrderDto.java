package com.aterehov.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private int id;

    private int customerId;

    private String customer;

    List<OrderItemDto> orderItems;
}
