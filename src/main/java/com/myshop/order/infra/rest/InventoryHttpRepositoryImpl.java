package com.myshop.order.infra.rest;

import com.myshop.order.infra.rest.dto.AppObject;
import com.myshop.order.infra.rest.dto.InventoryDto;
import com.myshop.order.infra.rest.feign.InventoryFeignClient;
import com.myshop.order.query.domain.InventoryHttpRepository;
import com.myshop.order.query.view.InventoryView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InventoryHttpRepositoryImpl implements InventoryHttpRepository {
    private final InventoryFeignClient inventoryFeignClient;

    @Override
    public InventoryView getInventory(String productId) {
        ResponseEntity<AppObject<InventoryDto>> response = inventoryFeignClient.getInventory(productId);
        AppObject<InventoryDto> responseBody = response.getBody();
        InventoryDto inventoryDto = responseBody.getApp();
        // Return InventoryView object using builder pattern
        return InventoryView.builder()
                .id(inventoryDto.getId())
                .productId(inventoryDto.getProductId())
                .quantity(inventoryDto.getQuantity())
                .price(inventoryDto.getPrice())
                .build();
    }
}
