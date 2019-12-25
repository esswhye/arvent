package com.arvent.Repository;

import com.arvent.Entity.Product;
import com.arvent.Repository.JPAQuery.ProductLongId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository< Product, Long>, CustomProductRepository {

    Optional<Product> findTopByOrderByIdDesc();

}
