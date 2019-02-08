package com.mckinsey.lime.apiTests;

import com.mckinsey.lime.apiTestBeans.BillingHistoryResponse;
import com.mckinsey.lime.apiTestBeans.CustomResponse;
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
public class BillingHistoryTest extends ApiBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public BillingHistoryTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    //TODO: What is the reson for firing PREFERENCES api post BillingHistory API call
    @Test(groups = {"apiFlowTest", "subscriberAPI"}, description = "Test Billing History API response code")
    public void testBillingHistoryResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code of " + indUser.getUserName());

        List<ProductsResponse.IndividualProduct> allNonConvergenceProducts = customResponse_Products.getResponseObj().getAllProductsWithOutConvergence();

        allNonConvergenceProducts.forEach(indProduct -> {
            try {
                CustomResponse<BillingHistoryResponse> customResponse_BillingHistory = ApiRequests.sendBillingHistoryAPI(customClassObj, indUser, indProduct, configData.getApi_channel(), configData.getApi_platform());

                CustomAssertions.assertResponseCodeAs200(customResponse_BillingHistory.getResponseCode(), "Verified the BILLING_HISTORY API response code of " + indUser.getUserName());

            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });

        List<ProductsResponse.ConvergenceProduct> allConv = customResponse_Products.getResponseObj().getProducts().getConv();
        allConv.forEach(indConvProduct -> {
            try {
                CustomResponse<BillingHistoryResponse> customResponse_BillingHistory = ApiRequests.sendBillingHistoryAPI(customClassObj, indUser, indConvProduct, configData.getApi_channel(), configData.getApi_platform());

                CustomAssertions.assertResponseCodeAs200(customResponse_BillingHistory.getResponseCode(), "Verified the BILLING_HISTORY API response code of " + indUser.getUserName());

            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });


    }
}
