package com.arvent.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@ApiModel(description = "All details about the Products.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated product ID")
    private Long id;

    @Column(name = "product_name", nullable = false)
    @ApiModelProperty(notes = "The product name")
    private String productName;

    @Column(name = "product_brand")
    @ApiModelProperty(notes = "The product's brand")
    private String productBrand;

    @Column(name = "product_price")
    @ApiModelProperty(notes = "The product's price")
    private Double productPrice;

    @Column(name = "product_discount")
    @ApiModelProperty (notes = "The product's Discount")
    private Double productDiscount;

    @Column(name = "product_imagelink")
    @ApiModelProperty(notes = "Image directory")
    private String productImageLink;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductHeightWidth productHeightWidth;

    @Column(nullable = false)
    private int quantity;

    private boolean isAvailable;

    /*
    public Product(String productName, String productBrand, Double productPrice, Double productDiscount, String productImageLink, ProductHeightWidth productHeightWidth) {
        this.productName = productName;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.productImageLink = productImageLink;
        this.productHeightWidth = productHeightWidth;
        productHeightWidth.setProduct(this);

    }*/
}

/*
    create table products
        (
                id bigint not null AUTO_INCREMENT primary key,
                productName varchar(255) not null,
        productBrand varchar (255),
        productPrice number (10), not null,
        productDiscount number (10),
        productImageLink varchar (255),
        productImageWidth varchar (255),
        productImageHeight varchar (255),
        created_date       DATETIME              DEFAULT CURRENT_TIMESTAMP  ,
        last_modified_date DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        );

        */