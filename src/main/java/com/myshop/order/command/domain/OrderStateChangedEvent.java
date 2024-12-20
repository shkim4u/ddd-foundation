package com.myshop.order.command.domain;

import com.myshop.common.event.Event;

public class OrderStateChangedEvent extends Event {
    private String orderNumber;
    private final OrderState newState;

    public OrderStateChangedEvent(String number, OrderState newState) {
        super();
        this.orderNumber = number;
        this.newState = newState;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
