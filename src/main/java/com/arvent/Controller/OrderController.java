package com.arvent.Controller;

import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Repository.OrderRepository;
import com.arvent.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "Order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;



    @PostMapping
    public ResponseEntity createOrder(@RequestBody List<ShoppingCart> itemList) throws OutOfStockException {

        orderService.validateProductExistence(itemList);

        orderService.createOrder(itemList);



        return null;

    }

}
