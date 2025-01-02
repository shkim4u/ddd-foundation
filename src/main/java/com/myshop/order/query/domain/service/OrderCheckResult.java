package com.myshop.order.query.domain.service;

import com.myshop.catalog.query.product.ProductData;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderCheckResult {
    private boolean canPlaceOrder;
    private List<ProductData> products;
}
