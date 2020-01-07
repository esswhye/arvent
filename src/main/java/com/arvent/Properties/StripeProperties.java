package com.arvent.Properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="stripe-properties")
public class StripeProperties {

    private String publicKey;

    private String secretKey;


}
