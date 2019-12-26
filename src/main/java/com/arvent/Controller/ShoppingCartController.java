package com.arvent.Controller;

import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Service.CustomerService;
import com.arvent.Service.ProductService;
import com.arvent.Service.ShoppingCartService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ShoppingCart")
@AllArgsConstructor
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private ProductService productService;
    private CustomerService customerService;

    @PostMapping("/add")
    @ApiOperation(value = "Add Customer's product into shopping cart", response = ShoppingCart.class)
    public ResponseEntity createShoppingItem(@RequestHeader(value = "id") Long id,
    @RequestParam("productId") Long productId) throws ProductNotFoundException, CustomerNotFoundException {

        Product product = productService.getProductById(productId);
        Customer customer = customerService.findCustomerById(id);
        ShoppingCart shoppingCart = shoppingCartService.addItemToCart(customer,product);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }
}
