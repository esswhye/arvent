package com.arvent.Controller;

import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Exception.ShoppingCartException.QuantityMoreThanProductQuantity;
import com.arvent.Service.CustomerService;
import com.arvent.Service.ProductService;
import com.arvent.Service.ShoppingCartService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            throws ProductNotFoundException, CustomerNotFoundException, QuantityMoreThanProductQuantity {

        Product product = productService.getProductById(productId);
        if(product.getAvailableQuantity()<quantity)
        {
            throw new QuantityMoreThanProductQuantity(productId);
        }
        Customer customer = customerService.findCustomerById(id);
        shoppingCartService.addItemToCart(customer,product,quantity);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


    @GetMapping("/get")
    @ApiOperation(value = "Get Customer's Item", response = ShoppingCartDTO.class)
    public ResponseEntity getCustomerCart(@RequestHeader(value = "id") Long id) throws CustomerNotFoundException{

        //List<Product> cartItem = shoppingCartService.getItemList(id);

        ShoppingCartDTO shoppingCart = shoppingCartService.getItemListV2(id);

        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete Customer's Cart")
    public ResponseEntity deleteCustomerCart(@RequestHeader(value = "cartId") List<Long> cartId) {
        shoppingCartService.deleteCartByCustomerIdAndProductId(cartId);

        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
