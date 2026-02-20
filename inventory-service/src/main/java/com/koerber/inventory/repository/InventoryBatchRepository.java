
package com.koerber.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.koerber.inventory.entity.InventoryBatch;
import java.util.List;

public interface InventoryBatchRepository extends JpaRepository<InventoryBatch, Long> {
    List<InventoryBatch> findByProductIdOrderByExpiryDateAsc(Long productId);
}
