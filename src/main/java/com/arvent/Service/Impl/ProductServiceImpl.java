package com.arvent.Service.Impl;

import com.arvent.DTO.ProductDTO;
import com.arvent.DTO.ProductHeightWidthDTO;
import com.arvent.Entity.Product;
import com.arvent.Entity.ProductHeightWidth;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Repository.ProductHeightWidthRepository;
import com.arvent.Repository.ProductRepository;
import com.arvent.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    private final
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
        //https://dzone.com/articles/java-8-optional-handling-nulls-properly

        Optional<Product> product = productRepository.findTopByOrderByIdDesc();
        productRepository.saveBulkNewProducts(productList);
        //productRepository.saveBulkNewProductsHeightWidth(productList,optionalStartId.isPresent() ? optionalStartId.get() : 0L);
        productRepository.saveBulkNewProductsHeightWidth(productList,product.get().getId());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product:productList) {
           productDTOList.add(productDTOBuilder(product));
        }
        return productDTOList;
    }

    @Override
    public ProductDTO productDTOBuilder(Product product)
    {
        return ProductDTO.builder().productBrand(product.getProductBrand())
                .productDiscount(product.getProductDiscount())
                .productImageLink(product.getProductImageLink())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productHeightWidth(ProductHeightWidthDTO.builder().productWidth(product.getProductHeightWidth().getProductWidth())
                        .productHeight(product.getProductHeightWidth().getProductHeight()).build())
                .build();
    }

    @Override
    public Product findCustomerById(Long id) throws ProductNotFoundException
    {
        //Optional<Product> optionalProduct = Optional.of(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id)));
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isPresent())
        {
            return optionalProduct.get();
        }else
        {
            throw new ProductNotFoundException(id);
        }
    }
}
