package com.myshop.inventory.domain.repository;

import com.myshop.catalog.command.domain.product.ProductId;
import com.myshop.inventory.domain.model.aggregates.Inventory;
import com.myshop.inventory.domain.model.aggregates.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
    Optional<Inventory> findByProductId(ProductId productId);
}
