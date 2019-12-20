package com.arvent.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_height_width")
@ApiModel(description = "All details about the Customer.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class ProductHeightWidth extends BaseEntity{


    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "product_height", nullable = false)
    @ApiModelProperty(notes = "Image Height")
    private int productHeight;

    @Column(name = "product_width", nullable = false)
    @ApiModelProperty(notes = "Image Width")
    private int productWidth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @MapsId
    @ToString.Exclude
    private Product product;
    /*
    public ProductHeightWidth(int productHeight, int productWidth) {
        this.productHeight = productHeight;
        this.productWidth = productWidth;
    } */
}
