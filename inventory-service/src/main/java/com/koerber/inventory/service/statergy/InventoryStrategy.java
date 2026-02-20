package com.koerber.inventory.service.statergy;

public interface InventoryStrategy {

    void reserveStock(Long productId, Integer quantity);
}
