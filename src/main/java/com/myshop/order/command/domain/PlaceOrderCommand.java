package com.myshop.order.command.domain;

import com.myshop.member.command.domain.MemberId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlaceOrderCommand {
    private List<OrderProduct> orderProducts;
    private MemberId ordererMemberId;
    private ShippingInfo shippingInfo;
}
