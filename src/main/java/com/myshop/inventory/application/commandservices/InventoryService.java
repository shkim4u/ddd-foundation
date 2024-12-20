package com.myshop.inventory.application.commandservices;

import com.myshop.inventory.domain.model.aggregates.Inventory;
import com.myshop.inventory.domain.repository.InventoryRepository;
import com.myshop.order.command.domain.OrderPlacedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @EventListener
    @Transactional
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        event.getOrderLines().forEach(orderLine -> {
            Inventory inventory = inventoryRepository.findByProductId(orderLine.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("No inventory found for product: " + orderLine.getProductId()));
            inventory.removeStock(orderLine.getQuantity());
            inventoryRepository.save(inventory);
        });
    }
}
