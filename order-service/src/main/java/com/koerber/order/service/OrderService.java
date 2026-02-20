package com.koerber.order.service;

import com.koerber.order.client.InventoryClient;
import com.koerber.order.dto.OrderRequest;
import com.koerber.order.dto.OrderResponse;
import com.koerber.order.entity.Order;
import com.koerber.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final InventoryClient inventoryClient;

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .status("PLACED")
                .createdAt(LocalDateTime.now())
                .build();

        order = repository.save(order);

        try {

            inventoryClient.updateInventory(
                    request.getProductId(),
                    request.getQuantity()
            );

            order.setStatus("PLACED");
            repository.save(order);

            return OrderResponse.builder()
                    .orderId(order.getId())
                    .productId(order.getProductId())
                    .productName(order.getProductName())
                    .quantity(order.getQuantity())
                    .status(order.getStatus())
                    .reservedFromBatchIds(List.of()) // optional simple implementation
                    .message("Order placed. Inventory reserved.")
                    .build();

        } catch (Exception ex) {

            order.setStatus("FAILED");
            repository.save(order);

            return OrderResponse.builder()
                    .orderId(order.getId())
                    .productId(order.getProductId())
                    .productName(order.getProductName())
                    .quantity(order.getQuantity())
                    .status(order.getStatus())
                    .reservedFromBatchIds(List.of())
                    .message("Order failed due to insufficient inventory.")
                    .build();
        }
    }


}
