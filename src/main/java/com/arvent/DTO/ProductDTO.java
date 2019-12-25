package com.arvent.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private String productName;
    private String productBrand;
    private Double productPrice;
    private Double productDiscount;
    private String productImageLink;

    private ProductHeightWidthDTO productHeightWidth;
}
