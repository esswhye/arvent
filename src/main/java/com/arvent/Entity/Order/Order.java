package com.arvent.Entity.Order;


import com.arvent.Entity.BaseEntity;
import com.arvent.Entity.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
    //https://www.baeldung.com/spring-angular-ecommerce
@Entity
@Table(name = "Orders")
@ApiModel(description = "Customer Order Details")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated order ID")
    private Long id;

    @Column(name = "current_status", nullable = false)
    private Enum<Status> currentStatus;

    @OneToMany(mappedBy = "order")
    @Valid
    @JsonManagedReference
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

}
