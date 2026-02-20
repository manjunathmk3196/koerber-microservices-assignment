package com.koerber.inventory.service.factory;

import com.koerber.inventory.service.statergy.InventoryStrategy;
//import com.koerber.inventory.service.strategy.InventoryStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class InventoryStrategyFactory {

    private final Map<String, InventoryStrategy> strategies;

    public InventoryStrategy getStrategy(String type) {

        InventoryStrategy strategy = strategies.get(type);

        if (strategy == null) {
            throw new IllegalArgumentException("Invalid strategy type");
        }

        return strategy;
    }
}
