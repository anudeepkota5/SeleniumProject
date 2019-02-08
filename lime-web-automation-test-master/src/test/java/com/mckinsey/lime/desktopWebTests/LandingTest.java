package com.mckinsey.lime.desktopWebTests;

import com.mckinsey.lime.pages.*;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class LandingTest extends DesktopWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();

    //    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        DesktopPageObjects.killPageObjects();
        if (result.getStatus() == ITestResult.FAILURE) {
            //Do some thing
        }
    }

    @Test(groups = {}, description = "Landing Screen")
    public void testLanding(ITestContext context) {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        LandingPage landingPage = DesktopPageObjects.landingPage(getDriver());
        FindMeAPlanPage findMeAPlanPage = DesktopPageObjects.findMeAPlanPage(getDriver());
        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());
        RetrieveApplicationPage retrieveApplicationPage = DesktopPageObjects.retrieveApplicationPage(getDriver());

        landingPage.isIncomeLogoDisplayed();
        landingPage.clickIncomeLogo();
        landingPage.closeCurrentWindow();

        CustomAssertions.assertText(customClassObj, landingPage.getPageHeading(), "Find yourself a life insurance",
                "Verified the Page Heading as ");
        CustomAssertions.assertText(customClassObj, landingPage.getPageHeadingDescription(),
                "Find possible protection or savings plans in your own time and at your own pace.",
                "Verified the Page Heading Description as ");

        landingPage.clickFindMePlanMaster();
        CustomAssertions.assertTrue(customClassObj, findMeAPlanPage.isUserOnFindMeAPlanPage(),
                "User successfully navigated to FindMeAPlan page");

        commonPage.clickBackNavigationIcon();
        CustomAssertions.assertTrue(customClassObj, landingPage.isUserOnLandingPage(),
                "User successfully navigated back to Landing page");

//        landingPage.getTotalControlMessage();
//        landingPage.getEasyToUseMessage();

        landingPage.clickProtectionMaster();
        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isProtectionTabSelected(),
                "User successfully navigated to OurPlans page and Protection tab selected");
        CustomAssertions.assertFalse(customClassObj, ourPlansPage.isSavingsTabSelected(),
                "User successfully navigated to OurPlans page and Savings tab selected");

        commonPage.clickBackNavigationIcon();
        CustomAssertions.assertTrue(customClassObj, landingPage.isUserOnLandingPage(),
                "User successfully navigated back to Landing page");

        landingPage.clickSavingsMaster();
        CustomAssertions.assertTrue(customClassObj, ourPlansPage.isSavingsTabSelected(),
                "User successfully navigated to OurPlans page and Savings tab selected");

        commonPage.clickBackNavigationIcon();
        CustomAssertions.assertTrue(customClassObj, landingPage.isUserOnLandingPage(),
                "User successfully navigated back to Landing page");

        landingPage.clickRetrieveApplicationMaster();
        CustomAssertions.assertTrue(customClassObj, retrieveApplicationPage.isUserOnRetrieveApplicationPage(),
                "User successfully navigated to Retrieve Application page");

//        retrieveApplicationPage.clickResumeNowButton();
        commonPage.clickBackNavigationIcon();
        CustomAssertions.assertTrue(customClassObj, landingPage.isUserOnLandingPage(),
                "User successfully navigated back to Landing page");

        CustomAssertions.assertTrue(customClassObj, landingPage.isHelpIconDisplayed(), "Verified whether the Help icon is displayed or not");

    }
}
