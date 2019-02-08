package com.mckinsey.lime.apiTests;

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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Deprecated
public class AllocationStatusTest extends ApiBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public AllocationStatusTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    @Test(groups = {"apiFlowTest", "paymentAPI"}, description = "Test Allocation-Status API response code")
    public void testAllocationStatusResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code of " + indUser.getUserName());
        ProductsResponse productsResponseObj = customResponse_Products.getResponseObj();

        List<String> allAccountIDs4NonConvergenceProducts = productsResponseObj.getAllAccountIDs4NonConvergenceProducts();
        List<String> allConvAccountIDs = productsResponseObj.getAllConvergenceInvoiceIdentifiers();
        List<String> allAccountIDs = Stream.concat(allAccountIDs4NonConvergenceProducts.stream(), allConvAccountIDs.stream()).collect(Collectors.toList());

        try {
            CustomResponse<HashMap> customResponse_AllocationStatus = ApiRequests.sendAllocationStatusAPI(customClassObj, indUser, allAccountIDs, configData.getApi_channel(), configData.getApi_platform());

            CustomAssertions.assertResponseCodeAs200(customResponse_AllocationStatus.getResponseCode(), "Verified the ALLOCATION-STATUS API response code of " + indUser.getUserName());

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }
}
