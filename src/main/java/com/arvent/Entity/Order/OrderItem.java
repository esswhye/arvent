package com.arvent.Entity.Order;

import com.arvent.Entity.BaseEntity;
import com.arvent.Entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Order_Items")
@ApiModel(description = "Customer Order Details")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated order ID")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToOne
    private Product product;

    private int quantity;


    @Column(name = "sub_total", nullable = false)
    private Double subTotal;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Column(name="purchased_product_price")
    private double purchasedProductPrice;


}
