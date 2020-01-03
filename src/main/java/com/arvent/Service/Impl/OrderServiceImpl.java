package com.arvent.Service.Impl;

import com.arvent.Entity.Order.Order;
import com.arvent.Entity.Order.OrderItem;
import com.arvent.Entity.Order.Status;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Repository.OrderRepository;
import com.arvent.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService
{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {

        return null;
    }

    @Override
    public Order createOrder(List<ShoppingCart> itemList) {
        //List<Product> productList = shoppingCartList.stream().map(t-> t.getProduct()).collect(Collectors.toList());
        double totalCost = 0;
        Order order = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();

        for (ShoppingCart item:itemList
             ) {
            totalCost += item.getTotalCost();
            OrderItem orderItem = new OrderItem(item.getProduct(), item.getQuantity());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);

        }

        order = Order.builder().currentStatus(Status.TOPAY).customer(itemList.get(0).getCustomer()).totalCost(totalCost).orderItemList(orderItemList).build();

        orderRepository.save(order);

        return null;
    }

    @Override
    public void validateProductExistence(List<ShoppingCart> itemList) throws OutOfStockException {
        for (ShoppingCart item:itemList
             ) {
            if(!item.getProduct().isAvailable())
                throw new OutOfStockException(item.getProduct().getId());
        }
    }
}
