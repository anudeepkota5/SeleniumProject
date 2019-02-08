package com.mckinsey.lime.mobileWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.apiTestBeans.ToppingsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.mobilePages.BuyExtraPackagePage;
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
public class BuyExtraPackageTest extends MobileWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public BuyExtraPackageTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    //    @AfterMethod
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LandingPage landingPage = new LandingPage(getDriver());

            landingPage.logOutFromApp();
        }
    }

    @Test(groups = {"mobileWeb"}, description = "Buy Extra Package Screen")
    public void testBuyExtraPackage(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        LandingPage landingPage = new LandingPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        SideMenu sideMenu = new SideMenu(getDriver());
        BuyExtraPackagePage buyExtraPackagePage = new BuyExtraPackagePage(getDriver());

        CustomResponse<ProductsResponse> customResponse_Products = ApiRequests.sendProductsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());
        CustomAssertions.assertText(customClassObj, customResponse_Products.getResponseCode(), 200, "Verified the PRODUCTS API response code of " + indUser.getUserName() + "as 200");

        loginPage.loginIntoTrueApplicationFromLandingPage(indUser.getUserName(), indUser.getPassWord());


        sideMenu.navigate2BuyExtraPackage();

        buyExtraPackagePage.clickDropDown();

        final List<String> productsInDropDown = buyExtraPackagePage.getProductsInDropDown();


        List<ProductsResponse.IndividualProduct> allProductsForBuyExtraPackage = customResponse_Products.getResponseObj().getAllProductsForBuyExtraPackage();
        final List<String> expected = allProductsForBuyExtraPackage.stream()
                .map(ProductsResponse.IndividualProduct::getProductId)
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
        buyExtraPackagePage.clickDropDown();

        allProductsForBuyExtraPackage.forEach(item -> {

            try {
                CustomResponse<ToppingsResponse> customResponse_Toppings = ApiRequests.sendToppingsAPI(customClassObj, indUser, item, configData.getApi_channel(), configData.getApi_platform());
                buyExtraPackagePage.selectDropdownValueByProductID(item);

                ToppingsResponse responseObj_toppings = customResponse_Toppings.getResponseObj();
                //TODO: for TVS response is coming in JSON ARRAY. need to handle
                if (responseObj_toppings == null) {
                    return;
                }
                if (responseObj_toppings.getToppingTags().size() == 0) {
                    CustomAssertions.assertText(customClassObj, buyExtraPackagePage.noExtraPackMessage(), "No Extra Packages",
                            "Verified the No Extra pack message as the product " + item.getProductId() + " don't have any available Extra packages to buy");
                } else {
                    responseObj_toppings.getToppingTags().forEach(indToppingTag -> {
                        if (indToppingTag.getTitle().getEn().equalsIgnoreCase("Voice")) {
                            buyExtraPackagePage.clickVoiceTab();

                        } else if (indToppingTag.getTitle().getEn().equalsIgnoreCase("Voice + Data")) {
                            buyExtraPackagePage.clickVoiceAndDataTab();

                        } else if (indToppingTag.getTitle().getEn().equalsIgnoreCase("Data")) {
                            buyExtraPackagePage.clickDataTab();

                        } else if (indToppingTag.getTitle().getEn().equalsIgnoreCase("Social")) {
                            buyExtraPackagePage.clickSocialTab();

                        }
                        CustomAssertions.assertText(customClassObj, buyExtraPackagePage.getNumberOfHotOffers(), indToppingTag.getHotOffers().size(),
                                "Verified the number of Hot offers available for product: " + item.getProductId());
                        CustomAssertions.assertText(customClassObj, buyExtraPackagePage.getNumberOfOtherPackages(), indToppingTag.getPlanCodes().size(),
                                "Verified the number of other packages available for product: " + item.getProductId());

                    });
                }

            } catch (IOException e) {
                System.out.println("Exception occurred for " + item);
                e.printStackTrace();
            }

        });
        landingPage.logOutFromApp();
    }
}
