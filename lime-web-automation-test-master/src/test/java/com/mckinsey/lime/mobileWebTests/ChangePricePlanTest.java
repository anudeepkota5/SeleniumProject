package com.mckinsey.lime.mobileWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.PricePlanResponse;
import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.mobilePages.ChangePackagePage;
import com.mckinsey.lime.mobilePages.LandingPage;
import com.mckinsey.lime.mobilePages.LoginPage;
import com.mckinsey.lime.mobilePages.SideMenu;
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
public class ChangePricePlanTest extends MobileWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public ChangePricePlanTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    //    @AfterMethod
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LandingPage landingPage = new LandingPage(getDriver());

            landingPage.logOutFromApp();
        }
    }

    @Test(groups = {"mobileWeb"}, description = "Change Price Plan Screen")
    public void testChangePricePlan(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        LandingPage landingPage = new LandingPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        SideMenu sideMenu = new SideMenu(getDriver());
        ChangePackagePage changePackagePage = new ChangePackagePage(getDriver());


        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());
        CustomAssertions.assertText(customClassObj, customResponse_Products.getResponseCode(), 200, "Verified the PRODUCTS API response code of " + indUser.getUserName() + "as 200");

        List<ProductsResponse.IndividualProduct> allProductsForCPP = customResponse_Products.getResponseObj().getAllProductsForChangePackage();

        loginPage.loginIntoTrueApplicationFromLandingPage(indUser.getUserName(), indUser.getPassWord());
        sideMenu.navigate2ChangePricePlan();

        if (allProductsForCPP.size() != 0) {

            final List<String> expected = allProductsForCPP.stream()
                    .map(ProductsResponse.IndividualProduct::getProductId)
                    .collect(Collectors.toList());

            changePackagePage.clickDropDown();

            final List<String> productsInDropDown = changePackagePage.getProductsInDropDown();


            if (expected.size() == productsInDropDown.size()) {
                expected.forEach(expect -> {
                    CustomAssertions.assertTrue(customClassObj, productsInDropDown.contains(expect),
                            "Verifying whether the product " + expect + " is displayed in the drop down list or not.");

                });
            } else {

                CustomAssertions.assertTrue(customClassObj, false,
                        "Expected And Actual entries in thge dropdown are not same \n \t Expected: " + expected + "\n\t Actual: " + productsInDropDown);
            }
            changePackagePage.clickDropDown();

            allProductsForCPP.forEach(item -> {

                try {
                    CustomResponse<PricePlanResponse> customResponse_PricePlan = ApiRequests.sendPricePlanAPI(customClassObj, indUser, item, configData.getApi_channel(), configData.getApi_platform());
                    changePackagePage.selectDropdownValueByProductID(item);

                    PricePlanResponse responseObj_PricePlan = customResponse_PricePlan.getResponseObj();
                    //TODO: for TVS response is coming in JSON ARRAY. need to handle
                    if (responseObj_PricePlan == null) {
                        return;
                    }
                    if (responseObj_PricePlan.getRecommendedPricePlans() == null) {
                        CustomAssertions.assertText(customClassObj, changePackagePage.noChangePackMessage(), "We cannot change price plan for this number",
                                "Verified the No Extra pack message as the product " + item.getProductId() + " don't have any available Extra packages to buy");
                    }

                } catch (IOException e) {
                    System.out.println("Exception occurred for " + item);
                    e.printStackTrace();
                }

            });
            landingPage.logOutFromApp();
        } else {
            CustomAssertions.assertText(customClassObj, changePackagePage.noProducts4ChangePackMessage(), "We cannot change price plan for this number",
                    "Verified the No Extra pack message as the user " + indUser.getUserName() + " don't have any available products available for CPP");
        }
    }
}
