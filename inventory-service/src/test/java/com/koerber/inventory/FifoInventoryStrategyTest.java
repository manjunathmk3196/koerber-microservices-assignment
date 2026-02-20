package com.koerber.inventory;

import com.koerber.inventory.entity.InventoryBatch;
import com.koerber.inventory.exception.InsufficientStockException;
import com.koerber.inventory.repository.InventoryBatchRepository;
import com.koerber.inventory.service.InventoryService;
import com.koerber.inventory.service.factory.InventoryStrategyFactory;
import com.koerber.inventory.service.statergy.FifoInventoryStrategy;
import com.koerber.inventory.service.statergy.InventoryStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FifoInventoryStrategyTest {

    @Mock
    private InventoryBatchRepository repository;

    @InjectMocks
    private FifoInventoryStrategy strategy;

    @Test
    void shouldReserveStockSuccessfully() {

        InventoryBatch batch = new InventoryBatch(
                1L, 1001L, "Laptop", 10, LocalDate.now()
        );

        when(repository.findByProductIdOrderByExpiryDateAsc(1001L))
                .thenReturn(List.of(batch));

        strategy.reserveStock(1001L, 5);

        assertEquals(5, batch.getQuantity());
        verify(repository, atLeastOnce()).save(batch);
    }

    @Test
    void shouldThrowExceptionWhenStockInsufficient() {

        InventoryBatch batch = new InventoryBatch(
                1L, 1001L, "Laptop", 2, LocalDate.now()
        );

        when(repository.findByProductIdOrderByExpiryDateAsc(1001L))
                .thenReturn(List.of(batch));

        assertThrows(InsufficientStockException.class,
                () -> strategy.reserveStock(1001L, 5));
    }
}
