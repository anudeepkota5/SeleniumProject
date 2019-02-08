package com.mckinsey.lime.apiUtils;

import com.mckinsey.lime.testDataBeans.TestData;

public class ApiUrl {
    //ToDo: Need to update as per the latest design
    public static final String INVOICE = "http://dmpapi-dev.trueid.net/iservice-subscriber-dev/invoices?accountNo=${accountNo}";
    public static final String PAYMENTS_TOKENS = "https://vault.omise.co/tokens";
    public static final String ANONYMOUS_PAYMENT = "https://dmpapi2.trueid-dev.net/iservice-payment/api/customers/anonymous";
    private static final String DEV_BASE_URL = TestData.getConfigData().getAppEnvironment().getBaseURL();
    public static final String PRODUCTS = DEV_BASE_URL + "/iservice-subscriber/api/products";
    public static final String CONVERSE = DEV_BASE_URL + "/iservice-support/api/converse";
    public static final String COUPONS = DEV_BASE_URL + "/iservice-privilege/api/coupons";
    public static final String CARDS = DEV_BASE_URL + "/iservice-payment/api/cards";
    public static final String TOPUP_VALIDITY = DEV_BASE_URL + "/iservice-content/api/topupvalidity";
    public static final String SERVICE_NUMBER = DEV_BASE_URL + "/iservice-content/api/servicenumber";
    public static final String PRODUCT_DETAILS = DEV_BASE_URL + "/iservice-subscriber/api/products/details";
    public static final String PREFERENCES = DEV_BASE_URL + "/iservice-subscriber/api/bills/preferences";
    public static final String ALLOCATION_STATUS = DEV_BASE_URL + "/iservice-payment/allocation-status";
    public static final String TRANSLATIONS = DEV_BASE_URL + "/iservice-content/api/translations?platform=mobile";
    public static final String TYPE = DEV_BASE_URL + "/iservice-subscriber/api/products/${msisdn}/type";
    public static final String REQUEST = DEV_BASE_URL + "/iservice-user/api/otp/request";
    public static final String TOPPINGS = DEV_BASE_URL + "/iservice-package/api/producttypes/${ProductType}/subscriptiontypes/${SubscriptionType}/products/${msisdn}/toppings";
    public static final String BILL_DETAILS = DEV_BASE_URL + "/iservice-subscriber/api/invoices?${accountIDs}";
    public static final String PAYMENT_HISTORY = DEV_BASE_URL + "/iservice-payment/api/payments/history?accountId=${accountID}";
    public static final String PRICE_PLAN = DEV_BASE_URL + "/iservice-package/api/products/${msisdn}/priceplans";
    public static final String PRICE_PLAN_TNC = DEV_BASE_URL + "/iservice-content/api/producttypes/TrueMoveH/packages/${pricePlanCode}/tnc";
    public static final String INVOICES = DEV_BASE_URL + "/iservice-subscriber/api/invoices";
    public static final String BILLING_HISTORY = DEV_BASE_URL + "/iservice-subscriber/api/bills/history";
    public static final String FIND_PLAN_OPTIONS = DEV_BASE_URL + "/wcms/api/getContent";
    public static final String QUOT_PRODUCTS = DEV_BASE_URL + "/quot/api/getProducts";
    private static final String BASE_URL = TestData.getConfigData().getAppEnvironment().getBaseURL();


}
