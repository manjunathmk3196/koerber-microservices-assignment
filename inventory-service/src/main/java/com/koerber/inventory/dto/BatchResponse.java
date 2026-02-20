package com.koerber.inventory.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BatchResponse {

    private Long batchId;
    private Integer quantity;
    private LocalDate expiryDate;
}
