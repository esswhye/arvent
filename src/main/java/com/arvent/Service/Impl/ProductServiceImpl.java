package com.arvent.Service.Impl;

import com.arvent.DTO.ProductDTO;
import com.arvent.Entity.Product;
import com.arvent.Entity.ProductHeightWidth;
import com.arvent.Repository.ProductHeightWidthRepository;
import com.arvent.Repository.ProductRepository;
import com.arvent.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductHeightWidthRepository productHeightWidthRepository;

    @Override
    public Product productBuilder(ProductDTO productDTO) {

        Product product = Product.builder().productBrand(productDTO.getProductBrand())
                .productDiscount(productDTO.getProductDiscount())
                .productHeightWidth(ProductHeightWidth.builder()
                        .productWidth(productDTO.getProductHeightWidth().getProductWidth())
                        .productHeight(productDTO.getProductHeightWidth().getProductHeight())
                        .build())
                .productImageLink(productDTO.getProductImageLink())
                .productName(productDTO.getProductName())
                .productPrice(productDTO.getProductPrice()).build();



        /*
        Product product = Product.builder().productBrand(productDTO.getProductBrand())
                .productDiscount(productDTO.getProductDiscount()).productImageLink(productDTO.getProductImageLink())
                .productName(productDTO.getProductName())
                .productPrice(productDTO.getProductPrice()).build();
        */
        return product;
    }



    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
        System.out.println("Product saved" + "\n" + product.toString());
    }

    @Override
    public ProductHeightWidth productHeightWidthBuilder(ProductDTO productDTO, Product product) {

        ProductHeightWidth productHeightWidth = ProductHeightWidth.builder().productWidth(productDTO.getProductHeightWidth().getProductWidth())
                .productHeight(productDTO.getProductHeightWidth().getProductHeight())
                .product(product)
                .build();

        return productHeightWidth;
    }

    @Override
    public void saveProductHeightWidth(ProductHeightWidth productHeightWidth) {

        productHeightWidthRepository.save(productHeightWidth);
        System.out.println("Product saved" + "\n" + productHeightWidth.toString());
    }
}
