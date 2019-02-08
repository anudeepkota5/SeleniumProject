package com.mckinsey.lime.apiTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.ProductDetailsResponse_Depricated;
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
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class ProductDetailsTest extends ApiBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public ProductDetailsTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    @Test(groups = {"apiFlowTest", "subscriberAPI"}, description = "Test Product Details API response code")
    public void testProductDetailsResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code of " + indUser.getUserName());

        List<ProductsResponse.IndividualProduct> allProducts = customResponse_Products.getResponseObj().getAllProductsIncludingConvergence();

        allProducts.forEach(indProduct -> {
            try {
                CustomResponse<ProductDetailsResponse_Depricated> customResponse_ProductDetails = ApiRequests.sendProductDetailsAPI(customClassObj, indUser, indProduct, configData.getApi_channel(), configData.getApi_platform());

                CustomAssertions.assertResponseCodeAs200(customResponse_ProductDetails.getResponseCode(), "Verified the PRODUCTS_DETAILS API response code of " + indProduct.getProductId());

            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });

    }

    @Test(groups = {"apiFlowTest", "subscriberAPI"}, description = "Test Product Details API response code in preLogin for TOL & TVS")
    public void testCheckUsageResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code of " + indUser.getUserName());

        List<ProductsResponse.IndividualProduct> allTOL = customResponse_Products.getResponseObj().getTolProducts(true);
        List<ProductsResponse.IndividualProduct> allTVS = customResponse_Products.getResponseObj().getTvsProducts(true);
        ArrayList<ProductsResponse.IndividualProduct> allTolTvs = new ArrayList<>();
        allTolTvs.addAll(allTOL);
        allTolTvs.addAll(allTVS);

        allTolTvs.forEach(indProduct -> {
            try {
                CustomResponse<ProductDetailsResponse_Depricated> customResponse_ProductDetails = ApiRequests.sendProductDetailsAPIPreLogin(customClassObj, indUser, indProduct, configData.getApi_channel(), configData.getApi_platform());

                CustomAssertions.assertResponseCodeAs200(customResponse_ProductDetails.getResponseCode(), "Verified the PRE_LOGIN_PRODUCTS_DETAILS API response code of " + indProduct.getProductId());

            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });

    }


}
