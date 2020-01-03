package com.arvent.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart")
@ApiModel(description = "Customer's Cart")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated cart ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @ToString.Exclude
    private Product product;

    @Column(name = "is_deleted" , nullable = false)
    private boolean isDeleted;

    private int quantity;

    private double totalCost;

    public ShoppingCart(Customer customer, Product product, boolean isDeleted, int quantity) {
        this.customer = customer;
        this.product = product;
        this.isDeleted = isDeleted;
        this.quantity = quantity;
        this.totalCost = quantity*product.getProductPrice();
    }
}
