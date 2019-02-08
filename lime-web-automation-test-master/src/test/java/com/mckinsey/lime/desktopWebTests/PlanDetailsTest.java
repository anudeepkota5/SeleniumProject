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

public class PlanDetailsTest extends DesktopWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();

    //    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        DesktopPageObjects.killPageObjects();
        if (result.getStatus() == ITestResult.FAILURE) {
            //Do some thing
        }
    }

    @Test(groups = {}, description = "Plan Details Screen")
    public void testOurPlans(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        LandingPage landingPage = DesktopPageObjects.landingPage(getDriver());
        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());

        CustomResponse<ProductDetailsResponse> productDetailsCustomResponse = ApiRequests.sendProductDetailsAPI(customClassObj);
        ProductDetailsResponse responseObj = productDetailsCustomResponse.getResponseObjList().get(0);

        landingPage.clickSavingsMaster();
        CustomAssertions.assertText(customClassObj, ourPlansPage.getPageHeading(), "Our plans", "Verified the Page Heading as ");

        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isSavingsTabSelected(),
                "Verified whether the Savings Tab is selected or not");

        int allSavingsPlanCount = responseObj.getAllSavingsPlanCount();

        assertions(customClassObj, responseObj, allSavingsPlanCount);

        landingPage.clickProtectionMaster();
        CustomAssertions.assertText(customClassObj, ourPlansPage.getPageHeading(), "Our plans", "Verified the Page Heading as ");

        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isProtectionTabSelected(),
                "Verified whether the Protection Tab is selected or not");

        int allProtectionPlanCount = responseObj.getAllProtectionPlanCount();

        assertions(customClassObj, responseObj, allProtectionPlanCount);

    }

    private void assertions(CustomClass customClassObj, ProductDetailsResponse responseObj, int allSavingsPlanCount) {

        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());
        PlanDetailsPage planDetailsPage = DesktopPageObjects.planDetailsPage(getDriver());
        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());

        int numberOfProductCards = ourPlansPage.getNumberOfProductCards();
        CustomAssertions.assertText(customClassObj, numberOfProductCards, allSavingsPlanCount,
                "Verified the number of plans displayed as per API");

        List<ProductCardBean> productCardsDisplayed = ourPlansPage.getProductCardsDisplayed();

        productCardsDisplayed.stream().forEach(actualItem -> {

            ProductDetailsResponse.IndProductDetails productDetailsByName = responseObj.getProductDetailsByName(actualItem.getProductHeader());
            if (productDetailsByName != null) {

                ourPlansPage.clickPlanCard(actualItem.getProductTitle(), actualItem.getProductSubTitle());

                CustomAssertions.assertText(customClassObj, planDetailsPage.getPlanTitle(),
                        actualItem.getProductTitle(),
                        "Verified the Product Title as ");

                CustomAssertions.assertText(customClassObj, planDetailsPage.getPlanSubTitle(),
                        actualItem.getProductSubTitle(),
                        "Verified the Product SubTitle as ");

                //TODO: Need to check Whether the section is Expanding properly or not

                CustomAssertions.assertTrue(customClassObj, planDetailsPage.isGetAQuoteButtonDisplayed(),
                        "Verified presence of Quote Button");

                commonPage.clickBackNavigationIcon();

            } else {
                CustomAssertions.assertTrue(customClassObj, false,
                        "Verified whether the Product with actual name is available or not in CMS: " + actualItem.getProductHeader());
            }
        });
        commonPage.clickBackNavigationIcon();
    }
}

