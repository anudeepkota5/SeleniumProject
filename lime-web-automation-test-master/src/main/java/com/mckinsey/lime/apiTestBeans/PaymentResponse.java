package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class PaymentResponse extends BaseResponse {

    private String transactionId;
    private String amount;
    private String status;
    private String createdAt;
    private String authorizeUri;
    private String chargeId;
    private List<payableProduct> payables;


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class payableProduct {
        private String companyCode;
        private String merchantName;
        private String productType;
        private String subscriptionType;
        private String accountId;
        private String amount;
        private String isBillConsolidated;
        private String id;
        private String paymentSystem;
        private String shop_code;
        private String merchant_reference;
        private String merchant_name;
        private String ref1;
        private String service;
        private String merchant_id;
        private boolean isConvergence;
    }
}
