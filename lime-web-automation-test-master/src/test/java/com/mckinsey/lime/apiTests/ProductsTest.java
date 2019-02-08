package com.mckinsey.lime.apiTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
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
public class ProductsTest extends ApiBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public ProductsTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    @Test(groups = {"apiFlowTest", "subscriberAPI"}, description = "Test Products API response code")
    public void testProductsResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code of " + indUser.getUserName());


    }

    @Test(groups = {"apiFlowTest", "subscriberAPI"}, description = "Test ProductByID API response code")
    public void testProductsByIdResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code of " + indUser.getUserName());

        List<ProductsResponse.IndividualProduct> allProducts = customResponse_Products.getResponseObj().getAllProducts(true);

        List<String> allProductIDs = allProducts.stream().map(ProductsResponse.IndividualProduct::getProductId).collect(Collectors.toList());
        if (allProductIDs.size() != 0) {

            allProductIDs.forEach(item -> {
                try {
                    CustomResponse<ProductsResponse> customResponse_ProductsByID = ApiRequests.sendProductsByIdAPI(customClassObj, item, configData.getApi_channel(), configData.getApi_platform());

                    CustomAssertions.assertResponseCodeAs200(customResponse_ProductsByID.getResponseCode(), "Verified the PRODUCTS_BY_ID API response code of " + item);

                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });

        } else {
            CustomLogging.writeToReport(CustomLogStrings.SKIPPED + "no products available for current user to send PRODUCTS_BY_ID api call: " + indUser.getUserName());

        }


    }

}
