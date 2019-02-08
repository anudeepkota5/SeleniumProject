package com.mckinsey.lime.paymentAPIs;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.PaymentResponse;
import com.mckinsey.lime.apiTestBeans.PaymentTokensResponse;
import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.init.BaseTest;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogStrings;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public class AnonymousPaymentTest extends BaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public AnonymousPaymentTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    @Test(groups = {"apiFlowTest", "paymentAPI"}, description = "Test ProductByID API response code")
    public void testAnonymousPaymentResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code of " + indUser.getUserName());

        List<ProductsResponse.IndividualProduct> allProducts = customResponse_Products.getResponseObj().getAllProducts(true);

        List<String> allProductIDs = allProducts.stream().map(item -> item.getProductId()).collect(Collectors.toList());
        if (allProductIDs.size() != 0) {

            allProductIDs.forEach(item -> {
                try {
                    CustomResponse<ProductsResponse> customResponse_ProductByID = ApiRequests.sendProductsByIdAPI(customClassObj, item, configData.getApi_channel(), configData.getApi_platform());

                    CustomAssertions.assertResponseCodeAs200(customResponse_ProductByID.getResponseCode(), "Verified the PRODUCTS_BY_ID API response code of " + item);

                    ProductsResponse responseObj_ProductByID = customResponse_ProductByID.getResponseObj();
                    double totalbalance = responseObj_ProductByID.getAllProductsIncludingConvergence().stream()
                            .map(ProductsResponse.IndividualProduct::getBalance)
                            .mapToDouble(Double::valueOf)
                            .sum();
                    if (totalbalance > 0) {
                        CustomResponse<PaymentTokensResponse> customResponse_PaymentTokens = ApiRequests.sendPaymentTokensAPI(customClassObj, configData.getApi_channel(), configData.getApi_platform());

                        CustomAssertions.assertResponseCodeAs200(customResponse_PaymentTokens.getResponseCode(), "Verified the Payments -> Tokens API response code of ");

                        CustomResponse<PaymentResponse> customeResponse_AnonymousPayment = ApiRequests.sendAnonymousPaymentAPI(customClassObj, responseObj_ProductByID, customResponse_PaymentTokens.getResponseObj(), configData.getApi_channel(), configData.getApi_platform());

                        CustomAssertions.assertResponseCodeAs200(customeResponse_AnonymousPayment.getResponseCode(), "Verified the Anonymous Payment API response code of " + item);

                    }

                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });

        } else {
            CustomLogging.writeToReport(CustomLogStrings.SKIPPED + "no products available for current user to send PRODUCTS_BY_ID api call: " + indUser.getUserName());

        }


    }

}
