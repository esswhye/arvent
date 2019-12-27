package com.arvent.Service.Impl;

import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Repository.ShoppingCartRepository;
import com.arvent.Service.CustomerService;
import com.arvent.Service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;
    private CustomerService customerService;

    @Override
    public ShoppingCart addItemToCart(Customer customer, Product product) throws SQLIntegrityConstraintViolationException {
        ShoppingCart shoppingCart = new ShoppingCart(0L,customer, product,false);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public List<Product> getItemList(Long id) throws CustomerNotFoundException {

        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByCustomer(customerService.findCustomerById(id));

        List<Product> productList = shoppingCartList.stream().map(t-> t.getProduct()).collect(Collectors.toList());

        return productList;
    }
}
