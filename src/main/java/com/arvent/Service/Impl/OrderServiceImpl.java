package com.arvent.Service.Impl;

import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.DTO.ShoppingCartItemListDTO;
import com.arvent.Entity.Order.Order;
import com.arvent.Entity.Order.OrderItem;
import com.arvent.Entity.Order.Status;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Repository.CustomerRepository;
import com.arvent.Repository.OrderRepository;
import com.arvent.Repository.ProductRepository;
import com.arvent.Service.OrderService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService
{


    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    private CustomerRepository customerRepository;

    @Override
    public List<Order> getAllOrders() {

        return null;
    }

    @Override
    public Order createOrder(ShoppingCartDTO shoppingCartDTO) {
        //List<Product> productList = shoppingCartList.stream().map(t-> t.getProduct()).collect(Collectors.toList());
        Order order = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();


        order = Order.builder().currentStatus(Status.TOPAY.toString()).totalCost(shoppingCartDTO.getTotalCost())
                .customer(customerRepository.getOne(shoppingCartDTO.getCustomerId()))
                .orderItemList(orderItemList).build();

        //order = Order.builder().currentStatus(Status.TOPAY.toString()).customer(itemList.get(0).getCustomer()).totalCost(totalCost).orderItemList(orderItemList).build();

        for (ShoppingCartItemListDTO item:shoppingCartDTO.getItemList()
        ) {

            OrderItem orderItem = new OrderItem(productRepository.getOne(item.getProductId()), item.getQuantity());
            orderItem.setOrder(order);
            orderItem.setSubCost(item.getSubCost());
            orderItemList.add(orderItem);

        }

        orderRepository.save(order);

        return null;
    }

    @Override
    public void validateProductExistence(List<ShoppingCartItemListDTO> itemList) throws OutOfStockException {
        for (ShoppingCartItemListDTO item:itemList
             ) {
            if(!item.isAvailable())
                throw new OutOfStockException(item.getProductId());
        }
    }

    @Override
    public Order getOrderByOrderId(Long userId) {

        return orderRepository.findById(userId).get();
    }

    @Override
    public void deleteOrderByCustomerId(Long orderId) {

        orderRepository.deleteById(orderId);

    }
}
