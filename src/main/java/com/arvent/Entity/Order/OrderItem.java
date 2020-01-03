package com.arvent.Entity.Order;

import com.arvent.Entity.BaseEntity;
import com.arvent.Entity.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @ManyToOne
    @JoinColumn(name = "fk_order")
    private Order order;

    @OneToOne
    private Product product;

    @Column(name = "order_date" , nullable = false)
    private Date orderDate;

    private int quantity;


    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        Date date = new Date();
        this.orderDate = date;
    }
}
