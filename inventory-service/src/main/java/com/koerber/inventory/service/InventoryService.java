package com.koerber.inventory.service;

import com.koerber.inventory.dto.BatchResponse;
import com.koerber.inventory.exception.InsufficientStockException;
import com.koerber.inventory.service.factory.InventoryStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.koerber.inventory.repository.InventoryBatchRepository;
import com.koerber.inventory.entity.InventoryBatch;
import com.koerber.inventory.dto.InventoryResponse;
import com.koerber.inventory.mapper.InventoryMapper;

import java.util.List;
    @Service
    @RequiredArgsConstructor
    public class InventoryService {

        private final InventoryStrategyFactory strategyFactory;
        private final InventoryBatchRepository repository;

        public void updateInventory(Long productId, Integer quantity) {

            strategyFactory
                    .getStrategy("FIFO")
                    .reserveStock(productId, quantity);
        }

        public InventoryResponse getInventory(Long productId) {

            List<InventoryBatch> batches =
                    repository.findByProductIdOrderByExpiryDateAsc(productId);

            if (batches.isEmpty()) {
                throw new RuntimeException("Product not found");
            }

            String productName = batches.get(0).getProductName();

            List<BatchResponse> batchResponses = batches.stream()
                    .map(batch -> BatchResponse.builder()
                            .batchId(batch.getBatchId())
                            .quantity(batch.getQuantity())
                            .expiryDate(batch.getExpiryDate())
                            .build())
                    .toList();

            return InventoryResponse.builder()
                    .productId(productId)
                    .productName(productName)
                    .batches(batchResponses)
                    .build();
        }

    }

