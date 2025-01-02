package com.myshop.order.query.domain.service;

import com.myshop.catalog.command.domain.product.ProductId;
import com.myshop.catalog.query.product.ProductData;
import com.myshop.catalog.query.product.ProductDataDao;
import com.myshop.exceptions.NoOrderProductException;
import com.myshop.order.command.domain.PlaceOrderCommand;
import com.myshop.order.query.domain.InventoryHttpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 주문에 관련된 공통 비즈니스 로직을 처리하는 도메인 서비스 클래스.
 * Aggregate에 속하지 않는 도메인 로직을 처리합니다.
 */
@Component
@RequiredArgsConstructor
public class OrderDomainService {
    private final ProductDataDao productDataDao;
    private final InventoryHttpRepository inventoryHttpRepository;

    /**
     * [2025-01-03] 주문 가능 여부를 확인합니다.
     * (주의) 주문 가능 여부를 확인하면서 placeOrderCommand에 상품의 재고 수량을 설정하는 Side-effect가 발생합니다.
     * @param placeOrderCommand
     * @return 주문 가능 여부
     */
    public OrderCheckResult canPlaceOrder(PlaceOrderCommand placeOrderCommand) {
        Assert.notNull(placeOrderCommand, "OrderRequest must not be null");
        // 주문 대상 상품에 대해서 상품 정보를 조회한다.
        List<ProductData> products = placeOrderCommand.getOrderProducts()
            .stream()
            .map(op -> productDataDao.findById(new ProductId(op.getProductId()))
                .orElseThrow(() -> new NoOrderProductException(op.getProductId()))
            ).toList();

        // 일단 모든 상품에 재고가 존재하는지 확인한다. 동시에 재고 수량을 설정한다.
        boolean canPlaceOrder = products.stream()
                .peek(product -> product.setInventoryQuantity(inventoryHttpRepository.getInventory(product.getId().getId()).getQuantity()))
                .allMatch(product -> product.getInventoryQuantity() >= 1);

        // 또한 주문 수량이 재고 수량보다 작거나 같아야 한다.
        canPlaceOrder = canPlaceOrder && placeOrderCommand.getOrderProducts().stream()
                .allMatch(op -> products.stream()
                        .filter(product -> product.getId().getId().equals(op.getProductId()))
                        .allMatch(product -> product.getInventoryQuantity() >= op.getQuantity()));

        return OrderCheckResult.builder().products(products).canPlaceOrder(canPlaceOrder).build();
    }
}
