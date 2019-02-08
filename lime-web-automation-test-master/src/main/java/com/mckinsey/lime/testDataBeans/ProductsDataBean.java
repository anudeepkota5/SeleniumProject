package com.mckinsey.lime.testDataBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Deprecated
public class ProductsDataBean {
    private Product product1;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Product {

        private String customerId;
        private String productId;
        private String productType;
        private String subscriberId;
        private String subscriptionType;
        private String thaiID;
        private String description;
    }
}
