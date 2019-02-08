package com.mckinsey.lime.apiTests;

import com.mckinsey.lime.apiTestBeans.BillDetailsResponse;
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
import java.util.stream.Collectors;

@Deprecated
public class BillDetailsTest extends ApiBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public BillDetailsTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    @Test(groups = {"apiFlowTest", "subscriberAPI"}, description = "Test Bill Details API response code")
    public void testBillDetailsResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code of " + indUser.getUserName());

        List<ProductsResponse.IndividualProduct> allProducts = customResponse_Products.getResponseObj().getAllProducts(false);
        List<ProductsResponse.ConvergenceProduct> allConvBundles = customResponse_Products.getResponseObj().getProducts().getConv();

        allProducts.forEach(indProduct -> {
            verifyBillDetailsAPI(customClassObj, indUser, "accountId=" + indProduct.getAccountId());
        });

        allConvBundles.forEach(indBundle -> {
            String accountIDsString = indBundle.getLinkedProducts().stream().map(item -> "accountId=" + item.getAccountId()).collect(Collectors.joining("&"));
            accountIDsString = accountIDsString + "&convergenceInvoiceIdentifier=" + indBundle.getLinkedProducts().get(0).getConvergenceInvoiceIdentifier();

            verifyBillDetailsAPI(customClassObj, indUser, accountIDsString);
        });

    }

    private void verifyBillDetailsAPI(CustomClass customClassObj, UsersDataBean.User indUser, String accountIDsString) {
        try {
            CustomResponse<BillDetailsResponse> customResponse_BillDetails = ApiRequests.sendBillDetailsAPI(customClassObj, indUser, accountIDsString, configData.getApi_channel(), configData.getApi_platform());

            CustomAssertions.assertResponseCodeAs200(customResponse_BillDetails.getResponseCode(), "Verified the BILL_DETAILS API response code of " + indUser.getUserName());

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
