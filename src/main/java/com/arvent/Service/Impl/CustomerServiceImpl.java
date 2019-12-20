package com.arvent.Service.Impl;

import com.arvent.DTO.CustomerDTO;
import com.arvent.Entity.Customer;
import com.arvent.Exception.CustomerServiceException;
import com.arvent.Repository.CustomerRepository;
import com.arvent.Service.CustomerService;
import lombok.AllArgsConstructor;
import com.diffplug.common.base.FieldsAndGetters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }


    @Override
    public void saveCustomer(Customer customer) {

        customerRepository.save(customer);
        System.out.println("customer saved");
        System.out.println(customer);
        /*
        Optional<Customer> customer1 = customerRepository.findById(2L);

        if (BCrypt.checkpw("string", customer1.get().getPassword()))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
            */

    }

    @Override
    public Customer customerBuilder(CustomerDTO customerDTO) {
        return Customer.builder().address(customerDTO.getAddress())
                .emailId(customerDTO.getEmailId())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .password(customerDTO.getPassword())
                .postalCode(customerDTO.getPostalCode())
                .userName(customerDTO.getUserName()).build();
    }

    @Override
    public Customer findCustomerById(Long id)  {

        Optional<Customer> customer = customerRepository.findById(id);

        return customer.orElse(null);
        /*
        if(customer.isPresent()) {
            return customer.get();
        }
         */
    }

    @Override
    public void updateCustomer(CustomerDTO customer, Long id) {
        Customer builtCustomer = customerBuilder(customer);
        builtCustomer = customerSetOldField(builtCustomer);

        /*Customer builtCustomer = customerBuilder(customer);
        try {
            Optional<Customer> customerFound = findCustomerById(id);
            if(customerFound.isPresent())
            {
                builtCustomer.setId(id);
                customerRepository.save(builtCustomer);
            }
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public Customer customerSetOldField(Customer customer) {

        FieldsAndGetters.fields(customer)
                .map(field -> field.getKey().getName() + " = " + field.getValue())
                .forEach(System.out::println);



            return null;


    }


}
