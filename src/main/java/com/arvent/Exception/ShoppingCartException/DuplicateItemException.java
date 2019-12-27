package com.arvent.Exception.ShoppingCartException;

import java.sql.SQLIntegrityConstraintViolationException;

public class DuplicateItemException extends SQLIntegrityConstraintViolationException {

    public DuplicateItemException() {
    }

    public DuplicateItemException(Long productId, Long customerId) {
        super("Error Product id: " + productId + " existed in Customer :" + customerId);
    }
}
