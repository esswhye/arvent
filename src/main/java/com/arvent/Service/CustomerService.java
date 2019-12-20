package com.arvent.Service;

import com.arvent.DTO.CustomerDTO;
import com.arvent.Entity.Customer;
import com.arvent.Exception.CustomerServiceException;

import java.util.List;


public interface CustomerService {

    List<Customer> findAllCustomer();

    void saveCustomer(Customer customer);

    Customer customerBuilder(CustomerDTO customerDTO);

    Customer findCustomerById(Long id) ;

    void updateCustomer(CustomerDTO customer, Long id);

    Customer customerSetOldField(Customer customer);
}
