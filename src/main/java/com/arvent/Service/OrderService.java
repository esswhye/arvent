package com.arvent.Service;

import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.DTO.ShoppingCartItemListDTO;
import com.arvent.Entity.Order.Order;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;

import java.util.List;

public interface OrderService {

     List<Order> getAllOrders();

     Order createOrder(ShoppingCartDTO productList);

     void validateProductExistence(List<ShoppingCartItemListDTO> itemList) throws OutOfStockException;

     Order getOrderByOrderId(Long userId);

     void deleteOrderByCustomerId(Long orderId);

    void validateProductExistence2(List<Long> itemIdList);
}
