package com.arvent.Exception.CustomerException;

import com.arvent.Exception.ArventException;

public class CustomerPasswordException extends ArventException {

    public CustomerPasswordException() {
    }

    public CustomerPasswordException(final String message) {
        super(message);
    }
}
