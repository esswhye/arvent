package com.arvent.Service;

import com.arvent.Entity.Customer;
import com.arvent.Entity.CustomerCreditCard;
import com.arvent.Entity.Order.Order;

public interface PaymentService {

    String createCustomerCreditCard (Customer customer);

    void chargeCreditCard(Order order);
}
