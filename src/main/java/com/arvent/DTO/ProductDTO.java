package com.arvent.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

    private String productName;
    private String productBrand;
    private Double productPrice;
    private Double productDiscount;
    private String productImageLink;
    private ProductHeightWidthDTO productHeightWidth;
}
