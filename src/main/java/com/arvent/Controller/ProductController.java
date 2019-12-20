package com.arvent.Controller;

import com.arvent.DTO.ProductDTO;
import com.arvent.Entity.Product;
import com.arvent.Entity.ProductHeightWidth;
import com.arvent.Service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("product")
@Api(value="Product Management System", description="Operations pertaining to product in Product Management System")
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation(value = "Add a product")
    @PostMapping("/products/create")
    public ResponseEntity createProduct(@ApiParam(value = "Product object store into database. 1 to 1 relationship mandatory.")
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

}
