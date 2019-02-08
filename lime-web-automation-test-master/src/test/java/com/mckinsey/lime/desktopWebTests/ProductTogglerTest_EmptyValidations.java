package com.mckinsey.lime.desktopWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.DobRulesResponse;
import com.mckinsey.lime.apiTestBeans.ProductDetailsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.guiDataBeans.ProductCardBean;
import com.mckinsey.lime.pages.*;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CommonEnums;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogStrings;
import com.mckinsey.lime.utils.CustomLogging;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ProductTogglerTest_EmptyValidations extends DesktopWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();

    //    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        DesktopPageObjects.killPageObjects();
        if (result.getStatus() == ITestResult.FAILURE) {
            //Do some thing
        }
    }

    @Test(groups = {}, description = "Get A Quote (Pre-Toggler)Screen")
    public void testGetAQuotePage(ITestContext context) throws IOException {

        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        LandingPage landingPage = DesktopPageObjects.landingPage(getDriver());
        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());
        PlanDetailsPage planDetailsPage = DesktopPageObjects.planDetailsPage(getDriver());
        GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(getDriver());

        CustomResponse<ProductDetailsResponse> productDetailsCustomResponse = ApiRequests.sendProductDetailsAPI(customClassObj);
        ProductDetailsResponse responseObj = productDetailsCustomResponse.getResponseObjList().get(0);

        landingPage.clickProtectionMaster();
        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isProtectionTabSelected(),
                "Verified whether the Protection Tab is selected or not");

        assertionsByPolicyType(customClassObj, responseObj);

        commonPage.clickHomeNavigationIcon();


        landingPage.clickSavingsMaster();
        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isSavingsTabSelected(),
                "Verified whether the Savings Tab is selected or not");

        assertionsByPolicyType(customClassObj, responseObj);

        commonPage.clickHomeNavigationIcon();

    }

    private void assertionsByPolicyType(CustomClass customClassObj, ProductDetailsResponse planDetailsResponseObj) throws IOException {

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());
        PlanDetailsPage planDetailsPage = DesktopPageObjects.planDetailsPage(getDriver());
        GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(getDriver());

        DobRulesResponse dobRulesResponse = ApiRequests.sendDobRulesAPI(customClassObj).getResponseObjList().get(0);

        List<ProductCardBean> productCardsDisplayed = ourPlansPage.getProductCardsDisplayed();

        for (int i = 0; i < productCardsDisplayed.size(); i++) {

            ProductCardBean currentProduct = productCardsDisplayed.get(i);
            ProductDetailsResponse.IndProductDetails indProductDetails1 = planDetailsResponseObj.getProductDetailsByTitles(currentProduct.getProductTitle(), currentProduct.getProductSubTitle());

            CustomLogging.writeToReport(CustomLogStrings.INFO_LOG,
                    "Getting quote for plan: " + indProductDetails1.getName() + " | " + indProductDetails1.getSubTitle(),
                    new String[]{indProductDetails1.getName() + " | " + indProductDetails1.getSubTitle()});

            ourPlansPage.clickPlanCard(indProductDetails1.getName(), indProductDetails1.getSubTitle());

            planDetailsPage.clickGetAQuoteButton();

            CustomAssertions.assertText(customClassObj, getAQuotePage.getPageHeader(), "Get a quote now .", "Verified whether the Page header is displayed proper or not");
            CustomAssertions.assertText(customClassObj, getAQuotePage.getPlanName(), indProductDetails1.getName(), "Verified whether the Plan Name displayed proper or not");
            getAQuotePage.clickViewDetailsButton();
            //Need to verify the plan Name in ProductDetails Page
            planDetailsPage.clickGetAQuoteButton();
            CustomAssertions.assertText(customClassObj, getAQuotePage.getPageHeader(), "Get a quote now .", "Verified whether the Page header is displayed proper or not");

            commonPage.clickNextButton();
            //Verify validation messages
            CustomAssertions.assertText(customClassObj, getAQuotePage.getDobErrorMessage(), "If not selected, 'Please select your Date of Birth.'", "");
            CustomAssertions.assertText(customClassObj, getAQuotePage.getGenderErrorMessage(), "If not filled, 'Please select your gender.'", "");
            CustomAssertions.assertText(customClassObj, getAQuotePage.getSmokeErrorMessage(), "If not selected, 'Please tell us whether you smoke.'", "");


            commonPage.clickBackNavigationIcon();
            commonPage.clickBackNavigationIcon();
            commonPage.clickBackNavigationIcon();
            commonPage.clickBackNavigationIcon();

        }
    }

}
