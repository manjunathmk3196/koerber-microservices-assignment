package com.koerber.inventory;

import com.koerber.inventory.service.InventoryService;
import com.koerber.inventory.service.factory.InventoryStrategyFactory;
import com.koerber.inventory.service.statergy.InventoryStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

        @Mock
        private InventoryStrategyFactory factory;

        @Mock
        private InventoryStrategy strategy;

        @InjectMocks
        private InventoryService service;

        @Test
        void shouldDelegateToFactoryStrategy() {

            when(factory.getStrategy("FIFO")).thenReturn(strategy);

            service.updateInventory(1001L, 5);

            verify(strategy).reserveStock(1001L, 5);
        }
    }