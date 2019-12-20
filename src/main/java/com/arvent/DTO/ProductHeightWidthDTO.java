package com.arvent.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductHeightWidthDTO {

    private int productHeight;
    private int productWidth;
    //private Product product;
}
