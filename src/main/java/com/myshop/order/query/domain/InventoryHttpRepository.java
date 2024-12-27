package com.myshop.order.query.domain;

import com.myshop.order.query.view.InventoryView;

public interface InventoryHttpRepository {
    /**
     * 재고 조회한다.
     * @param productId 상품 ID
     * @return 재고 정보
     */
    InventoryView getInventory(String productId);
}
