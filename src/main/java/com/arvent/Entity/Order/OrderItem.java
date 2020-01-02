package com.arvent.Entity.Order;

import com.arvent.Entity.BaseEntity;
import com.arvent.Entity.Customer;
import com.arvent.Entity.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne
    @JoinColumn(name = "customer_ID")
    private Customer customer;

    @OneToOne
    private Product product;

    @Column(name = "order_date" , nullable = false)
    private Date orderDate;




}

enum Status {
    TOPAY,
    TOSHIP,
    TORECEIVE,
    COMPLETED,
    CANCELLED,
    RETURNREFUND
}