package com.arvent.Controller;

import com.arvent.Entity.Customer;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Service.CustomerService;
import com.arvent.Service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("PaymentTest")
@AllArgsConstructor
public class PaymentController {

    private CustomerService customerService;

    private PaymentService paymentService;

    @GetMapping("/test")
    public ResponseEntity createCustomerCreditCard(@RequestHeader Long customerId) throws CustomerNotFoundException {
        Customer customer = customerService.findCustomerById(customerId);
        paymentService.createCustomerCreditCard(customer);
        return null;
    }


}
