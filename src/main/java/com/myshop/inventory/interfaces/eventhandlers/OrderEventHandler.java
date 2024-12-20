package com.myshop.inventory.interfaces.eventhandlers;

import com.myshop.inventory.application.commandservices.InventoryCommandService;
import com.myshop.order.command.domain.OrderPlacedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {
    private final InventoryCommandService inventoryCommandService;

    public OrderEventHandler(InventoryCommandService inventoryCommandService) {
        this.inventoryCommandService = inventoryCommandService;
    }

    @EventListener
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        inventoryCommandService.removeStock(event);
    }
}
