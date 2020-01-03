package com.arvent.Service;

import com.arvent.Entity.Order.Order;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;

import java.util.List;

public interface OrderService {

     List<Order> getAllOrders();

     Order createOrder(List<ShoppingCart> productList);

     void validateProductExistence(List<ShoppingCart> itemList) throws OutOfStockException;
}
