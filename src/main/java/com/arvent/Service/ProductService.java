package com.arvent.Service;

import com.arvent.DTO.ProductDTO;
import com.arvent.Entity.Product;
import com.arvent.Entity.ProductHeightWidth;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {

    Product productBuilder(ProductDTO productDTO);

    void saveProduct(Product product);

    ProductHeightWidth productHeightWidthBuilder(ProductDTO productDTO, Product product);

    void saveProductHeightWidth(ProductHeightWidth productHeightWidth);

    void addListProducts(List<Product> productList);

    List<ProductDTO> getAllProducts();

    ProductDTO productDTOBuilder(Product product);

    Product findProductById(Long id) throws ProductNotFoundException;

    Product getProductById(Long id) throws ProductNotFoundException;

    @Transactional
    boolean updateProductQuantity(HashMap<Long, Integer> productIdList) throws OutOfStockException;

    void updateProductQuantityBack(Map<Long, Integer> product);

    Page<ProductDTO> getAllProductsByPage(int pageNo, int pageSize);

    void updateProductDetail(ProductDTO productDTO) throws ProductNotFoundException;
}
