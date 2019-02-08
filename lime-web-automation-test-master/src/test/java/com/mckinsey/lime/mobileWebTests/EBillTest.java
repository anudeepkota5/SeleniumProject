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
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public class EBillTest extends MobileWebBaseTest {

    private final UsersDataBean apiUsersData = TestData.getApiUsersData();
    private final ConfigDataBean configData = TestData.getConfigData();

    //    @AfterMethod
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LandingPage landingPage = new LandingPage(getDriver());

            landingPage.clickMyAccount();
            landingPage.clickLogOut();
        }
    }

    @Test(groups = {"mobileWeb"}, description = "eBill Screen")
    public void testEBill(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);
        for (UsersDataBean.User indUser : apiUsersData.getUsers()) {
            CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());
            CustomAssertions.assertText(customClassObj, customResponse_Products.getResponseCode(), 200, "Verified the PRODUCTS API response code of " + indUser.getUserName() + "as 200");


            LandingPage landingPage = new LandingPage(getDriver());
            LoginPage loginPage = new LoginPage(getDriver());
            SideMenu sideMenu = new SideMenu(getDriver());
            EBillPage eBillPage = new EBillPage(getDriver());

            BillsAndUsagePage billsAndUsagePage = new BillsAndUsagePage(getDriver());

            loginPage.loginIntoTrueApplicationFromLandingPage(indUser.getUserName(), indUser.getPassWord());


            //TODO: need to add Convergence Products as well
            //TODO: Need to check/count PrePaid Products separately
            List<ProductsResponse.IndividualProduct> allProducts = customResponse_Products.getResponseObj().getAllPostPaidProducts(false);

            List<String> allAccountIDs = allProducts.stream().map(ProductsResponse.IndividualProduct::getAccountId).collect(Collectors.toList());

            if (allAccountIDs.size() != 0) {
                try {
                    CustomResponse<HashMap> customResponse_Preferences = ApiRequests.sendPreferencesAPI(customClassObj, indUser, allAccountIDs, configData.getApi_channel(), configData.getApi_platform());
                    CustomAssertions.assertText(customClassObj, customResponse_Preferences.getResponseCode(), 200, "Verified the PREFERENCES API response code of " + indUser.getUserName() + "as 200");

                    sideMenu.navigate2Ebill();
                    CustomAssertions.assertText(customClassObj, eBillPage.numberOfEbillProducts() + eBillPage.numberOfpaperBillProducts(), customResponse_Preferences.getResponseObjList().size(),
                            "Verifying whether the number of bill cards for user " + indUser.getUserName() + " is displayed correct or not.");


                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
            landingPage.logOutFromApp();

        }
    }
}
