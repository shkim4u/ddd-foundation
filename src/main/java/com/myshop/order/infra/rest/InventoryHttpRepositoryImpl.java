package com.myshop.order.infra.rest;

import com.myshop.order.infra.rest.feign.InventoryFeignClient;
import com.myshop.order.query.domain.InventoryHttpRepository;
import com.myshop.order.query.view.InventoryView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InventoryHttpRepositoryImpl implements InventoryHttpRepository {
    private final InventoryFeignClient inventoryFeignClient;

    @Override
    public InventoryView getInventory(String productId) {
        return inventoryFeignClient.getInventory(productId);
    }
}
