package com.myshop.order.query.application.outboundservices;

import com.myshop.order.query.domain.InventoryHttpRepository;
import com.myshop.order.query.view.InventoryView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalInventoryService {
    private final InventoryHttpRepository inventoryHttpRepository;

    public InventoryView getInventory(String productId) {
        return inventoryHttpRepository.getInventory(productId);
    }
}
