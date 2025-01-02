package com.myshop.order.command.application;

import com.myshop.common.ValidationError;
import com.myshop.order.command.domain.PlaceOrderCommand;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderRequestValidator {
    public List<ValidationError> validate(PlaceOrderCommand placeOrderCommand) {
        List<ValidationError> errors = new ArrayList<>();
        if (placeOrderCommand == null) {
            errors.add(ValidationError.of("required"));
        } else {
            if (placeOrderCommand.getOrdererMemberId() == null)
                errors.add(ValidationError.of("ordererMemberId", "required"));
            if (placeOrderCommand.getOrderProducts() == null)
                errors.add(ValidationError.of("orderProducts", "required"));
            if (placeOrderCommand.getOrderProducts().isEmpty())
                errors.add(ValidationError.of("orderProducts", "required"));

            if (placeOrderCommand.getShippingInfo() == null) {
                errors.add(ValidationError.of("shippingInfo", "required"));
            } else {
                if (placeOrderCommand.getShippingInfo().getReceiver() == null) {
                    errors.add(ValidationError.of("shippingInfo.receiver", "required"));
                } else {
                    if (!StringUtils.hasText(placeOrderCommand.getShippingInfo().getReceiver().getName())) {
                        errors.add(ValidationError.of("shippingInfo.receiver.name", "required"));
                    }
                    if (!StringUtils.hasText(placeOrderCommand.getShippingInfo().getReceiver().getPhone())) {
                        errors.add(ValidationError.of("shippingInfo.receiver.phone", "required"));
                    }
                    if (placeOrderCommand.getShippingInfo().getAddress() == null) {
                        errors.add(ValidationError.of("shippingInfo.address", "required"));
                    } else {
                        if (!StringUtils.hasText(placeOrderCommand.getShippingInfo().getAddress().getZipCode())) {
                            errors.add(ValidationError.of("shippingInfo.address.zipCode", "required"));
                        }
                        if (!StringUtils.hasText(placeOrderCommand.getShippingInfo().getAddress().getAddress1())) {
                            errors.add(ValidationError.of("shippingInfo.address.address1", "required"));
                        }
                        if (!StringUtils.hasText(placeOrderCommand.getShippingInfo().getAddress().getAddress2())) {
                            errors.add(ValidationError.of("shippingInfo.address.address2", "required"));
                        }
                    }
                }
            }
        }
        return errors;
    }
}
