package com.aterehov.controller;

import com.aterehov.dto.OrderDto;
import com.aterehov.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    private ResponseEntity<?> postOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return ResponseEntity.ok().build();
    }

}
