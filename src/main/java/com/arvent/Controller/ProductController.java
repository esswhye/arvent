package com.arvent.Controller;

import com.arvent.DTO.ProductDTO;
import com.arvent.Entity.Product;
import com.arvent.Entity.ProductHeightWidth;
import com.arvent.Exception.ProductException.ProductNotFoundException;
import com.arvent.Service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
@Api(value="Product Management System", description="Operations pertaining to product in Product Management System")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Add a product")
    @PostMapping("/products/create")
    public ResponseEntity addProduct(@ApiParam(value = "Product object store into database. 1 to 1 relationship mandatory.")
                                        @Valid @RequestBody ProductDTO productDTO)
    {
        Product product = productService.productBuilder(productDTO);
        ProductHeightWidth productHeightWidth = productService.productHeightWidthBuilder(productDTO,product);
        product.setProductHeightWidth(productHeightWidth);
        /*
        Product productTest = new Product("Test","test",0.0,0.0,"test",
            new ProductHeightWidth(0,0));
        */
        //productRepository.save( product);

        productService.saveProduct(product);

        return new ResponseEntity<>("Product saved", HttpStatus.OK);
    }



    @ApiOperation(value = "Add a list product")
    @PostMapping("/products/addProducts")
    public ResponseEntity addListProduct(@ApiParam(value = "List of product object store into database. 1 to 1 relationship mandatory.")
                                        @Valid @RequestBody List<ProductDTO> productDTOList)
    {

        List<Product> productList = new ArrayList<>();

        for (ProductDTO productdto:productDTOList) {
            Product product = productService.productBuilder(productdto);
            ProductHeightWidth productHeightWidth = productService.productHeightWidthBuilder(productdto,product);
            product.setProductHeightWidth(productHeightWidth);
            productList.add(product);
        }

       productService.addListProducts(productList);

        return new ResponseEntity<>("Product saved", HttpStatus.OK);
    }

    @ApiOperation(value = "Get list of products")
    @GetMapping("/products")
    public ResponseEntity<List> getListOfProducts()
    {
        List<ProductDTO> productDTOList = productService.getAllProducts();

        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }

    @ApiOperation(value = "Get page products")
    @GetMapping("/productspage")
    public ResponseEntity getPageOfProducts(@RequestHeader("pageNo") int pageNo, @RequestHeader("pageSize") int pageSize) {

        Page<ProductDTO> productDTOPage = productService.getAllProductsByPage(pageNo,pageSize);

        return new ResponseEntity<>(productDTOPage, HttpStatus.OK);
    }


    @ApiOperation(value = "Get a products")
    @GetMapping("/products/id")
    public ResponseEntity<ProductDTO> getAProducts(@RequestHeader(value = "id") Long id) throws ProductNotFoundException
    {
        Product product = productService.getProductById(id);
        ProductDTO productDTO = productService.productDTOBuilder(product);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @ApiOperation(value = "Update a products")
    @PutMapping("/products/update")
    public ResponseEntity updateProduct(@RequestBody ProductDTO productDTO) throws ProductNotFoundException
    {


        productService.updateProductDetail(productDTO);

        return new ResponseEntity<>("Update product :" + productDTO.getProductId()+ " successfully",HttpStatus.OK);
    }








}
