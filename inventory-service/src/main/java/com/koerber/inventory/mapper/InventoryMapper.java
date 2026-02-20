package com.koerber.inventory.mapper;

import com.koerber.inventory.dto.BatchResponse;
import com.koerber.inventory.dto.InventoryResponse;
import com.koerber.inventory.entity.InventoryBatch;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryMapper {

    public static InventoryResponse toResponse(List<InventoryBatch> batches) {

        if (batches == null || batches.isEmpty()) {
            return InventoryResponse.builder()
                    .batches(List.of())
                    .build();
        }

        Long productId = batches.get(0).getProductId();
        String productName = batches.get(0).getProductName();

        List<BatchResponse> batchResponses = batches.stream()
                .map(batch -> BatchResponse.builder()
                        .batchId(batch.getBatchId())
                        .quantity(batch.getQuantity())
                        .expiryDate(batch.getExpiryDate())
                        .build())
                .collect(Collectors.toList());

        return InventoryResponse.builder()
                .productId(productId)
                .productName(productName)
                .batches(batchResponses)
                .build();
    }
}
