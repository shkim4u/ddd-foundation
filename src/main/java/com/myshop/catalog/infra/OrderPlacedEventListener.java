package com.myshop.catalog.infra;

import com.myshop.shareddomain.event.order.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderPlacedEventListener {
    private final StreamBridge streamBridge;

    @Autowired
    public OrderPlacedEventListener(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void handle(OrderPlacedEvent event) {
        log.info("Sending OrderPlacedEvent to the event stream bridge: {}", event);
        streamBridge.send("order-events-out-0", MessageBuilder.withPayload(event).build());
    }
}
