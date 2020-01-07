/*
package com.arvent.Properties;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

// we multplied amount by 100 because Stripe requires not dollars, but cents. So if we have to pay $100 then we should pass 100000 to Stripe. Also this value should be always integer.
@Component
//@AllArgsConstructor
public class StripeClient {

    @Autowired
    private StripeProperties stripeProperties;

    @PostConstruct
    public void initStripeKey(){
        Stripe.apiKey = this.stripeProperties.getSecretKey();
    }
    public Charge chargeCreditCard (String token, double amount) throws Exception
    {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }

    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard =     Customer.retrieve(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }


}
*/