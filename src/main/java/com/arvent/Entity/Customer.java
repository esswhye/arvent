package com.arvent.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@ApiModel(description = "All details about the Customer.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated customer ID")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @ApiModelProperty(notes = "The customer first name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @ApiModelProperty(notes = "The customer last name")
    private String lastName;

    @Column(name = "email", nullable = false)
    @ApiModelProperty(notes = "The customer email id")
    private String emailId;

    @Column(name = "user_name", nullable = false)
    @ApiModelProperty(notes = "The customer user name")
    private String userName;

    @Column(name = "password", nullable = false)
    @ApiModelProperty(notes = "The customer password")
    private String password;

    @Column(name = "address", nullable = false)
    @ApiModelProperty(notes = "the customer address")
    private String address;

    @Column(name = "postal_code", nullable = false)
    @ApiModelProperty(notes = "The customer postal code")
    private String postalCode;

}
