package com.arvent.Service;

import com.arvent.DTO.ProductDTO;
import com.arvent.Entity.Product;
import com.arvent.Entity.ProductHeightWidth;

import java.util.List;

public interface ProductService {

    Product productBuilder(ProductDTO productDTO);

    void saveProduct(Product product);

    ProductHeightWidth productHeightWidthBuilder(ProductDTO productDTO, Product product);

    void saveProductHeightWidth(ProductHeightWidth productHeightWidth);

    void addListProducts(List<Product> productList);
}
