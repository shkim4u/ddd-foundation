package com.myshop.catalog.infra;

import com.myshop.order.command.domain.OrderPlacedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderPlacedEventListener {
    @EventListener
    public void handle(OrderPlacedEvent event) {
        System.out.println("OrderPlacedEvent handled: " + event.getNumber());
    }
}
