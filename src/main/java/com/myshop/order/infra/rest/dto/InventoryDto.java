package com.myshop.order.infra.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    // 인벤토리 아이디
    private String id;
    // 상품 아이디
    private String productId;
    // 재고 수량
    private int quantity;
    // 입고 가격
    private int price;
}
