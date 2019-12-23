package com.arvent.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCustomerDTO {

        private String firstName;
        private String lastName;
        private String emailId;
        private String password;
        private String address;
        private String postalCode;

}

