package com.arvent.Controller;

import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.Entity.Order.Order;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(name = "Order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;



    @ApiOperation(value = "Create Order")
    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody ShoppingCartDTO shoppingCartDTO) throws OutOfStockException {


        orderService.validateProductExistence(shoppingCartDTO.getItemList());

        orderService.createOrder(shoppingCartDTO);

        return null;

    }


    @ApiOperation(value = "Get Order by Order Id")
    @GetMapping("/get")
    public ResponseEntity getOrderByOrderId(@RequestHeader Long orderId) throws OutOfStockException {


        Order order = orderService.getOrderByOrderId(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @ApiOperation(value = "Get Order by Order Id")
    @GetMapping("/delete")
    public ResponseEntity deleteOrderByCustomerId(@RequestHeader Long orderId)  {


        orderService.deleteOrderByCustomerId(orderId);

        return new ResponseEntity<>("Delete Test", HttpStatus.OK);

    }

}
