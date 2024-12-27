package com.myshop.order.infra.rest.feign;

import com.myshop.order.query.view.InventoryView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 재고 서비스 Feign 클라이언트
 * TODO: Configuration을 통한 세부 설정 추가 - 에러 코드, 로그 레벨, 인터셉터, 디코더, Circuit Breaker, Fallback 등
 */
@FeignClient(name = "inventory", url = "${feign.client.config.inventory.endpoint}")
public interface InventoryFeignClient {
    @GetMapping("/inventory/v1/{productId}")
    InventoryView getInventory(@PathVariable("productId") String productId);
}
