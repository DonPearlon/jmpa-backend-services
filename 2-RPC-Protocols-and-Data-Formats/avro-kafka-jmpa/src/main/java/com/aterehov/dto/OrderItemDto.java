package com.aterehov.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private int id;
    private String name;
    private double price;
}
