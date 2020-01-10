package com.arvent.Controller;

import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.Entity.Order.Order;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(name = "order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;



    @ApiOperation(value = "Create Order")
    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody ShoppingCartDTO shoppingCartDTO) throws OutOfStockException, CustomerNotFoundException, ProductNotFoundException {

        /**
         * UpdateProductQuantity
         * Payment
         * Successful
         * createOrder with status To_Ship - delete shoppingcart item - and  -block quantity add purchasePrice
         * --------------------------------
         * Failed
         * createOrder with status To_Pay expire in 1hour
         */

        orderService.updateProductQuantityTest(shoppingCartDTO);

        orderService.createOrder(shoppingCartDTO);

        /*
        try {
            orderService.createOrder(shoppingCartDTO);
        }catch Exception(){
            //send a msg to mq, worker do it
        }
        */
        boolean paymentSuccess = true;
        /*
        if(orderService.paymentCharge())
        {

        }
        */
        return new ResponseEntity<>("Purchase Successfully",HttpStatus.OK);

    }

    @ApiOperation(value = "Get Order by Order Id")
    @GetMapping("/get")
    public ResponseEntity getOrderByOrderId(@RequestHeader Long orderId){


        Order order = orderService.getOrderByOrderId(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @ApiOperation(value = "Get Order by Order Id")
    @GetMapping("/delete")
    public ResponseEntity deleteOrderByCustomerId(@RequestHeader Long orderId){


        orderService.deleteOrderByCustomerId(orderId);

        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);

    }

}
