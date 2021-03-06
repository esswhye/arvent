package com.arvent.Service.Impl;

import com.arvent.DTO.ProductDTO;
import com.arvent.DTO.ProductHeightWidthDTO;
import com.arvent.Entity.Product;
import com.arvent.Entity.ProductHeightWidth;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Exception.ShoppingCartException.OutOfStockException;
import com.arvent.Repository.ProductHeightWidthRepository;
import com.arvent.Repository.ProductRepository;
import com.arvent.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
                .productPrice(productDTO.getProductPrice())
                .availableQuantity(productDTO.getQuantity())
                .blockQuantity(0)
                .build();
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

        productRepository.refreshInformationSchema();

        int nextProductAutoIncrementValue = getNextProductAutoIncrementValue();

        productRepository.saveBulkNewProducts(productList);
        //productRepository.saveBulkNewProductsHeightWidth(productList,optionalStartId.isPresent() ? optionalStartId.get() : 0L);
        //TODO where to get next auto_increment id - count(max)+1 X
        productRepository.saveBulkNewProductsHeightWidth(productList,nextProductAutoIncrementValue);

    }

    private int getNextProductAutoIncrementValue() {

        String sql = "SELECT Auto_increment FROM information_schema.TABLES WHERE TABLE_SCHEMA = \"Eviant\" AND TABLE_NAME = \"products\";";

        return jdbcTemplate.queryForObject(
                sql, new Object[] {}, Integer.class);
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
        if(product.getProductHeightWidth() != null)
            return ProductDTO.builder().productBrand(product.getProductBrand()).productId(product.getId())
                .productDiscount(product.getProductDiscount())
                .productImageLink(product.getProductImageLink())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productHeightWidth(ProductHeightWidthDTO.builder().productWidth(product.getProductHeightWidth().getProductWidth())
                        .productHeight(product.getProductHeightWidth().getProductHeight()).build())
                    .quantity(product.getAvailableQuantity())
                .build();
        else
            return ProductDTO.builder().productBrand(product.getProductBrand())
                    .productDiscount(product.getProductDiscount())
                    .productImageLink(product.getProductImageLink())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .quantity(product.getAvailableQuantity())
                    .build();
    }

    @Override
    public Product findProductById(Long id) throws ProductNotFoundException
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

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent())
            return productOptional.get();
        else
            throw new ProductNotFoundException(id);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean updateProductQuantity(HashMap<Long,Integer> productIdQuantity) throws OutOfStockException {
        Set<Map.Entry<Long, Integer>> entries = productIdQuantity.entrySet();
        for (Map.Entry<Long, Integer> entry : entries) {
            Long key = entry.getKey();
            Integer value = entry.getValue();
            int update = productRepository.updateProductQuantity(key,value);
            if(update < 1)
            {
                throw new OutOfStockException(key);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateProductQuantityBack(Map<Long, Integer> product) {

        product.forEach((productId,quantity) -> productRepository.updateProductQuantityBack(productId,quantity));

    }

    @Override
    public Page<ProductDTO> getAllProductsByPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Page<Product> productPage = productRepository.findAll(pageable);

        Page<ProductDTO> productDTOS = productPage.map(this::productDTOBuilder);

        return productDTOS;
    }

    @Override
    public void updateProductDetail(ProductDTO productDTO) throws ProductNotFoundException {

        Product product = findProductById(productDTO.getProductId());

        product.setId(productDTO.getProductId());
        product.getProductHeightWidth().setId(productDTO.getProductId());
        product.setAvailableQuantity(productDTO.getQuantity());
        product.setProductBrand(productDTO.getProductBrand());
        product.setProductDiscount(productDTO.getProductDiscount());
        product.setProductImageLink(productDTO.getProductImageLink());
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.getProductHeightWidth().setProductHeight(productDTO.getProductHeightWidth().getProductHeight());
        product.getProductHeightWidth().setProductWidth(productDTO.getProductHeightWidth().getProductWidth());

        productRepository.save(product);
    }

}
