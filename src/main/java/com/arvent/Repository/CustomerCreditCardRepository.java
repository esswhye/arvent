package com.arvent.Repository;

import com.arvent.Entity.CustomerCreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCreditCardRepository extends JpaRepository<CustomerCreditCard, Long> {
}
