package com.mckinsey.lime.desktopWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.ProductDetailsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.guiDataBeans.CompareSectionBean;
import com.mckinsey.lime.guiDataBeans.ProductCardBean;
import com.mckinsey.lime.pages.*;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogStrings;
import com.mckinsey.lime.utils.CustomLogging;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.List;

public class ComparePlansTest extends DesktopWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();

    //    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            //Do some thing
        }
    }

    //TODO: Yet to automate info popup for each Compare section... Pending as the API call fails
    @Test(groups = {}, description = "Compare Plans Screen")
    public void testComparePlans(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        LandingPage landingPage = DesktopPageObjects.landingPage(getDriver());
        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());
        ComparePlansPage comparePlansPage = DesktopPageObjects.comparePlansPage(getDriver());

        CustomResponse<ProductDetailsResponse> productDetailsCustomResponse = ApiRequests.sendProductDetailsAPI(customClassObj);
        ProductDetailsResponse responseObj = productDetailsCustomResponse.getResponseObjList().get(0);


        landingPage.clickSavingsMaster();
        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isSavingsTabSelected(),
                "User successfully navigated to OurPlans page and Savings tab selected");

        assertions(customClassObj, commonPage, comparePlansPage, responseObj);


        landingPage.clickProtectionMaster();
        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isProtectionTabSelected(),
                "User successfully navigated to OurPlans page and Protection tab selected");

        assertions(customClassObj, commonPage, comparePlansPage, responseObj);


    }

    private void assertions(CustomClass customClassObj, CommonPage commonPage, ComparePlansPage comparePlansPage, ProductDetailsResponse responseObj) {
        comparePlansPage.clickComparePlansLink();

        List<ProductCardBean> comparableBoxesDisplayed = comparePlansPage.getComparableBoxesDisplayed();

        for (int i = 0; i < comparableBoxesDisplayed.size(); i++) {
            ProductDetailsResponse.IndProductDetails indProductDetails1 = responseObj.getProductDetailsByTitles(comparableBoxesDisplayed.get(i).getProductTitle(), comparableBoxesDisplayed.get(i).getProductSubTitle());
            for (int j = i + 1; j < comparableBoxesDisplayed.size(); j++) {
                ProductDetailsResponse.IndProductDetails indProductDetails2 = responseObj.getProductDetailsByTitles(comparableBoxesDisplayed.get(j).getProductTitle(), comparableBoxesDisplayed.get(j).getProductSubTitle());

                CustomLogging.writeToReport(CustomLogStrings.INFO_LOG,
                        "Comparing Plans: " + indProductDetails1.getName() + " | " + indProductDetails1.getSubTitle() + " and " + indProductDetails2.getName() + " | " + indProductDetails2.getSubTitle(),
                        new String[]{indProductDetails1.getName() + " | " + indProductDetails1.getSubTitle(), indProductDetails2.getName() + " | " + indProductDetails2.getSubTitle()});

                comparePlansPage.clickSelectThisButton(indProductDetails1.getName(), indProductDetails1.getSubTitle());
                comparePlansPage.clickSelectThisButton(indProductDetails2.getName(), indProductDetails2.getSubTitle());

                comparePlansPage.clickCompareNowButton();

                List<ProductDetailsResponse.IndProductDetails.Compare> compare1 = indProductDetails1.getCompare();
                List<ProductDetailsResponse.IndProductDetails.Compare> compare2 = indProductDetails2.getCompare();

                CustomAssertions.assertText(customClassObj, comparePlansPage.getComparePlanHeaderLeft(),
                        indProductDetails1.getName(),
                        "Verified whether the Plan header on left is displayed correct or not");
                CustomAssertions.assertText(customClassObj, comparePlansPage.getComparePlanHeaderRight(),
                        indProductDetails2.getName(),
                        "Verified whether the Plan header on right is displayed correct or not");

                CustomAssertions.assertText(customClassObj, comparePlansPage.getNumberOfCompareSectionsDisplayed(), compare1.size(),
                        "Verified whether the number of compare sections displayed are correct or not");

                List<CompareSectionBean> compareSectionsDisplayed = comparePlansPage.getCompareSectionsDisplayed();
                compareSectionsDisplayed.stream()
                        .forEach(actualSection -> {
                            String actualHeader = actualSection.getHeader();
                            ProductDetailsResponse.IndProductDetails.Compare compareSectionObjectByName = indProductDetails1.getCompareSectionObjectByName(actualHeader);
                            if (compareSectionObjectByName != null) {
                                /*CustomAssertions.assertText(customClassObj, actualSection.getTextLeft(), indProductDetails1.getCompareObjectDescriptionByTitle(actualHeader),
                                        "Verified whether the correct text in left side displayed or not");
                                CustomAssertions.assertText(customClassObj, actualSection.getTextRight(), indProductDetails2.getCompareObjectDescriptionByTitle(actualHeader),
                                        "Verified whether the correct text in right side displayed or not");*/

                            } else {
                                CustomAssertions.assertTrue(customClassObj, false,
                                        "Verified whether the Compare section with actual name is available or not in CMS: " + actualHeader);
                            }


                        });

                commonPage.clickBackNavigationIcon();
            }
        }
        commonPage.clickBackNavigationIcon();
        commonPage.clickBackNavigationIcon();
    }
}
