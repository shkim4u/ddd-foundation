package com.myshop.inventory.application.queryservices;

import com.myshop.catalog.command.domain.product.ProductId;
import com.myshop.inventory.domain.model.aggregates.Inventory;
import com.myshop.inventory.domain.model.view.InventoryView;
import com.myshop.inventory.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryQueryService {
    private final InventoryRepository inventoryRepository;

    public InventoryView getInventory(String productId) {
        Inventory inventory = inventoryRepository.findByProductId(ProductId.of(productId))
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for productId: " + productId));

        // Convert Inventory to InventoryView.
        return InventoryView.builder()
                .id(inventory.getId().getId())
                .productId(inventory.getProductId().getId())
                .quantity(inventory.getQuantity())
                .price(inventory.getPrice().getValue())
                .build();
    }
}
