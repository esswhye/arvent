package com.arvent.Exception;

public class CustomerServiceException extends Exception {

    private static final long serialVersionUID = -470180507998010368L;

    public CustomerServiceException() {
        super();
    }

    public CustomerServiceException(final String message) {
        super(message);
    }
}
