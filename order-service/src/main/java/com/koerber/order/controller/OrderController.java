package com.koerber.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.koerber.order.dto.OrderRequest;
import com.koerber.order.dto.OrderResponse;
import com.koerber.order.service.OrderService;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public OrderResponse placeOrder(@Valid @RequestBody OrderRequest request) {
        return service.placeOrder(request);
    }
}
