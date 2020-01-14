package com.arvent.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;

@Entity
@Table(name = "customers_credit_card")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ToString(callSuper = true)
public class CustomerCreditCard extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stripe_id")
    private String stripeId;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
