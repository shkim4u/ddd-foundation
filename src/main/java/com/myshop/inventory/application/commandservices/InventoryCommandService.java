package com.myshop.inventory.application.commandservices;

import com.myshop.inventory.domain.model.aggregates.Inventory;
import com.myshop.inventory.domain.repository.InventoryRepository;
import com.myshop.order.command.domain.OrderPlacedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryCommandService {
    private final InventoryRepository inventoryRepository;

    public InventoryCommandService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void removeStock(OrderPlacedEvent event) {
        event.getOrderLines().forEach(orderLine -> {
            Inventory inventory = inventoryRepository.findByProductId(orderLine.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("No inventory found for product: " + orderLine.getProductId()));
            inventory.removeStock(orderLine.getQuantity());
            inventoryRepository.save(inventory);
        });
    }
}
