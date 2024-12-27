package com.myshop.inventory.interfaces.rest;

import com.myshop.inventory.application.queryservices.InventoryQueryService;
import com.myshop.inventory.domain.model.view.InventoryView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/inventory/v1")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryQueryService inventoryQueryService;

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InventoryView getInventory(@PathVariable String productId) {
        // Get inventory by productId.
        return inventoryQueryService.getInventory(productId);
    }
}
