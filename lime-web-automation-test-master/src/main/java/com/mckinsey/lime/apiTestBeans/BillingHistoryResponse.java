package com.mckinsey.lime.apiTestBeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class BillingHistoryResponse extends BaseResponse {
    private Map<String, List<IndBillingHistory>> billingsByAccountIDs;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndBillingHistory {

        private String invoiceNo;
        private String dueDate;
        private String invoiceType;
        private String billAmount;
        private String unpaidAmount;
        private String invoiceCycle;
        private String accountId;
        private String invoiceAmount;
        private String invoiceStatus;
        private String invoiceBcBanId;
        private String adjustmentAmount;
        private String isInvoiceDownloadable;

    }
}
