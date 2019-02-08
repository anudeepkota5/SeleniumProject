package com.mckinsey.lime.desktopWebTests;

import com.mckinsey.lime.apiTestBeans.FindPlanOptionsResponse;
import com.mckinsey.lime.apiTestBeans.FindPlanResultsResponse;
import com.mckinsey.lime.apiTestBeans.ProductDetailsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.pages.*;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.JavaUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SmartFinderTest extends DesktopWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();

    //    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            //Do some thing
        }
    }

    @Test(groups = {}, description = "Landing Screen")
    public void testSmartFinderDropDownLogic(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        LandingPage landingPage = DesktopPageObjects.landingPage(getDriver());
        FindMeAPlanPage findMeAPlanPage = DesktopPageObjects.findMeAPlanPage(getDriver());
        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());
        RetrieveApplicationPage retrieveApplicationPage = DesktopPageObjects.retrieveApplicationPage(getDriver());

        landingPage.clickFindMePlanMaster();
        CustomAssertions.assertTrue(customClassObj, findMeAPlanPage.isUserOnFindMeAPlanPage(),
                "User successfully navigated to FindMeAPlan page");

        FindPlanOptionsResponse findPlanResultsOptions = ApiRequests.sendFindPlanOptionsAPI(customClassObj).getResponseObjList().get(0);
        FindPlanResultsResponse findPlanResultsResponse = ApiRequests.sendFindPlanResultsAPI(customClassObj).getResponseObjList().get(0);
        ProductDetailsResponse productDetailsResponse = ApiRequests.sendProductDetailsAPI(customClassObj).getResponseObjList().get(0);

        List<FindPlanOptionsResponse.IndFindPlanOptionsResponse> dropDown1Values = findPlanResultsOptions.getDropDown1Values();
        dropDown1Values.forEach(dropDown1 -> {

            List<FindPlanOptionsResponse.IndFindPlanOptionsResponse> dropDown2Values = findPlanResultsOptions.getDropDown2Values(dropDown1);
            dropDown2Values.forEach(dropDown2 -> {

                List<FindPlanOptionsResponse.IndFindPlanOptionsResponse> dropDown3Values = findPlanResultsOptions.getDropDown3Values(dropDown1);
                dropDown3Values.forEach(dropDown3 -> {

                    List<FindPlanOptionsResponse.IndFindPlanOptionsResponse> dropDown4Values = findPlanResultsOptions.getDropDown4Values(dropDown1);
                    dropDown4Values.forEach(dropDown4 -> {

                        CustomLogging.writeInfoLogToReport("Selecting: " + dropDown1.getName()
                                + " --> " + dropDown2.getName()
                                + " --> " + dropDown3.getName()
                                + " --> " + dropDown4.getName());
                        findMeAPlanPage.selectDropDown1ByVisibleText(dropDown1.getName());
                        JavaUtils.hardWait(1);
                        findMeAPlanPage.selectDropDown2ByVisibleText(dropDown2.getName());
                        findMeAPlanPage.selectDropDown3ByVisibleText(dropDown3.getName());
                        findMeAPlanPage.selectDropDown4ByVisibleText(dropDown4.getName());

                        findMeAPlanPage.clickShortlistNowButton();

                        List<String> expPlanCodes = findPlanResultsResponse.getResultPlanCodes(dropDown1, dropDown2, dropDown3, dropDown4);
                        expPlanCodes.sort(String.CASE_INSENSITIVE_ORDER);

                        if (expPlanCodes.isEmpty()) {
                            CustomAssertions.assertText(customClassObj, findMeAPlanPage.getNoPlansMessage(),
                                    "Unfortunately no plans found!",
                                    "Verified whether the No Plans message is displayed proper or not");
                            findMeAPlanPage.closeNoPlansPopup();
                        } else {
                            CustomAssertions.assertText(customClassObj,   findMeAPlanPage.getNumberOfPlanResults(), expPlanCodes.size(),
                                    "Verified whether number of results displayed are correct or not");
                            List<String> displayedPlanCodes =   findMeAPlanPage.getDisplayedPlanResults().stream()
                                    .map(item -> productDetailsResponse.getPlanCode(item))
                                    .collect(Collectors.toList());
                            displayedPlanCodes.sort(String.CASE_INSENSITIVE_ORDER);

                            CustomAssertions.assertText(customClassObj, displayedPlanCodes, expPlanCodes,
                                    "Verified whether the Results displayed proper or not");

                            commonPage.clickBackNavigationIcon();
                        }


                    });

                });

            });
        });


    }
}
