package com.arvent.Controller;

import com.arvent.DTO.CustomerDTO;
import com.arvent.Entity.Customer;
import com.arvent.Exception.*;
import com.arvent.Service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@Api(value = "Customer Management System", description = "Operations pertaining to customer in Customer Management System")
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "View a list of available customer", response = List.class)
    @GetMapping("/customers")
    public ResponseEntity<List> getAllEmployees() {

        List<Customer> all = customerService.findAllCustomer();
        //return ResponseEntity.ok(all);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a customer")
    @PostMapping("/customers/create")
    public ResponseEntity createCustomer(
            @ApiParam(value = "Customer object store into database")
            @Valid @RequestBody CustomerDTO customer) throws CustomerExistedException {

        customer.setPassword(customerService.customerEncryptPassword(customer.getPassword()));
        Customer builtCustomer = customerService.customerBuilder(customer);
        customerService.saveCustomer(builtCustomer);
        return new ResponseEntity<>("Customer saved", HttpStatus.OK);
    }

    @GetMapping("/customHeader")
    public ResponseEntity<String> customHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");

        return new ResponseEntity<>(
                "Custom header set", headers, HttpStatus.OK);
    }

    @GetMapping("/customer/id")
    public ResponseEntity getCustomerInfo(
            @RequestHeader(value = "id") Long id,
            @RequestHeader(value="User-Agent", defaultValue="foo")String userAgent
            //HttpServletResponse response
            )//throws ResourcesNotFoundException, CustomerServiceException
            throws CustomerNotFoundException {


        Customer customer = customerService.findCustomerById(id);

        return new ResponseEntity<>(customer,HttpStatus.OK);
        //System.out.println(userAgent);
        /*
        try {
            Optional<Customer> optionalCustomer = customerService.findCustomerById(id);
            if(optionalCustomer.isPresent())
            {
                Customer customer = optionalCustomer.get();
                return new ResponseEntity<>(customer,HttpStatus.OK);
            }
            throw new CustomerNotFoundException(id);
        } catch (CustomerNotFoundException e) {
            System.out.println("Error unable to find customer id: " + id);
            return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
        }
        */
    }
    @ApiOperation(value = "Update customer")
    @PutMapping("/customers/id/update")
    public ResponseEntity changeCustomerDetails(
            @RequestHeader(value = "id") Long id,
            @RequestHeader(value = "password") String password,
            @Valid @RequestBody CustomerDTO customer) throws CustomerPasswordException, IllegalAccessException
    {
        customerService.updateCustomer(customer,id,password);
        return new ResponseEntity<>("Customer saved", HttpStatus.OK);
    }

}
