package com.arvent.Repository;

import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import com.arvent.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByCustomer(Customer customer);

    @Query("select s.product from ShoppingCart s WHERE s.customer IN :ids")
    List<Product> getAllCustomerProductByShoppingCartId(Optional<List<Long>> itemIdList);
}
