package com.koerber.inventory.service.statergy;

import com.koerber.inventory.entity.InventoryBatch;
import com.koerber.inventory.exception.InsufficientStockException;
import com.koerber.inventory.repository.InventoryBatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("FIFO")
@RequiredArgsConstructor
public class FifoInventoryStrategy implements InventoryStrategy {

    private final InventoryBatchRepository repository;

    @Override
    public void reserveStock(Long productId, Integer quantity) {

        List<InventoryBatch> batches =
                repository.findByProductIdOrderByExpiryDateAsc(productId);

        int remaining = quantity;

        for (InventoryBatch batch : batches) {

            if (remaining <= 0) break;

            int available = batch.getQuantity();

            if (available > 0) {

                int deduct = Math.min(available, remaining);
                batch.setQuantity(available - deduct);
                remaining -= deduct;

                repository.save(batch);
            }
        }

        if (remaining > 0) {
            throw new InsufficientStockException("Not enough stock available");
        }
    }
}
