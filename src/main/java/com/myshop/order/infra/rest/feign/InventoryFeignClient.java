package com.myshop.order.infra.rest.feign;

import com.myshop.order.infra.rest.dto.AppObject;
import com.myshop.order.infra.rest.dto.InventoryDto;
import com.myshop.order.query.view.InventoryView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 재고 서비스 Feign 클라이언트
 * TODO: Configuration을 통한 세부 설정 추가 - 에러 코드, 로그 레벨, 인터셉터, 디코더, Circuit Breaker, Fallback 등
 */
@FeignClient(name = "inventory", url = "${feign.client.config.inventory.endpoint}", configuration = InventoryFeignConfig.class)
public interface InventoryFeignClient {
    @GetMapping(value = "/inventory/v1/{productId}")
//    @GetMapping(value = "/orders/order")
    ResponseEntity<AppObject<InventoryDto>> getInventory(@PathVariable("productId") String productId);
//    @RequestMapping(method = RequestMethod.GET, value = "/inventory/v1/{productId}", consumes = "application/json", produces = "application/json")
//    ResponseEntity<AppObject<InventoryDto>> getInventory(@PathVariable(value = "productId") String productId);
}