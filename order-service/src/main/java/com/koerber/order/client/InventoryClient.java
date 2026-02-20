package com.koerber.order.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final RestTemplate restTemplate;

    @Value("${inventory.service.url}")
    private String inventoryUrl;

    public void updateInventory(Long productId, Integer quantity) {

        Map<String, Object> requestBody = Map.of(
                "productId", productId,
                "quantity", quantity
        );

        restTemplate.postForObject(inventoryUrl, requestBody, Void.class);
    }
}
