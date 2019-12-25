package com.arvent.Exception.CustomerException;

import com.arvent.Exception.ArventException;

public class CustomerNotFoundException extends ArventException {

    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException( Long id) {
        super("Error Customer id: " + id + " not found");
    }
}
