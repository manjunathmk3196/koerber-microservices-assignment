package com.koerber.inventory.controller;

import com.koerber.inventory.dto.InventoryResponse;
import com.koerber.inventory.dto.InventoryUpdateRequest;
import com.koerber.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getInventory(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                inventoryService.getInventory(productId)
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateInventory(
            @RequestBody InventoryUpdateRequest request) {

        inventoryService.updateInventory(
                request.getProductId(),
                request.getQuantity()
        );

        return ResponseEntity.ok().build();
    }
}
