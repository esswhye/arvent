package com.arvent.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart" ,uniqueConstraints={
        @UniqueConstraint(columnNames = {"customer_id", "product_id"})})
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
    @JoinColumn(name ="customer_id")
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

    private int quantity;

    //private double totalCost;

    public ShoppingCart(Customer customer, Product product, int quantity) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        //this.totalCost = quantity*product.getProductPrice();
    }
}
