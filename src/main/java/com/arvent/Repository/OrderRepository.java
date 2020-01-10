package com.arvent.Repository;

import com.arvent.Entity.Order.Order;
import com.arvent.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
