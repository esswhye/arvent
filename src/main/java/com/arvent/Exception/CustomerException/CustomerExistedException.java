package com.arvent.Exception.CustomerException;

import com.arvent.Exception.ArventException;

public class CustomerExistedException extends ArventException {

    public CustomerExistedException() {
    }

    public CustomerExistedException( String userName) {
        super("Error User name: " + userName + " existed. Please use other username");
    }
}
