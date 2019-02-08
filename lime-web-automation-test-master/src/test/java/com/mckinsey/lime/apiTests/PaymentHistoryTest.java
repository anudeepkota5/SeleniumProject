package com.mckinsey.lime.apiTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.InvoicesResponse;
import com.mckinsey.lime.apiTestBeans.PaymentHistoryResponse;
import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

@Deprecated
public class PaymentHistoryTest extends ApiBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public PaymentHistoryTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    @Test(groups = {"apiFlowTest", "paymentAPI"}, description = "Test Payment History API response code")
    public void testPaymentHistoryResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code of " + indUser.getUserName());

        List<ProductsResponse.IndividualProduct> allProducts = customResponse_Products.getResponseObj().getAllProductsIncludingConvergence();

        allProducts.forEach(indProduct -> {
            try {
                CustomResponse<PaymentHistoryResponse> customResponse_PaymentHistory = ApiRequests.sendPaymentHistoryAPI(customClassObj, indUser, indProduct, configData.getApi_channel(), configData.getApi_platform());

                CustomAssertions.assertResponseCodeAs200(customResponse_PaymentHistory.getResponseCode(), "Verified the PAYMENT_HISTORY API response code of " + indProduct.getProductId());

                customResponse_PaymentHistory.getResponseObj().getPayments().forEach(payment -> payment.getPaymentHistory().forEach(paymentHistory -> paymentHistory.getInvoices().forEach(invoice ->
                {
                    try {

                        CustomResponse<InvoicesResponse> customResponse_Invoices = ApiRequests.sendInvoicesAPI(customClassObj, indUser, invoice, configData.getApi_channel(), configData.getApi_platform());

                        CustomAssertions.assertResponseCodeAs200(customResponse_Invoices.getResponseCode(), "Verified the PAYMENT_HISTORY -> INVOICES API response code of " + indProduct.getProductId());

                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })));

            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });


    }
}
