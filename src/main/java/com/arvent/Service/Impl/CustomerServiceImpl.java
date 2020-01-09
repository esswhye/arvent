package com.arvent.Service.Impl;

import com.arvent.DTO.CustomerDTO;
import com.arvent.DTO.UpdateCustomerDTO;
import com.arvent.Entity.Customer;
import com.arvent.Exception.CustomerException.CustomerExistedException;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.CustomerException.CustomerPasswordException;
import com.arvent.Repository.CustomerRepository;
import com.arvent.Service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;
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
    public void saveCustomer(Customer customer) throws CustomerExistedException{

        List<Customer> customerList = findAllCustomer();

         if(customerList.stream()
             .anyMatch(t -> t.getUserName().equals(customer.getUserName())))
         {
             throw new CustomerExistedException(customer.getUserName());
         }

        customerRepository.save(customer);
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

    private Customer customerBuilder(UpdateCustomerDTO customerDTO) {
        return Customer.builder().address(customerDTO.getAddress())
                .emailId(customerDTO.getEmailId())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .password(customerDTO.getPassword())
                .postalCode(customerDTO.getPostalCode())
                .build();
    }

    @Override
    public Customer findCustomerById(Long id) throws CustomerNotFoundException {

        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent())
        {
            return customer.get();
        }else
        {
         throw new CustomerNotFoundException(id);

        }

       // return customer.orElse(null);
        /*
        if(customer.isPresent()) {
            return customer.get();
        }
         */
    }

    @Override
    public void updateCustomer(UpdateCustomerDTO customer, Long id, String password)throws CustomerPasswordException, IllegalAccessException, CustomerNotFoundException{

        Customer builtCustomer = customerBuilder(customer);

        try {
            Customer existedCustomerDetail = customerRepository.findById(id).get();

            if (BCrypt.checkpw(password, existedCustomerDetail.getPassword())) {
                System.out.println("It matches");
                //Field[] fields = builtCustomer.getClass().getDeclaredFields();
                Field[] fieldsExisted = existedCustomerDetail.getClass().getDeclaredFields();
                for(Field field : builtCustomer.getClass().getDeclaredFields()){
                    field.setAccessible(true);
                    Object value = field.get(builtCustomer);
                    System.out.println(field.getName()+ " "+value);
                    if(value!=null)
                    {
                        for(Field fieldExisted: fieldsExisted)
                        {
                            fieldExisted.setAccessible(true);
                            //Object existedValue = field.get(existedCustomerDetail);
                            if(field.getName().equals("password"))
                            {
                                field.set(existedCustomerDetail,customerEncryptPassword(value.toString()));
                            }else {
                                field.set(existedCustomerDetail, value);
                            }
                        }
                    }
                }
                System.out.println(existedCustomerDetail.toString());
                customerRepository.save(existedCustomerDetail);
            }
            else
                throw new CustomerPasswordException(existedCustomerDetail.getUserName() + " Invalid Password. Please try again!");
        }catch (NoSuchElementException ex)
        {
            throw new CustomerNotFoundException(id);
        }



        /*Customer builtCustomer = customerBuilder(customer);
        try {
            Optional<Customer> customerFound = findProductById(id);
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
    public String customerEncryptPassword(String password)

    {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return hashed;
    }


}
