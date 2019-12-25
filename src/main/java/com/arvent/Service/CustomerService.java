package com.arvent.Service;

import com.arvent.DTO.CustomerDTO;
import com.arvent.DTO.UpdateCustomerDTO;
import com.arvent.Entity.Customer;
import com.arvent.Exception.CustomerException.CustomerExistedException;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.CustomerException.CustomerPasswordException;

import java.util.List;


public interface CustomerService {

    List<Customer> findAllCustomer();

    void saveCustomer(Customer customer) throws CustomerExistedException;

    Customer customerBuilder(CustomerDTO customerDTO);

    Customer findCustomerById(Long id) throws CustomerNotFoundException;

    void updateCustomer(UpdateCustomerDTO customer, Long id, String password) throws CustomerPasswordException, IllegalAccessException, CustomerNotFoundException;

    String customerEncryptPassword(String password);
}
