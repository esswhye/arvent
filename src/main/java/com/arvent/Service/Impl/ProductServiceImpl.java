package com.arvent.Service.Impl;

import com.arvent.DTO.ProductDTO;
import com.arvent.Entity.Product;
import com.arvent.Entity.ProductHeightWidth;
import com.arvent.Repository.ProductHeightWidthRepository;
import com.arvent.Repository.ProductRepository;
import com.arvent.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    final
    ProductHeightWidthRepository productHeightWidthRepository;

    @Autowired
    public ProductServiceImpl(ProductHeightWidthRepository productHeightWidthRepository) {
        this.productHeightWidthRepository = productHeightWidthRepository;
    }

    @Override
    public Product productBuilder(ProductDTO productDTO) {



        /*
        Product product = Product.builder().productBrand(productDTO.getProductBrand())
                .productDiscount(productDTO.getProductDiscount()).productImageLink(productDTO.getProductImageLink())
                .productName(productDTO.getProductName())
                .productPrice(productDTO.getProductPrice()).build();
        */
        return Product.builder().productBrand(productDTO.getProductBrand())
                .productDiscount(productDTO.getProductDiscount())
                .productHeightWidth(ProductHeightWidth.builder()
                        .productWidth(productDTO.getProductHeightWidth().getProductWidth())
                        .productHeight(productDTO.getProductHeightWidth().getProductHeight())
                        .build())
                .productImageLink(productDTO.getProductImageLink())
                .productName(productDTO.getProductName())
                .productPrice(productDTO.getProductPrice()).build();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
        System.out.println("Product saved" + "\n" + product.toString());
    }

    @Override
    public ProductHeightWidth productHeightWidthBuilder(ProductDTO productDTO, Product product) {

        return ProductHeightWidth.builder().productWidth(productDTO.getProductHeightWidth().getProductWidth())
                .productHeight(productDTO.getProductHeightWidth().getProductHeight())
                .product(product)
                .build();
    }

    @Override
    public void saveProductHeightWidth(ProductHeightWidth productHeightWidth) {

        productHeightWidthRepository.save(productHeightWidth);
        System.out.println("Product saved" + "\n" + productHeightWidth.toString());
    }

    @Override
    @Transactional
    public void addListProducts(List<Product> productList) {

        Long startId = productRepository.findTopByOrderByIdDesc().getId();
        productRepository.saveBulkNewProducts(productList);
        productRepository.saveBulkNewProductsHeightWidth(productList,startId);
    }
}
