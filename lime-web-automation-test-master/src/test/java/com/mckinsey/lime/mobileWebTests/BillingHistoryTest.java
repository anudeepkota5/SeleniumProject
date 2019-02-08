package com.mckinsey.lime.mobileWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.mobilePages.*;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public class BillingHistoryTest extends MobileWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public BillingHistoryTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    //@AfterMethod
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LandingPage landingPage = new LandingPage(getDriver());

            landingPage.logOutFromApp();
        }
    }

    @Test(groups = {"mobileWeb"}, description = "Billing History Screen")
    public void testBillingHistory(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        LandingPage landingPage = new LandingPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        BillsAndUsagePage billsAndUsagePage = new BillsAndUsagePage(getDriver());
        SideMenu sideMenu = new SideMenu(getDriver());
        BillingHistoryPage billingHistoryPage = new BillingHistoryPage(getDriver());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        if (CustomAssertions.assertText(customClassObj, customResponse_Products.getResponseCode(), 200, "Verified the PRODUCTS API response code of " + indUser.getUserName() + "as 200")) {

            loginPage.loginIntoTrueApplicationFromLandingPage(indUser.getUserName(), indUser.getPassWord());

            sideMenu.navigate2BillingHistory();

            billingHistoryPage.clickDropDown();

            final List<String> productsInDropDown = billingHistoryPage.getProductsInDropDown();

            final List<String> expected = customResponse_Products.getResponseObj().getProductsForBillingHistory().stream()
                    .map(item -> {

                        if (item instanceof ProductsResponse.ConvergenceProduct) {
                            return ((ProductsResponse.ConvergenceProduct) item).getName();
                        } else {
                            return ((ProductsResponse.IndividualProduct) item).getProductId();
                        }
                    })
                    .collect(Collectors.toList());
            if (expected.size() == productsInDropDown.size()) {
                expected.forEach(expect -> {

                    CustomAssertions.assertTrue(customClassObj, productsInDropDown.contains(expect),
                            "Verifying whether the product " + expect + " is displayed in the drop down list or not.");

                });
            } else {
                CustomAssertions.assertTrue(customClassObj, false,
                        "Expected And Actual entries in thge dropdown are not same \n \t Expected: " + expected + "\n\t Actual: " + productsInDropDown);
            }

            landingPage.logOutFromApp();
        }
    }
}
