package com.mckinsey.lime.mobileWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.mobilePages.BillsAndUsagePage;
import com.mckinsey.lime.mobilePages.LandingPage;
import com.mckinsey.lime.mobilePages.LoginPage;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.List;

@Deprecated
public class BillsAndUsageTest extends MobileWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public BillsAndUsageTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    //    @AfterMethod
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LandingPage landingPage = new LandingPage(getDriver());

            landingPage.logOutFromApp();
        }
    }

    @Test(groups = {"mobileWeb"}, description = "Bills And Usage Screen")
    public void testBillsAndUsageScreenTmhPostPaid(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        LandingPage landingPage = new LandingPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        BillsAndUsagePage billsAndUsagePage = new BillsAndUsagePage(getDriver());


        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());
        CustomAssertions.assertText(customClassObj, customResponse_Products.getResponseCode(), 200, "Verified the PRODUCTS API response code of " + indUser.getUserName() + "as 200");

        loginPage.loginIntoTrueApplicationFromLandingPage(indUser.getUserName(), indUser.getPassWord());


        //TODO: need to add Convergence Products as well
        //TODO: Need to check/count PrePaid Products separately
        List<ProductsResponse.Product> allProductCards = customResponse_Products.getResponseObj().getProductCards();

        CustomAssertions.assertText(customClassObj, billsAndUsagePage.getNumberOfProducts(), allProductCards.size(), "Verified the number of product cards available");
//            CustomAssertions.assertTrue(billsAndUsagePage.isProductCardExpanded(allProductCards.get(0)), "Verified whether Product " + allProductCards.get(0).getProductId() + " is in Expanded Mode or not");

        allProductCards.forEach(item -> {
            new BillsAndUsageUtility().billsAndUsageScreen(customClassObj, context, (AppiumDriver) getDriver(), indUser, item, customResponse_Products.getResponseObj());
        });
        landingPage.clickMyAccount();
        landingPage.clickLogOut();

    }
}
