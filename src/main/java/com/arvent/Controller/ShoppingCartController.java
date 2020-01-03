package com.arvent.Controller;

import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Service.CustomerService;
import com.arvent.Service.ProductService;
import com.arvent.Service.ShoppingCartService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("ShoppingCart")
@AllArgsConstructor
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private ProductService productService;
    private CustomerService customerService;

    @PostMapping("/add")
    @ApiOperation(value = "Add Customer's product into shopping cart", response = String.class)
    public ResponseEntity createShoppingItem(@RequestHeader(value = "id") Long id,
    @RequestParam("productId") Long productId, @RequestParam("quantity") int quantity)
            throws ProductNotFoundException, CustomerNotFoundException, SQLIntegrityConstraintViolationException, OutOfStockException {

        Product product = productService.getProductById(productId);
        Customer customer = customerService.findCustomerById(id);
        shoppingCartService.addItemToCart(customer,product,quantity);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/get")
    @ApiOperation(value = "Get Customer's Cart", response = ShoppingCart.class)
    public ResponseEntity getCustomerCart(@RequestHeader(value = "id") Long id) throws CustomerNotFoundException{

        //List<Product> cartItem = shoppingCartService.getItemList(id);

        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartByCustomerId(id);




        return new ResponseEntity<>(shoppingCartList, HttpStatus.OK);
    }







}
