package com.arvent.Service;

import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.ShoppingCartException.DuplicateItemException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(Customer customer, Product product) throws DuplicateItemException, SQLIntegrityConstraintViolationException;

    List<Product> getItemList(Long id) throws CustomerNotFoundException;
}
