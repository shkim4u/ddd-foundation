package com.myshop.order.ui;

import com.myshop.order.command.domain.OrderProduct;
import com.myshop.order.command.domain.PlaceOrderCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class OrderRequestValidator4Spring implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PlaceOrderCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PlaceOrderCommand placeOrderCommand = (PlaceOrderCommand) o;
        if (placeOrderCommand.getOrderProducts() == null || placeOrderCommand.getOrderProducts().isEmpty()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderProducts", "required");
        } else {
            for (int i = 0; i < placeOrderCommand.getOrderProducts().size(); i++) {
                OrderProduct orderProduct = placeOrderCommand.getOrderProducts().get(i);
                if (orderProduct.getProductId() == null || orderProduct.getProductId().trim().isEmpty()) {
                    errors.rejectValue("orderProducts[" + i + "].productId", "required");
                }
                if (orderProduct.getQuantity() <= 0) {
                    errors.rejectValue("orderProducts[" + i + "].quantity", "nonPositive");
                }
            }
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.receiver.name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.receiver.phone", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.zipCode", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.address1", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.address2", "required");
    }
}
