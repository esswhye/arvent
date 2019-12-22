package com.arvent.Service;

import com.arvent.DTO.CustomerDTO;
import com.arvent.Entity.Customer;
import com.arvent.Exception.CustomerExistedException;
import com.arvent.Exception.CustomerNotFoundException;
import com.arvent.Exception.CustomerPasswordException;

import java.util.List;


public interface CustomerService {

    List<Customer> findAllCustomer();

    void saveCustomer(Customer customer) throws CustomerExistedException;

    Customer customerBuilder(CustomerDTO customerDTO);

    Customer findCustomerById(Long id) throws CustomerNotFoundException;

    void updateCustomer(CustomerDTO customer, Long id, String password) throws CustomerPasswordException, IllegalAccessException;

    String customerEncryptPassword(String password);
}
