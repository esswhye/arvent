package com.arvent.Service;

import com.arvent.DTO.ShoppingCartDTO;
import com.arvent.DTO.ShoppingCartItemListDTO;
import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.arvent.Exception.ShoppingCartException.DuplicateItemException;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(Customer customer, Product product, int quantity);

    List<Product> getItemList(Long id) throws CustomerNotFoundException;

    List<ShoppingCart> getShoppingCartByCustomerId(Long id) throws CustomerNotFoundException;

    ShoppingCartDTO getItemListV2(Long id) throws CustomerNotFoundException;

    void deleteItemsByShoppingCartId(List<ShoppingCartItemListDTO> itemList);

    void deleteCartByCustomerIdAndProductId(List<Long> cartId);
}
