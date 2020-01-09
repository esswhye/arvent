package com.arvent.Service.Impl;

import com.arvent.DTO.OrderItemDTO;
import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.DTO.ShoppingCartItemListDTO;
import com.arvent.Entity.Order.Order;
import com.arvent.Entity.Order.OrderItem;
import com.arvent.Entity.Order.Status;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Repository.CustomerRepository;
import com.arvent.Repository.OrderRepository;
import com.arvent.Repository.ProductRepository;
import com.arvent.Repository.ShoppingCartRepository;
import com.arvent.Service.CustomerService;
import com.arvent.Service.OrderService;

import com.arvent.Service.ProductService;
import com.arvent.Service.ShoppingCartService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService
{
    private OrderRepository orderRepository;

    private ProductService productService;

    private CustomerService customerService;

    private ShoppingCartService shoppingCartService;

    @Override
    public List<Order> getAllOrders() {

        return null;
    }

    @Override
    @Transactional
    public Order createOrder(ShoppingCartDTO shoppingCartDTO) throws  CustomerNotFoundException, ProductNotFoundException {


        Order order;
        List<OrderItem> orderItemList = new ArrayList<>();


        order = Order.builder().currentStatus(Status.TOSHIP.getStatus())
                .customer(customerService.findCustomerById(shoppingCartDTO.getCustomerId()))
                .orderItemList(orderItemList).totalCost(0.0).build();

        //order = Order.builder().currentStatus(Status.TOPAY.toString()).customer(itemList.get(0).getCustomer()).totalCost(totalCost).orderItemList(orderItemList).build();

        for (ShoppingCartItemListDTO item:shoppingCartDTO.getItemList()
        ) {


            OrderItem orderItem = new OrderItem(productService.getProductById(item.getProductId()), item.getQuantity());

            orderItem.setOrder(order);
            orderItemList.add(orderItem);
            orderItem.setPurchasedProductPrice(orderItem.getProduct().getProductPrice());
            orderItem.setSubTotal(orderItem.getProduct().getProductPrice()*item.getQuantity());
            order.addTotalCost(orderItem.getProduct().getProductPrice(),item.getQuantity());
        }

        orderRepository.save(order);

        shoppingCartService.deleteItemsByShoppingCartId(shoppingCartDTO.getItemList());


        return null;

    }

    @Override
    public Order getOrderByOrderId(Long userId) {

        return orderRepository.findById(userId).get();
    }

    @Override
    public void deleteOrderByCustomerId(Long orderId) {

        orderRepository.deleteById(orderId);

    }

    @Override
    @Transactional
    public void updateProductQuantityTest(ShoppingCartDTO shoppingCartDTO) throws OutOfStockException {
        //List<Long> productIdList = shoppingCartDTO.getItemList().stream().map(ShoppingCartItemListDTO::getProductId).collect(Collectors.toList());

        HashMap<Long,Integer> productIdQuantity = new HashMap<>();

        shoppingCartDTO.getItemList().forEach(item ->
            productIdQuantity.put(item.getProductId(),item.getQuantity())
        );

        productService.updateProductQuantity(productIdQuantity);


    }
}
