package com.mckinsey.lime.apiTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.PricePlanResponse;
import com.mckinsey.lime.apiTestBeans.PricePlanTncResponse;
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
public class PricePlanTest extends ApiBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public PricePlanTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    @Test(groups = {"apiFlowTest", "contentAPI"}, description = "Test Price Plan API response code")
    public void testPricePlanResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code of " + indUser.getUserName());

        List<ProductsResponse.IndividualProduct> allProducts = customResponse_Products.getResponseObj().getAllProducts4CPP();

        allProducts.forEach(indProduct -> {
            try {
                CustomResponse<PricePlanResponse> customResponse_PricePlan = ApiRequests.sendPricePlanAPI(customClassObj, indUser, indProduct, configData.getApi_channel(), configData.getApi_platform());

                CustomAssertions.assertResponseCodeAs200(customResponse_PricePlan.getResponseCode(), "Verified the PRICE_PLAN API response code of " + indProduct.getProductId());

                customResponse_PricePlan.getResponseObj().getRecommendedPricePlans().forEach(recommendedPricePlan ->
                {
                    try {

                        CustomResponse<PricePlanTncResponse> customResponse_PricePlanTnc = ApiRequests.sendPricePlanTncAPI(customClassObj, indUser, indProduct, recommendedPricePlan, configData.getApi_channel(), configData.getApi_platform());

                        CustomAssertions.assertResponseCodeAs200(customResponse_PricePlanTnc.getResponseCode(), "Verified the PRICE_PLAN_TNC API response code of " + indProduct.getProductId());

                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });


    }
}
