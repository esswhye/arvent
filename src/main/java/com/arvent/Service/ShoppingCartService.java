package com.arvent.Service;

import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(Customer customer, Product product);
}
