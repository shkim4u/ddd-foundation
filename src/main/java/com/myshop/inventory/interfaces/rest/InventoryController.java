package com.myshop.inventory.interfaces.rest;

import com.myshop.inventory.application.queryservices.InventoryQueryService;
import com.myshop.inventory.domain.model.view.InventoryView;
import com.myshop.order.infra.rest.dto.AppObject;
import com.myshop.order.infra.rest.dto.InventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/inventory/v1")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryQueryService inventoryQueryService;

    @GetMapping(value = "/{productId}", consumes="application/json;charset=UTF-8")
    public ResponseEntity<AppObject<InventoryDto>> getInventoryWithJson(@PathVariable String productId) {
        return getInventory(productId);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<AppObject<InventoryDto>> getInventoryWithoutJson(@PathVariable String productId) {
        return getInventory(productId);
    }

    private ResponseEntity<AppObject<InventoryDto>> getInventory(String productId) {
        InventoryView inventoryView = inventoryQueryService.getInventory(productId);
        InventoryDto inventoryDto = new InventoryDto(
                inventoryView.getId(),
                inventoryView.getProductId(),
                inventoryView.getQuantity(),
                inventoryView.getPrice()
        );
        AppObject<InventoryDto> appObject = AppObject.appObject(inventoryDto);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(appObject);
    }

//    @GetMapping(value = "/{productId}", consumes="application/json;charset=UTF-8")
////    @GetMapping(value = "/{productId}")
//    public ResponseEntity<AppObject<InventoryDto>> getInventory(@PathVariable String productId) {
//        InventoryView inventoryView = inventoryQueryService.getInventory(productId);
//        InventoryDto inventoryDto = new InventoryDto(
//                inventoryView.getId(),
//                inventoryView.getProductId(),
//                inventoryView.getQuantity(),
//                inventoryView.getPrice()
//        );
//        AppObject<InventoryDto> appObject = AppObject.appObject(inventoryDto);
////        return ResponseEntity.ok(appObject);
//        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(appObject);
//    }
}