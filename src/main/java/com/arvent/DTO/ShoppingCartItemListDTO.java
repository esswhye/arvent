package com.arvent.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCartItemListDTO {

    private Long productId;
    private String productUrl;
    private String productBrand;
    private double productPrice;
    private boolean isAvailable;
    private double subCost;
    private int quantity;
}
