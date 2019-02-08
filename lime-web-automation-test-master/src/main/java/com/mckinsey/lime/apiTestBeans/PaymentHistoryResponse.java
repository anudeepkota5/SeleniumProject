package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class PaymentHistoryResponse extends BaseResponse {
    private List<Payment> payments;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payment {
        private String monthYear;
        private String totalAmount;
        private List<IndPaymentHistory> paymentHistory;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class IndPaymentHistory {
            private String originalAmount;
            private String postingDate;
            private String paymentSourceDesc;
            private String receiptTaxId;
            private List<Invoice> invoices;
            private String isPaidInAdvance;

            @Getter
            @Setter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Invoice {
                private String invoiceId;
                private String invoiceNo;
            }
        }

    }


}
