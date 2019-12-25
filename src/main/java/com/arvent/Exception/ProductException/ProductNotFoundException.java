package com.arvent.Exception.ProductException;

import com.arvent.Exception.ArventException;

public class ProductNotFoundException extends ArventException {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException( Long id) {
        super("Error Product id: " + id + " not found");
    }
}
