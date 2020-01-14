package com.arvent.Service.Impl;

import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.DTO.ShoppingCartItemListDTO;
import com.arvent.Entity.Order.Order;
import com.arvent.Entity.Order.OrderItem;
import com.arvent.Entity.Order.Status;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Repository.*;
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
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService
{
    private OrderRepository orderRepository;

    private ProductService productService;

    private CustomerService customerService;

    private OrderItemRepository orderItemRepository;

    private ShoppingCartService shoppingCartService;

    @Override
    public List<Order> getAllOrders() {

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(ShoppingCartDTO shoppingCartDTO) throws  CustomerNotFoundException, ProductNotFoundException {


        Order order;
        List<OrderItem> orderItemList = new ArrayList<>();


        order = Order.builder().currentStatus(Status.TOPAY.getStatus())
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

         Order orderSaved = orderRepository.save(order);



        //Remove this after finishing payment
        //shoppingCartService.deleteItemsByShoppingCartId(shoppingCartDTO.getItemList());


        return orderSaved;

    }

    @Override
    public Order getOrderByOrderId(Long userId) {

        return orderRepository.findById(userId).get();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteOrderByCustomerId(Long orderId) {

        //Product product = orderRepository.getById(cartId);

        Order order = orderRepository.findById(orderId).get();

        Map<Long,Integer> itemIdQuantity = order.getOrderItemList().stream().collect(Collectors.toMap(t->t.getProduct().getId(), t-> t.getQuantity()));

        productService.updateProductQuantityBack(itemIdQuantity);

        orderRepository.deleteById(orderId);

    }

    @Override
    @Transactional
    public void updateProductQuantityTest(ShoppingCartDTO shoppingCartDTO) throws OutOfStockException {
        //List<Long> productIdList = shoppingCartDTO.getItemList().stream().map(ShoppingCartItemListDTO::getProductId).collect(Collectors.toList());

        HashMap<Long,Integer> productIdQuantity = new HashMap<>();

        shoppingCartDTO.getItemList().forEach(item ->
            productIdQuantity.put(item.getProductId(),item.getQuantity()));

        productService.updateProductQuantity(productIdQuantity);

    }

    @Override
    public void changeOrderToShip(Order order, List<ShoppingCartItemListDTO> itemList) {

        order.setCurrentStatus(Status.TOSHIP.getStatus());
        shoppingCartService.deleteItemsByShoppingCartId(itemList);

    }


}
