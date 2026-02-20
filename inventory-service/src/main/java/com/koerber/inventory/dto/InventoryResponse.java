package com.koerber.inventory.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InventoryResponse {

    private Long productId;
    private String productName;
    private List<BatchResponse> batches;
}
