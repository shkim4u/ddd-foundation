package com.myshop.catalog.infra;

import com.myshop.order.command.domain.OrderPlacedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderPlacedEventListener {
    private final StreamBridge streamBridge;

    @Autowired
    public OrderPlacedEventListener(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
//    @Async
    public void handle(OrderPlacedEvent event) {
        System.out.println("OrderPlacedEvent handled: " + event.getNumber());
    }
}
