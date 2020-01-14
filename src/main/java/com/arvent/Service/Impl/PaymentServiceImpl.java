package com.arvent.Service.Impl;

import com.arvent.Entity.Customer;
import com.arvent.Entity.CustomerCreditCard;
import com.arvent.Entity.Order.Order;
import com.arvent.Service.CustomerService;
import com.arvent.Service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//https://www.codebyamir.com/blog/java-developers-guide-to-online-payments-with-stripe
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private CustomerService customerService;

    @Value("${stripe-properties.secret-key}")
    private String API_SECRET_KEY;

    public PaymentServiceImpl() {
        Stripe.apiKey = API_SECRET_KEY;
    }


    @Override
    public String createCustomerCreditCard(Customer customer) {

       Map<String, Object> customerParams = new HashMap<>();
       customerParams.put("description", customer.getFirstName()+ " " + customer.getLastName());
       customerParams.put("email", customer.getEmailAddress());



       String id = null;

       try{
           //create customer credit card

           com.stripe.model.Customer stripeCustomer = com.stripe.model.Customer.create(customerParams);
           id = stripeCustomer.getId();
           System.out.println(stripeCustomer);

       } catch (CardException e) {
           System.out.println("Status is: " + e.getCode());
           System.out.println("Message is: " + e.getMessage());
       } catch (RateLimitException e) {
           // Too many requests made to the API too quickly
           System.out.println("Status is: " + e.getCode());
           System.out.println("Message is: " + e.getMessage());
       } catch (InvalidRequestException e) {
           // Invalid parameters were supplied to Stripe's API
           System.out.println("Status is: " + e.getCode());
           System.out.println("Message is: " + e.getMessage());
       } catch (AuthenticationException e) {
           // Authentication with Stripe's API failed (wrong API key?)
           System.out.println("Status is: " + e.getCode());
           System.out.println("Message is: " + e.getMessage());
       } catch (ApiConnectionException e) {
           // Network communication with Stripe failed
           System.out.println("Status is: " + e.getCode());
           System.out.println("Message is: " + e.getMessage());
       } catch (StripeException e) {
           // Generic error
           System.out.println("Status is: " + e.getCode());
           System.out.println("Message is: " + e.getMessage());
       } catch (Exception e) {
           // Something else happened unrelated to Stripe
           System.out.println("Message is: " + e.getMessage());
       }

        CustomerCreditCard customerCreditCard = new CustomerCreditCard(0L,id,customer.getEmailAddress(),customer);

        customerService.createCustomerCreditCard(customerCreditCard);

        return id;
    }

    @Override
    public void chargeCreditCard(Order order) {

        int chargeAmountCents = (int) (order.getTotalCost() * 100);



    }
}
