package com.arvent.Exception.ShoppingCartException;

import com.arvent.Exception.ArventException;

public class QuantityMoreThanProductQuantity extends ArventException {

    public QuantityMoreThanProductQuantity(Long productId) {
        super("Error Product id: " + productId + " Exceeding");
    }
}
