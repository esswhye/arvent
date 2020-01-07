package com.arvent.Service;

import com.stripe.model.Coupon;

public interface StripeService {
    String createCustomer(String email, String token);
    public String createSubscription(String customerId, String plan, String coupon);
    boolean cancelSubscription(String subscriptionId);
    Coupon retrieveCoupon(String code);
    String createCharge(String email, String token, int amount);

}
