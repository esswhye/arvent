package com.arvent.Exception.ShoppingCartException;

import com.arvent.Exception.ArventException;

public class OutOfStockException extends ArventException {

    public OutOfStockException(Long productId) {
        super("Error Product id: " + productId + " Out of stock");
    }
}
