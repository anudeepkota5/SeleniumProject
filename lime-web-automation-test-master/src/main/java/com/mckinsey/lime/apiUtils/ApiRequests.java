package com.mckinsey.lime.apiUtils;

import com.mckinsey.lime.apiTestBeans.*;
import com.mckinsey.lime.guiDataBeans.GetAQuoteBean;
import com.mckinsey.lime.guiDataBeans.PossibleFiguresBean;
import com.mckinsey.lime.testDataBeans.FilePaths;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.utils.*;
import com.mckinsey.lime.utils.Utilities;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApiRequests {
    //TODO: accept-language is sometimes en & some times th

    @Deprecated
    public static CustomResponse<ProductsResponse> sendProductsAPI(CustomClass obj, UsersDataBean.User user, String channel, String platform) throws IOException {

        Instant start = Instant.now();


        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingGetRequest(ApiUrl.PRODUCTS, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRODUCTS api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<ProductsResponse> customResponse = new CustomResponse<>(response, ProductsResponse.class);

        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.PRODUCTS, headers, "", customResponse.getResponse()));

        return customResponse;

    }

    @Deprecated
    public static CustomResponse<PaymentTokensResponse> sendPaymentTokensAPI(CustomClass obj, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String postDataFileData = "{\"card\":{\"name\":\"Card Name\",\"number\":\"4242424242424242\",\"security_code\":\"123\",\"expiration_month\":1,\"expiration_year\":21}}";

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);
        headers.put("authority", "vault.omise.co");

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.PAYMENTS_TOKENS, postDataFileData, headers, true);
//        CustomLogging.witeInfo("Response Code : " + response.getStatusLine().getStatusCode());

        CustomLogging.writeInfoLogToReport("Time taken for PAYMENT TOKENS api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<PaymentTokensResponse> paymentTokensResponseCustomResponse = new CustomResponse<>(response, PaymentTokensResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.PAYMENTS_TOKENS, headers, postDataFileData, paymentTokensResponseCustomResponse.getResponse()));
        return paymentTokensResponseCustomResponse;

    }

    @Deprecated
    public static HttpResponse sendPaymentReceiptAPI(CustomClass obj, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String postDataFileData = "{\"card\":{\"name\":\"Card Name\",\"number\":\"4242424242424242\",\"security_code\":\"123\",\"expiration_month\":1,\"expiration_year\":21}}";

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.PAYMENTS_TOKENS, postDataFileData, headers, true);
//        CustomLogging.witeInfo("Response Code : " + response.getStatusLine().getStatusCode());

        CustomLogging.writeInfoLogToReport("Time taken for PAYMENT RECEIPT api request for is " + Duration.between(start, Instant.now()).getSeconds());

        return response;
    }

    @Deprecated
    public static CustomResponse<PaymentResponse> sendAnonymousPaymentAPI(CustomClass obj, ProductsResponse responseObj_ProductByID, PaymentTokensResponse responseObj_PaymentTokens, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String request_AnonymousPayment = Utilities.readFile("src/main/resources/apiRequests/anonymousPayment.json");

        request_AnonymousPayment = request_AnonymousPayment.replace("${tokenID}", responseObj_PaymentTokens.getId());
        request_AnonymousPayment = request_AnonymousPayment.replace("${fingerprint}", responseObj_PaymentTokens.getCard().getFingerprint());

        ProductsResponse.IndividualProduct individualProduct = responseObj_ProductByID.getAllProductsIncludingConvergence().get(0);
        String accountID, productType;
        if (individualProduct.isPartOfConvergenceProduct()) {
            accountID = individualProduct.getConvergenceInvoiceIdentifier();
            productType = "Convergence";
        } else {
            accountID = individualProduct.getAccountId();
            productType = individualProduct.getProductType();
        }
        request_AnonymousPayment = request_AnonymousPayment.replace("${companyCode}", individualProduct.getCompanyCode());
        request_AnonymousPayment = request_AnonymousPayment.replace("${merchantName}", individualProduct.getMerchantName());
        request_AnonymousPayment = request_AnonymousPayment.replace("${productType}", productType);
        request_AnonymousPayment = request_AnonymousPayment.replace("${subscriptionType}", individualProduct.getSubscriptionType());
        request_AnonymousPayment = request_AnonymousPayment.replace("${accountId}", accountID);
        request_AnonymousPayment = request_AnonymousPayment.replace("${amount}", individualProduct.getBalance().toString());
        request_AnonymousPayment = request_AnonymousPayment.replace("${isBillConsolidated}", individualProduct.getIsBillConsolidated());


        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);
//        headers.put("authority", "vault.omise.co");

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.ANONYMOUS_PAYMENT, request_AnonymousPayment, headers, false);

        CustomLogging.writeInfoLogToReport("Time taken for ANONYMOUS PAYMENT api request for is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<PaymentResponse> paymentResponseCustomResponse = new CustomResponse<>(response, PaymentResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.ANONYMOUS_PAYMENT, headers, request_AnonymousPayment, paymentResponseCustomResponse.getResponse()));

        return paymentResponseCustomResponse;
    }


    @Deprecated
    public static CustomResponse<ConverseMainResponse> sendConverseMainAPI(CustomClass obj, UsersDataBean.User user, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String postDataFileData = "{\"query\":\"hi\",\"sessionId\":\"Dummy-Session-ID\",\"previousIntentResponse\":null}";
        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
        headers.put("api-version", "1");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.CONVERSE, postDataFileData, headers);

        CustomLogging.writeInfoLogToReport("Time taken for CONVERSE MAIN api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<ConverseMainResponse> converseMainResponseCustomResponse = new CustomResponse<>(response, ConverseMainResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.CONVERSE, headers, postDataFileData, converseMainResponseCustomResponse.getResponse()));

        return converseMainResponseCustomResponse;
    }

    @Deprecated
    public static CustomResponse<ConverseMainResponse> preLogin_SendConverseMainAPI(CustomClass obj, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String postDataFileData = "{\"query\":\"hi\",\"sessionId\":\"Dummy-Session-ID\",\"previousIntentResponse\":null}";
        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("channel", channel);
        headers.put("api-version", "1");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.CONVERSE, postDataFileData, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRE-LOGIN: CONVERSE MAIN api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<ConverseMainResponse> converseMainResponseCustomResponse = new CustomResponse<>(response, ConverseMainResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.CONVERSE, headers, postDataFileData, converseMainResponseCustomResponse.getResponse()));

        return converseMainResponseCustomResponse;
    }

    @Deprecated
    public static CustomResponse<CouponsResponse> sendCouponsAPI(CustomClass obj, UsersDataBean.User user, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "1");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingGetRequest(ApiUrl.COUPONS, headers);
//        CustomLogging.witeInfo("Response Code : " + response.getStatusLine().getStatusCode());

        CustomLogging.writeInfoLogToReport("Time taken for COUPONS api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<CouponsResponse> couponsResponseCustomResponse = new CustomResponse<>(response, CouponsResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.COUPONS, headers, "", couponsResponseCustomResponse.getResponse()));

        return couponsResponseCustomResponse;
    }

    @Deprecated
    public static CustomResponse<CouponsResponse> preLogin_SendCouponsAPI(CustomClass obj, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("channel", channel);
//        headers.put("api-version", "1");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingGetRequest(ApiUrl.COUPONS, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRE-LOGIN: COUPONS api request for is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<CouponsResponse> couponsResponseCustomResponse = new CustomResponse<>(response, CouponsResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.COUPONS, headers, "", couponsResponseCustomResponse.getResponse()));

        return couponsResponseCustomResponse;
    }

    @Deprecated
    public static CustomResponse<CardsResponse> sendCardsAPI(CustomClass obj, UsersDataBean.User user, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);
        headers.put("ssoid", user.getSsoID());

        HttpResponse response = RestApiUtils.sendingGetRequest(ApiUrl.CARDS, headers);

        CustomLogging.writeInfoLogToReport("Time taken for CARDS api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<CardsResponse> cardsResponseCustomResponse = new CustomResponse<>(response, CardsResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.CARDS, headers, "", cardsResponseCustomResponse.getResponse()));

        return cardsResponseCustomResponse;
    }

    @Deprecated
    public static CustomResponse<TopUpValidityResponse> sendTopUpValidityAPI(CustomClass obj, UsersDataBean.User user, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingGetRequest(ApiUrl.TOPUP_VALIDITY, headers);

        CustomLogging.writeInfoLogToReport("Time taken for TOP-UP VALIDITY api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<TopUpValidityResponse> topUpValidityResponseCustomResponse = new CustomResponse<>(response, TopUpValidityResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.TOPUP_VALIDITY, headers, "", topUpValidityResponseCustomResponse.getResponse()));

        return topUpValidityResponseCustomResponse;
    }

    @Deprecated
    public static CustomResponse<ServiceNumberResponse> sendServiceNumberAPI(CustomClass obj, UsersDataBean.User user, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingGetRequest(ApiUrl.SERVICE_NUMBER, headers);

        CustomLogging.writeInfoLogToReport("Time taken for SERVICE NUMBER api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<ServiceNumberResponse> serviceNumberResponseCustomResponse = new CustomResponse<>(response, ServiceNumberResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.SERVICE_NUMBER, headers, "", serviceNumberResponseCustomResponse.getResponse()));

        return serviceNumberResponseCustomResponse;
    }

    @Deprecated
    public static CustomResponse<ProductsResponse> sendProductsByIdAPI(CustomClass obj, String msisdn, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("channel", channel);
        headers.put("api-version", "3");
        headers.put("platform", platform);
        //TODO: Need to check whether it is required or not
        headers.put("accesstoken", "null");

        String urlString = ApiUrl.PRODUCTS + "/" + msisdn;
        HttpResponse response = RestApiUtils.sendingGetRequest(urlString, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRE-LOGIN: PRODUCTS BY ID api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<ProductsResponse> customResponse = new CustomResponse<>(response, ProductsResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(urlString, headers, "", customResponse.getResponse()));

        return customResponse;

    }

    @Deprecated
    public static CustomResponse<TranslationsResponse> sendTranslationsAPI(CustomClass obj, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("accesstoken", "null");
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingGetRequest(ApiUrl.TRANSLATIONS, headers);

        CustomLogging.writeInfoLogToReport("Time taken for TRANSLATIONS api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<TranslationsResponse> translationsResponseCustomResponse = new CustomResponse<>(response, TranslationsResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.TRANSLATIONS, headers, "", translationsResponseCustomResponse.getResponse()));

        return translationsResponseCustomResponse;
    }

    @Deprecated
    public static CustomResponse<ProductDetailsResponse_Depricated> sendProductDetailsAPI(CustomClass obj, UsersDataBean.User user, ProductsResponse.IndividualProduct product, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String requestString = "{\"productId\":\"" + product.getProductId() + "\",\"productType\":\"" + product.getProductType() + "\",\"subscriberId\":\"" + product.getSubscriberId() + "\",\"subscriptionType\":\"" + product.getSubscriptionType() + "\",\"customerId\":\"" + product.getCustomerId() + "\",\"isShared\":false}";

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.PRODUCT_DETAILS, requestString, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRODUCT DETAILS api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<ProductDetailsResponse_Depricated> productDetailsResponseCustomResponse = new CustomResponse<>(response, ProductDetailsResponse_Depricated.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.PRODUCT_DETAILS, headers, requestString, productDetailsResponseCustomResponse.getResponse()));

        return productDetailsResponseCustomResponse;

    }

    @Deprecated
    public static CustomResponse<ProductDetailsResponse_Depricated> sendProductDetailsAPIPreLogin(CustomClass obj, UsersDataBean.User user, ProductsResponse.IndividualProduct product, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String requestString = "{\"productId\":\"" + product.getProductId() + "\",\"productType\":\"" + product.getProductType() + "\",\"subscriberId\":\"" + product.getSubscriberId() + "\",\"subscriptionType\":\"" + product.getSubscriptionType() + "\",\"customerId\":\"" + product.getCustomerId() + "\",\"isShared\":false}";

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("channel", channel);
        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.PRODUCT_DETAILS, requestString, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRE-LOGIN: PRODUCT DETAILS api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<ProductDetailsResponse_Depricated> productDetailsResponseCustomResponse = new CustomResponse<>(response, ProductDetailsResponse_Depricated.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.PRODUCT_DETAILS, headers, requestString, productDetailsResponseCustomResponse.getResponse()));

        return productDetailsResponseCustomResponse;

    }

    @Deprecated
    public static CustomResponse<HashMap> sendPreferencesAPI(CustomClass obj, UsersDataBean.User user, List<String> allAccountIDs, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String allAccountIDsRequest = allAccountIDs.stream().map(indAccountID -> "{\"accountId\":\"" + indAccountID + "\",\"isConvergence\":false,\"isShared\":false}").collect(Collectors.joining(","));
        String requestString = "{\"accountList\":[" + allAccountIDsRequest + "]}";

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.PREFERENCES, requestString, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PREFERENCES api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<HashMap> hashMapCustomResponse = new CustomResponse<>(response, HashMap.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.PREFERENCES, headers, requestString, hashMapCustomResponse.getResponse()));

        return hashMapCustomResponse;
    }

    @Deprecated
    public static CustomResponse<HashMap> sendAllocationStatusAPI(CustomClass obj, UsersDataBean.User user, List<String> allAccountIDs, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String allAccountIDsRequest = allAccountIDs.stream().map(indAccountID -> "\"" + indAccountID + "\"").collect(Collectors.joining(","));
        String requestString = "{\"accountIds\":[" + allAccountIDsRequest + "]}";

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.ALLOCATION_STATUS, requestString, headers);

        CustomLogging.writeInfoLogToReport("Time taken for ALLOCATION STATUS api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<HashMap> hashMapCustomResponse = new CustomResponse<>(response, HashMap.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.ALLOCATION_STATUS, headers, requestString, hashMapCustomResponse.getResponse()));

        return hashMapCustomResponse;
    }

    @Deprecated
    public static CustomResponse<HashMap> sendAllocationStatusAPI_preLogin(CustomClass obj, List<String> allAccountIDs, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String allAccountIDsRequest = allAccountIDs.stream().map(indAccountID -> "\"" + indAccountID + "\"").collect(Collectors.joining(","));
        String requestString = "{\"accountIds\":[" + allAccountIDsRequest + "]}";

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.ALLOCATION_STATUS, requestString, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRE-LOGIN: ALLOCATION STATUS api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<HashMap> hashMapCustomResponse = new CustomResponse<>(response, HashMap.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.ALLOCATION_STATUS, headers, requestString, hashMapCustomResponse.getResponse()));

        return hashMapCustomResponse;
    }

    @Deprecated
    public static CustomResponse<BillDetailsResponse> sendBillDetailsAPI(CustomClass obj, UsersDataBean.User user, String accountIDs, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        String url = ApiUrl.BILL_DETAILS.replace("${accountIDs}", accountIDs);
        HttpResponse response = RestApiUtils.sendingGetRequest(url, headers);

        CustomLogging.writeInfoLogToReport("Time taken for BILL DETAILS api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<BillDetailsResponse> billDetailsResponseCustomResponse = new CustomResponse<>(response, BillDetailsResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(url, headers, "", billDetailsResponseCustomResponse.getResponse()));

        return billDetailsResponseCustomResponse;

    }

    @Deprecated
    public static CustomResponse<PaymentHistoryResponse> sendPaymentHistoryAPI(CustomClass obj, UsersDataBean.User user, ProductsResponse.IndividualProduct product, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        String url = ApiUrl.PAYMENT_HISTORY.replace("${accountID}", product.getAccountId());
        HttpResponse response = RestApiUtils.sendingGetRequest(url, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PAYMENT HISTORY api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<PaymentHistoryResponse> paymentHistoryResponseCustomResponse = new CustomResponse<>(response, PaymentHistoryResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(url, headers, "", paymentHistoryResponseCustomResponse.getResponse()));

        return paymentHistoryResponseCustomResponse;

    }

    @Deprecated
    public static CustomResponse<PricePlanResponse> sendPricePlanAPI(CustomClass obj, UsersDataBean.User user, ProductsResponse.IndividualProduct product, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", product.getProductId());
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        String url = ApiUrl.PRICE_PLAN.replace("${msisdn}", product.getProductId());
        HttpResponse response = RestApiUtils.sendingGetRequest(url, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRICE PLAN api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<PricePlanResponse> pricePlanResponseCustomResponse = new CustomResponse<>(response, PricePlanResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(url, headers, "", pricePlanResponseCustomResponse.getResponse()));

        return pricePlanResponseCustomResponse;

    }

    @Deprecated
    public static CustomResponse<PricePlanTncResponse> sendPricePlanTncAPI(CustomClass obj, UsersDataBean.User user, ProductsResponse.IndividualProduct product, PricePlanResponse.RecommendedPricePlan recommendedPricePlan, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", product.getProductId());
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        String url = ApiUrl.PRICE_PLAN_TNC.replace("${pricePlanCode}", recommendedPricePlan.getOfferCode());
        HttpResponse response = RestApiUtils.sendingGetRequest(url, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRICE PLAN TERMS & CONDITIONS api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<PricePlanTncResponse> pricePlanTncResponseCustomResponse = new CustomResponse<>(response, PricePlanTncResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(url, headers, "", pricePlanTncResponseCustomResponse.getResponse()));

        return pricePlanTncResponseCustomResponse;

    }

    @Deprecated
    public static CustomResponse<BillingHistoryResponse> sendBillingHistoryAPI(CustomClass obj, UsersDataBean.User user, ProductsResponse.IndividualProduct product, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String requstString = "{\"accountIds\":[\"" + product.getAccountId() + "\"],\"pageSize\":12}";

        CustomLogging.writeInfoLogToReport("Time taken for BILLING HISTORY api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        return sendBillingHistoryAPI(obj, user, channel, platform, requstString);

    }

    @Deprecated
    private static CustomResponse<BillingHistoryResponse> sendBillingHistoryAPI(CustomClass obj, UsersDataBean.User user, String channel, String platform, String requstString) throws IOException {
        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.BILLING_HISTORY, requstString, headers);

        CustomLogging.writeInfoLogToReport("Time taken for BILLING HISTORY api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<BillingHistoryResponse> billingHistoryResponseCustomResponse = new CustomResponse<>(response, BillingHistoryResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.BILLING_HISTORY, headers, requstString, billingHistoryResponseCustomResponse.getResponse()));

        return billingHistoryResponseCustomResponse;
    }

    @Deprecated
    public static CustomResponse<BillingHistoryResponse> sendBillingHistoryAPI(CustomClass obj, UsersDataBean.User user, ProductsResponse.ConvergenceProduct product, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String accountIDs = product.getLinkedProducts().stream().map(item -> "\"" + item.getAccountId() + "\"").collect(Collectors.joining(",", "[", "]"));
        String requstString = "{\"accountIds\":" + accountIDs + ",\"pageSize\":12, \"convergenceInvoiceIdentifier\":\"" + product.getLinkedProducts().get(0).getConvergenceInvoiceIdentifier() + "\"}";

        CustomLogging.writeInfoLogToReport("Time taken for BILLING HISTORY api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        return sendBillingHistoryAPI(obj, user, channel, platform, requstString);

    }

    @Deprecated
    public static CustomResponse<InvoicesResponse> sendInvoicesAPI(CustomClass obj, UsersDataBean.User user, PaymentHistoryResponse.Payment.IndPaymentHistory.Invoice invoice, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String requstString = "{\"invoiceNos\":[\"" + invoice.getInvoiceNo() + "\"]}";

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.INVOICES, requstString, headers);

        CustomLogging.writeInfoLogToReport("Time taken for INVOICES api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<InvoicesResponse> invoicesResponseCustomResponse = new CustomResponse<>(response, InvoicesResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.INVOICES, headers, requstString, invoicesResponseCustomResponse.getResponse()));

        return invoicesResponseCustomResponse;

    }

    @Deprecated
    public static CustomResponse<TypeResponse> sendTypeAPI(CustomClass obj, ProductsResponse.IndividualProduct product, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("channel", channel);
        headers.put("api-version", "3");
        headers.put("platform", platform);

        String url = ApiUrl.TYPE.replace("${msisdn}", product.getProductId());
        HttpResponse response = RestApiUtils.sendingGetRequest(url, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRE-LOGIN: TYPE api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<TypeResponse> typeResponseCustomResponse = new CustomResponse<>(response, TypeResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(url, headers, "", typeResponseCustomResponse.getResponse()));

        return typeResponseCustomResponse;

    }

    @Deprecated
    public static CustomResponse<RequestResponse> sendRequestAPI(CustomClass obj, ProductsResponse.IndividualProduct product, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        String postRequest = "{\"msisdn\":\"" + product.getProductId() + "\"}";

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer null");
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.REQUEST, postRequest, headers);

        CustomLogging.writeInfoLogToReport("Time taken for PRE-LOGIN: REQUEST api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<RequestResponse> requestResponseCustomResponse = new CustomResponse<>(response, RequestResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.REQUEST, headers, postRequest, requestResponseCustomResponse.getResponse()));

        return requestResponseCustomResponse;

    }

    @Deprecated
    public static CustomResponse<ToppingsResponse> sendToppingsAPI(CustomClass obj, UsersDataBean.User user, ProductsResponse.IndividualProduct product, String channel, String platform) throws IOException {

        Instant start = Instant.now();

        Map<String, String> headers = new HashMap<>();
        headers.put("msisdn", "null");
        headers.put("ms-access-token", "Bearer " + user.getMsAccessToken());
        headers.put("channel", channel);
//        headers.put("api-version", "3");
        headers.put("platform", platform);

        String url = ApiUrl.TOPPINGS
                .replace("${ProductType}", product.getProductType())
                .replace("${msisdn}", product.getProductId())
                .replace("${SubscriptionType}", product.getSubscriptionType());
        HttpResponse response = RestApiUtils.sendingGetRequest(url, headers);

        CustomLogging.writeInfoLogToReport("Time taken for TOPPINGS api request for " + user.getUserName() + " is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<ToppingsResponse> toppingsResponseCustomResponse = new CustomResponse<>(response, ToppingsResponse.class);
        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(url, headers, "", toppingsResponseCustomResponse.getResponse()));

        return toppingsResponseCustomResponse;


    }

    //TODO: need to refactor
    public static void sendAutomationReportToSlack(File reportZipFile) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://slack.com/api/files.upload");

        post.addHeader("Authorization", "Bearer xoxp-227233942564-273402460597-437596878340-3abd16e940bd1eb0b52a959adec4d231");
//        post.addHeader("Authorization", "Bearer xoxb-227233942564-437754990563-ImnRatl5p5RH3NOguuPvg8Ft");

        InputStream inputStream = new FileInputStream(reportZipFile);

        FileBody fileBody = new FileBody(reportZipFile, ContentType.DEFAULT_BINARY);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        builder.addBinaryBody("file", inputStream, ContentType.create("application/zip"), reportZipFile.getName());
        builder.addTextBody("channels", "test");
        builder.addTextBody("filename", reportZipFile.getName());

        HttpEntity entity = builder.build();

        post.setEntity(entity);
        HttpResponse response = client.execute(post);

        CustomLogging.witeInfo(response.getStatusLine().getStatusCode());
    }

    public static CustomResponse<FindPlanOptionsResponse> sendFindPlanOptionsAPI(CustomClass obj) throws IOException {

        Instant start = Instant.now();

        String postDataFileData = "{\"data\":[{\"name\":\"smartfinderoptions\",\"category\":\"FRONTEND_CONFIG\"}]}";
        CommonEnums.AppEnvironment appEnvironment = TestData.getConfigData().getAppEnvironment();

        Map<String, String> headers = new HashMap<>();

        headers.put("origin", appEnvironment.getWebAppUrl());
        headers.put("referer", appEnvironment.getWebAppUrl() + "/smartFinder");
        headers.put("authority", appEnvironment.getAuthority());

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.FIND_PLAN_OPTIONS, postDataFileData, headers);
//        CustomLogging.witeInfo("Response Code : " + response.getStatusLine().getStatusCode());

        CustomLogging.writeInfoLogToReport("Time taken for PAYMENT TOKENS api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<FindPlanOptionsResponse> findPlanOptionsResponseCustomResponse = new CustomResponse<>(response, FindPlanOptionsResponse.class);

        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.FIND_PLAN_OPTIONS, headers, postDataFileData, findPlanOptionsResponseCustomResponse.getResponse()));
        return findPlanOptionsResponseCustomResponse;

    }

    public static CustomResponse<DobRulesResponse> sendDobRulesAPI(CustomClass obj) throws IOException {

        Instant start = Instant.now();

        String postDataFileData = "{\"data\":[{\"name\":\"pretogglerdobvalidationrules\",\"category\":\"FRONTEND_CONFIG\"}]}";
        CommonEnums.AppEnvironment appEnvironment = TestData.getConfigData().getAppEnvironment();

        Map<String, String> headers = new HashMap<>();

        headers.put("origin", appEnvironment.getWebAppUrl());
        headers.put("referer", appEnvironment.getWebAppUrl() + "/smartFinder");
        headers.put("authority", appEnvironment.getAuthority());

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.FIND_PLAN_OPTIONS, postDataFileData, headers);
//        CustomLogging.witeInfo("Response Code : " + response.getStatusLine().getStatusCode());

        CustomLogging.writeInfoLogToReport("Time taken for PAYMENT TOKENS api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<DobRulesResponse> findPlanOptionsResponseCustomResponse = new CustomResponse<>(response, DobRulesResponse.class);

        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.FIND_PLAN_OPTIONS, headers, postDataFileData, findPlanOptionsResponseCustomResponse.getResponse()));
        return findPlanOptionsResponseCustomResponse;

    }

    public static CustomResponse<FindPlanResultsResponse> sendFindPlanResultsAPI(CustomClass obj) throws IOException {

        Instant start = Instant.now();

        String postDataFileData = "{\"data\":[{\"name\":\"smartfinderlogic\",\"category\":\"FRONTEND_CONFIG\"}]}";
        CommonEnums.AppEnvironment appEnvironment = TestData.getConfigData().getAppEnvironment();

        Map<String, String> headers = new HashMap<>();

        headers.put("origin", appEnvironment.getWebAppUrl());
        headers.put("referer", appEnvironment.getWebAppUrl() + "/smartFinder");
        headers.put("authority", appEnvironment.getAuthority());

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.FIND_PLAN_OPTIONS, postDataFileData, headers);
//        CustomLogging.witeInfo("Response Code : " + response.getStatusLine().getStatusCode());

        CustomLogging.writeInfoLogToReport("Time taken for PAYMENT TOKENS api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<FindPlanResultsResponse> findPlanOptionsResponseCustomResponse = new CustomResponse<>(response, FindPlanResultsResponse.class);

        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.FIND_PLAN_OPTIONS, headers, postDataFileData, findPlanOptionsResponseCustomResponse.getResponse()));
        return findPlanOptionsResponseCustomResponse;

    }

    public static CustomResponse<ProductDetailsResponse> sendProductDetailsAPI(CustomClass obj) throws IOException {

        Instant start = Instant.now();

        String postDataFileData = "{\"data\":[{\"name\":\"productdetails\",\"category\":\"PRODUCT\"}]}";
        CommonEnums.AppEnvironment appEnvironment = TestData.getConfigData().getAppEnvironment();

        Map<String, String> headers = new HashMap<>();

        headers.put("origin", appEnvironment.getWebAppUrl());
        headers.put("referer", appEnvironment.getWebAppUrl() + "/");
        headers.put("authority", appEnvironment.getAuthority());

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.FIND_PLAN_OPTIONS, postDataFileData, headers);
//        CustomLogging.witeInfo("Response Code : " + response.getStatusLine().getStatusCode());

        CustomLogging.writeInfoLogToReport("Time taken for PAYMENT TOKENS api request is " + Duration.between(start, Instant.now()).getSeconds());

        CustomResponse<ProductDetailsResponse> ProductDetailsResponseCustomResponse = new CustomResponse<>(response, ProductDetailsResponse.class);

        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.FIND_PLAN_OPTIONS, headers, postDataFileData, ProductDetailsResponseCustomResponse.getResponse()));
        return ProductDetailsResponseCustomResponse;

    }

    public static CustomResponse<GetProductsApiResponse> sendGetProductsAPI(CustomClass obj, GetAQuoteBean getAQuoteBean) throws IOException {

        Instant start = Instant.now();

        String postDataFileData = "{\"apiKey\":\"" + getAQuoteBean.getIndProductDetails().getKey().toLowerCase() + "\",\"dob\":\"" + getAQuoteBean.getDay() + "." + getAQuoteBean.getMonth() + "." + getAQuoteBean.getYear() + "\",\"smokingStatus\":\"" + getAQuoteBean.getSmoke() + "\",\"gender\":\"" + getAQuoteBean.getGender() + "\"}";
        CommonEnums.AppEnvironment appEnvironment = TestData.getConfigData().getAppEnvironment();

        Map<String, String> headers = new HashMap<>();

        headers.put("origin", appEnvironment.getWebAppUrl());
        headers.put("referer", appEnvironment.getWebAppUrl() + "/product-toggler");
        headers.put("authority", appEnvironment.getAuthority());

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.QUOT_PRODUCTS, postDataFileData, headers);
//        CustomLogging.witeInfo("Response Code : " + response.getStatusLine().getStatusCode());

        CustomLogging.writeInfoLogToReport("Time taken for PAYMENT TOKENS api request is " + Duration.between(start, Instant.now()).getSeconds());
        CustomResponse<GetProductsApiResponse> getProductsResponse = new CustomResponse<>(response, GetProductsApiResponse.class);

        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.QUOT_PRODUCTS, headers, postDataFileData, getProductsResponse.getResponse()));
        return getProductsResponse;

    }

    public static CustomResponse<GetProductsApiResponse> sendEditGetProductsAPI(CustomClass obj, GetAQuoteBean getAQuoteBean, PossibleFiguresBean possibleFiguresBean) throws IOException {

        Instant start = Instant.now();

        String postDataFileData = Files.lines(Paths.get(FilePaths.API_REQUEST_EDIT_POSSIBLE_FIGURES))
                .map(item -> item.replace("${apiKey}", getAQuoteBean.getIndProductDetails().getKey().toLowerCase()))
                .map(item -> item.replace("${day}", getAQuoteBean.getDay()))
                .map(item -> item.replace("${month}", getAQuoteBean.getMonth()))
                .map(item -> item.replace("${year}", String.valueOf(getAQuoteBean.getYear())))
                .map(item -> item.replace("${smoke}", getAQuoteBean.getSmoke().toString()))
                .map(item -> item.replace("${gender}", getAQuoteBean.getGender().toString()))
                .map(item -> item.replace("${ageInYears}", JavaUtils.getAgeInYears(getAQuoteBean.getDay(), getAQuoteBean.getMonth(), getAQuoteBean.getYear())))
                .map(item -> item.replace("${premiumTerm}", possibleFiguresBean.getPremiumTerm().getExpGUIValue()))
                //TODO
                .map(item -> item.replace("${premiumTermType}", "YR"))
//                .map(item -> item.replace("${premiumAmount}", possibleFiguresBean.getPremiumAmount()))
                .map(item -> item.replace("${policyTerm}", possibleFiguresBean.getPolicyTerm()))
                .map(item -> item.replace("${PolicyTermType}", "YR"))
                .map(item -> item.replace("${SumAssured}", possibleFiguresBean.getSumAssured()))
//                .map(item -> item.replace("", ""))
                .collect(Collectors.joining("\n"));;

        CommonEnums.AppEnvironment appEnvironment = TestData.getConfigData().getAppEnvironment();

        Map<String, String> headers = new HashMap<>();

        headers.put("origin", appEnvironment.getWebAppUrl());
        headers.put("referer", appEnvironment.getWebAppUrl() + "/product-toggler");
        headers.put("authority", appEnvironment.getAuthority());

        HttpResponse response = RestApiUtils.sendingPostRequest(ApiUrl.QUOT_PRODUCTS, postDataFileData, headers);
//        CustomLogging.witeInfo("Response Code : " + response.getStatusLine().getStatusCode());

        CustomLogging.writeInfoLogToReport("Time taken for PAYMENT TOKENS api request is " + Duration.between(start, Instant.now()).getSeconds());
        CustomResponse<GetProductsApiResponse> getProductsResponse = new CustomResponse<>(response, GetProductsApiResponse.class);

        TestNGUtils.addApiCallDetailsObj(obj.getContext(), new ApiCallDetails(ApiUrl.QUOT_PRODUCTS, headers, postDataFileData, getProductsResponse.getResponse()));
        return getProductsResponse;

    }

}