package com.mckinsey.lime.desktopWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.ProductDetailsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.guiDataBeans.ProductCardBean;
import com.mckinsey.lime.pages.*;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.List;

public class ProductsOverviewTest extends DesktopWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();

    //    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        DesktopPageObjects.killPageObjects();
        if (result.getStatus() == ITestResult.FAILURE) {
            //Do some thing
        }
    }

    @Test(groups = {}, description = "Our Plans Screen - Products Overview")
    public void testOurPlans(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        LandingPage landingPage = DesktopPageObjects.landingPage(getDriver());
        FindMeAPlanPage findMeAPlanPage = DesktopPageObjects.findMeAPlanPage(getDriver());
        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());
        RetrieveApplicationPage retrieveApplicationPage = DesktopPageObjects.retrieveApplicationPage(getDriver());

        CustomResponse<ProductDetailsResponse> productDetailsCustomResponse = ApiRequests.sendProductDetailsAPI(customClassObj);
        ProductDetailsResponse responseObj = productDetailsCustomResponse.getResponseObjList().get(0);

        landingPage.clickProtectionMaster();
//        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isProtectionTabSelected(),
//                "User successfully navigated to OurPlans page and Protection tab selected");

        CustomAssertions.assertText(customClassObj, ourPlansPage.getPageHeading(), "Our plans", "Verified the Page Heading as ");
        CustomAssertions.assertText(customClassObj, ourPlansPage.getPageHeadingDescription(),
                "These are our protection and savings plans. Feel free to explore!",
                "Verified the Page Description as ");

        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isProtectionTabSelected(),
                "Verified whether the Protection Tab is selected or not");


        int allProtectionPlanCount = responseObj.getAllProtectionPlanCount();
        int numberOfProductCards = ourPlansPage.getNumberOfProductCards();

        CustomAssertions.assertText(customClassObj, numberOfProductCards, allProtectionPlanCount,
                "Verified the number of Protection plans displayed as per API CMS");

        List<ProductDetailsResponse.IndProductDetails> allProtectionPlans = responseObj.getAllProtectionPlans();
        List<ProductCardBean> productCardsDisplayed = ourPlansPage.getProductCardsDisplayed();

        productCardsDisplayed.stream()
                .forEach(actualItem -> {
                    ProductDetailsResponse.IndProductDetails productDetailsByName = responseObj.getProductDetailsByName(actualItem.getProductHeader());
                    if (productDetailsByName != null) {
                        CustomAssertions.assertText(customClassObj, actualItem.getProductDescription(),
                                productDetailsByName.getDescription(),
                                "Verified the Product Description as ");
                    } else {
                        CustomAssertions.assertTrue(customClassObj, false,
                                "Verified whether the Product with actual name is available or not in CMS: " + actualItem.getProductHeader());
                    }
                });

        ourPlansPage.clickSeeOtherIncomeProductsButton();
        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isPopupUpDisplayed(),
                "Verified whether the Popup is diaplyed or not");

        CustomAssertions.assertText(customClassObj, ourPlansPage.getPopupHeader(),
                "Sure about leaving?",
                "Verified the popup header");
        CustomAssertions.assertText(customClassObj, ourPlansPage.getPopupText(),
                "This site shows the life insurance plans that you can buy here. If you still want to see other plans, we will bring you to Income homepage.",
                "Verified the popup text");

        ourPlansPage.clickSeeOtherIncomeProductsOnPopup();
        ourPlansPage.closeCurrentWindow();

        ourPlansPage.clickSeeOtherIncomeProductsButton();

        ourPlansPage.clickStayHere();
        CustomAssertions.assertFalse(customClassObj, ourPlansPage.isPopupUpDisplayed(),
                "Verified whether the popup is closed or not");

        //Assert number of Protection plans displayed And the names of those.
        // Click on See Other Income Products button
        // Assert popup header
        // Assert Popup text
        // On popup, Click on Stay Here Button
        // On popup, Click on See Other Income Products button
        // Close current tab, navigate back to previous tab


        commonPage.clickBackNavigationIcon();
        CustomAssertions.assertTrue(customClassObj, landingPage.isUserOnLandingPage(),
                "User successfully navigated back to Landing page");

        landingPage.clickSavingsMaster();
        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isSavingsTabSelected(),
                "User successfully navigated to OurPlans page and Savings tab selected");
        int allSavingsPlanCount = responseObj.getAllSavingsPlanCount();
        int numberOfProductSavingsCards = ourPlansPage.getNumberOfProductCards();
        List<ProductCardBean> savingsProductCardsDisplayed = ourPlansPage.getProductCardsDisplayed();

        CustomAssertions.assertText(customClassObj, numberOfProductSavingsCards, allSavingsPlanCount,
                "Verified the number of Protection plans displayed as per API CMS");

        List<ProductDetailsResponse.IndProductDetails> allSavingsPlans = responseObj.getAllSavingsPlans();

        savingsProductCardsDisplayed.stream()
                .forEach(actualItem -> {
                    ProductDetailsResponse.IndProductDetails productDetailsByName = responseObj.getProductDetailsByName(actualItem.getProductHeader());
                    if (productDetailsByName != null) {
                        CustomAssertions.assertText(customClassObj, actualItem.getProductDescription(),
                                productDetailsByName.getDescription(),
                                "Verified the Product Description as ");
                    } else {
                        CustomAssertions.assertTrue(customClassObj, false,
                                "Verified whether the Product with actual name is available or not in CMS: " + actualItem.getProductHeader());
                    }
                });

        ourPlansPage.clickSeeOtherIncomeProductsButton();
        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isPopupUpDisplayed(),
                "Verified whether the Popup is diaplyed or not");

        CustomAssertions.assertText(customClassObj, ourPlansPage.getPopupHeader(),
                "Sure about leaving?",
                "Verified the popup header");
        CustomAssertions.assertText(customClassObj, ourPlansPage.getPopupText(),
                "This site shows the life insurance plans that you can buy here. If you still want to see other plans, we will bring you to Income homepage.",
                "Verified the popup text");

        ourPlansPage.clickSeeOtherIncomeProductsOnPopup();
        ourPlansPage.closeCurrentWindow();

        ourPlansPage.clickSeeOtherIncomeProductsButton();

        ourPlansPage.clickStayHere();
        CustomAssertions.assertFalse(customClassObj, ourPlansPage.isPopupUpDisplayed(),
                "Verified whether the popup is closed or not");


        commonPage.clickBackNavigationIcon();
        CustomAssertions.assertTrue(customClassObj, landingPage.isUserOnLandingPage(),
                "User successfully navigated back to Landing page");

    }
}
