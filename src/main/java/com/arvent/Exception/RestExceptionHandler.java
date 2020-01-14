package com.arvent.Exception;

import com.arvent.Exception.CustomerException.CustomerExistedException;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.CustomerException.CustomerPasswordException;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Exception.ShoppingCartException.DuplicateItemException;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Exception.ShoppingCartException.QuantityMoreThanProductQuantity;
import com.stripe.exception.StripeException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //https://objectpartners.com/2014/10/21/logging-rest-exceptions-with-spring/

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable (HttpMessageNotReadableException ex , HttpHeaders headers, HttpStatus status, WebRequest webRequest)
    {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error , ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError)
    {
        return new ResponseEntity<>(apiError,apiError.getStatus());
    }

    @ExceptionHandler({CustomerNotFoundException.class,ProductNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(
            Exception ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({CustomerExistedException.class,CustomerPasswordException.class,DuplicateItemException.class,OutOfStockException.class, QuantityMoreThanProductQuantity.class})
    protected ResponseEntity<Object> handleEntityNotAcceptable(
            Exception ex) {
        ApiError apiError = new ApiError(NOT_ACCEPTABLE);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({StripeException.class})
    protected ResponseEntity<Object> handleEntityNotAcceptable(
            StripeException ex) {
        ApiError apiError = new ApiError(NOT_ACCEPTABLE);
        apiError.setMessage(ex.toString());
        return buildResponseEntity(apiError);
    }


    /*
    @ExceptionHandler(CustomerPasswordException.class)
    protected ResponseEntity<Object> handleEntityNotAcceptable(
            CustomerPasswordException ex) {
        ApiError apiError = new ApiError(NOT_ACCEPTABLE);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            ProductNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DuplicateItemException.class)
    protected ResponseEntity<Object> handleEntityNotAcceptable(
            DuplicateItemException ex) {
        ApiError apiError = new ApiError(NOT_ACCEPTABLE);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(OutOfStockException.class)
    protected ResponseEntity<Object> handleEntityNotAcceptable(
            OutOfStockException ex) {
        ApiError apiError = new ApiError(NOT_ACCEPTABLE);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    */
}
