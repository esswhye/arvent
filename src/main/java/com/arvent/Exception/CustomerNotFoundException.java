package com.arvent.Exception;

public class CustomerNotFoundException extends ArventException{

    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException( Long id) {
        super("Error Customer id: " + id + " not found");
    }
}
