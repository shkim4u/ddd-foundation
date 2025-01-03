package com.myshop.inventory.interfaces.eventhandlers;

import com.myshop.inventory.application.commandservices.InventoryCommandService;
import com.myshop.order.command.domain.OrderPlacedEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {
    private final InventoryCommandService inventoryCommandService;
    private final StreamBridge streamBridge;

    public OrderEventHandler(InventoryCommandService inventoryCommandService, StreamBridge streamBridge) {
        this.inventoryCommandService = inventoryCommandService;
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        inventoryCommandService.removeStock(event);
        // [2025-01-02] 김상현: (Experimental) 주문 이벤트를 RabbitMQ로 전송
        streamBridge.send("order-events-out-0", MessageBuilder.withPayload(event).build());
    }
}
