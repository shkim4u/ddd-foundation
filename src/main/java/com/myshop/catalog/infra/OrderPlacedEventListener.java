package com.myshop.catalog.infra;

import com.myshop.order.command.domain.OrderPlacedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderPlacedEventListener {
    @EventListener
    public void handle(OrderPlacedEvent event) {
        System.out.println("OrderPlacedEvent handled: " + event.getNumber());
        // TODO: 재고 차감 코드
        // 아니면, 다른 마이크로서비스에게 알린다.
        // 알리는 수단은 Kafka, RabbitMQ, HTTP 등이 있다.
    }
}
