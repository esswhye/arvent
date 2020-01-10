package com.arvent.Repository;

import com.arvent.Entity.Product;
import com.arvent.Repository.JPAQuery.ProductLongId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository< Product, Long>, CustomProductRepository {

    Optional<Product> findTopByOrderByIdDesc();

    @Modifying
    @Query(value = "Update Product p set p.availableQuantity=(p.availableQuantity- :quantity), " +
            "p.blockQuantity = p.blockQuantity+:quantity where p.id = :productId AND p.availableQuantity >= :quantity")
    Integer updateProductQuantity(@Param("productId")Long productId, @Param("quantity") int quantity);

    @Modifying
    @Query(value = "Update Product p set p.blockQuantity=(p.blockQuantity-:quantity) where p.id = :productId")
    Integer updateProductQuantityAfterSuccessPurchase(@Param("productId")Long productId, @Param("quantity") int quantity);

    @Modifying
    @Query(value = "Update Product p set p.availableQuantity =(p.availableQuantity + :quantity) , p.blockQuantity = (p.blockQuantity - :quantity) where p.id = :productId")
    Integer updateProductQuantityBack(@Param("productId")Long id, @Param("quantity")Integer quantity);
}
