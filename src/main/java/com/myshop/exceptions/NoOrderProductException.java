package com.myshop.exceptions;

import lombok.Getter;

@Getter
public class NoOrderProductException extends RuntimeException {
    private String productId;

    public NoOrderProductException(String productId) {
        this.productId = productId;
    }

}
