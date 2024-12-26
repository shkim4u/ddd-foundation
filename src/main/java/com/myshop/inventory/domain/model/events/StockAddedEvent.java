package com.myshop.inventory.domain.model.events;

import com.myshop.inventory.domain.model.aggregates.InventoryId;

public class StockAddedEvent {
    private final InventoryId inventoryId;
    private final int amount;

    public StockAddedEvent(InventoryId inventoryId, int amount) {
        this.inventoryId = inventoryId;
        this.amount = amount;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public int getAmount() {
        return amount;
    }
}
