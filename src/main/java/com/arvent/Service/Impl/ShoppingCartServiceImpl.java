package com.arvent.Service.Impl;

import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Repository.ShoppingCartRepository;
import com.arvent.Service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart addItemToCart(Customer customer, Product product) {
        ShoppingCart shoppingCart = new ShoppingCart(0L,customer,product,true);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }
}
