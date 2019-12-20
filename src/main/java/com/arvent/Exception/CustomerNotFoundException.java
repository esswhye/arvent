package com.arvent.Exception;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException( Long id) {
        super("Error " + id + " not found");
    }
}
