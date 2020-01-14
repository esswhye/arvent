package com.arvent.Service;

import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.DTO.ShoppingCartItemListDTO;
import com.arvent.Entity.Order.Order;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

     List<Order> getAllOrders();

     Order createOrder(ShoppingCartDTO productList) throws OutOfStockException, CustomerNotFoundException, ProductNotFoundException;

     Order getOrderByOrderId(Long userId);

     void deleteOrderByCustomerId(Long orderId);

     @Transactional
     void updateProductQuantityTest(ShoppingCartDTO shoppingCartDTO) throws OutOfStockException;

    void changeOrderToShip(Order order, List<ShoppingCartItemListDTO> itemList);
}
