package com.arvent.Repository;

import com.arvent.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository< Product, Long> {
}
