package com.mckinsey.lime.apiTestBeans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//ToDo: Need to update as per the latest design
@Deprecated
public class IndividualBillDetails {
    private String invoiceNo;
    private String dueDate;
    private String billAmount;
    private String unpaidAmount;
    private String invoiceStatus;
    private String invoiceCycle;

}
