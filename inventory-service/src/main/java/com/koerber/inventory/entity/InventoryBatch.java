
package com.koerber.inventory.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="inventory_batch")
public class InventoryBatch {

    @Id
    private Long batchId;

    private Long productId;
    private String productName;
    private Integer quantity;
    private LocalDate expiryDate;
}
