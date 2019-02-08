package com.mckinsey.lime.desktopWebTests;

import com.mckinsey.lime.apiTestBeans.DobRulesResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.pages.*;
import com.mckinsey.lime.testDataBeans.ProductTogglerTestScenario;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.*;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;

public class ProductTogglerDataDrivenTest extends DesktopWebBaseTest {
    private final ProductTogglerTestScenario currentScenario;

    public ProductTogglerDataDrivenTest(ProductTogglerTestScenario currentScenario) {
        this.currentScenario = currentScenario;
    }

    //    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        DesktopPageObjects.killPageObjects();
        if (result.getStatus() == ITestResult.FAILURE) {
            //Do some thing
        }
    }

    @Test(groups = {}, description = "Toggler Flow")
    public void testGetAQuotePage(ITestContext context) throws IOException {
        CustomLogging.writeTestDataToReport(currentScenario.toString());

        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        LandingPage landingPage = DesktopPageObjects.landingPage(getDriver());
        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());
        PlanDetailsPage planDetailsPage = DesktopPageObjects.planDetailsPage(getDriver());
        GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(getDriver());
        PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(getDriver());


        DobRulesResponse dobRulesResponse = ApiRequests.sendDobRulesAPI(customClassObj).getResponseObjList().get(0);

        CustomLogging.writeToReport(CustomLogStrings.INFO_LOG,
                "Getting quote for plan: " + currentScenario.getPlan(),
                new String[]{currentScenario.getPlan().toString()});

        ourPlansPage.clickPlanCard(currentScenario.getPlan());

        planDetailsPage.clickGetAQuoteButton();

        CustomAssertions.assertText(customClassObj, getAQuotePage.getPageHeader(), "Get a quote now .", "Verified whether the Page header is displayed proper or not");
        CustomAssertions.assertText(customClassObj, getAQuotePage.getPlanName(), currentScenario.getPlan().getPlanTitle(), "Verified whether the Plan Name displayed proper or not");


        getAQuotePage.selectDay(currentScenario.getDob_day());
        getAQuotePage.selectMonth(currentScenario.getDob_month());
        getAQuotePage.selectYear(currentScenario.getDob_year());

        currentScenario.getGender().selectGender(getDriver());
        currentScenario.getSmoke().selectSmoke(getDriver());

        commonPage.clickNextButton();

        //TODO: get this from excel sheet
//        if (filledAge >= maxDaysAllowed) {
        if (false) {
            //TODO: get this from excel sheet
//            if (maxAgeLimit != 62) {
            if (false) {
                //TODO:Del
                String maxAgeLimit = "";
                CustomAssertions.assertText(customClassObj, getAQuotePage.getDobErrorMessage(),
                        "Oops. The maximum age to apply online for this plan is " + maxAgeLimit + ". You may want to explore other plans or click help to contact us if you need further assistance.",
                        "Verified whether correct error message displayed for DOB field or not");
                System.out.println(); // Still same error message of 62 years
            } else {
                CustomAssertions.assertText(customClassObj, getAQuotePage.getDobErrorMessage(),
                        "Oops. For online application, you need to be below age 62. Click help to contact us if you need further assistance.",
                        "Verified whether correct error message displayed for DOB field or not");
            }
            //TODO: get this from excel sheet
//        } else if (filledAge < minDaysAllowed) {
        } else if (false) {
            //TODO: get this from excel sheet
//            if (minAgeLimit != 18) {
            if (false) {
                //TODO:Del
                String minAgeLimit = "";
                CustomAssertions.assertText(customClassObj, getAQuotePage.getDobErrorMessage(),
                        "Oops. You need to be at least " + minAgeLimit + " years old for this plan. You may want to explore other plans or click help to contact us if you need further assistance.",
                        "Verified whether correct error message displayed for DOB field or not");
                System.out.println();
            } else {
                CustomAssertions.assertText(customClassObj, getAQuotePage.getDobErrorMessage(),
                        "Oops. You're really young. For online application, you need to be at least 18 years old. Click help to contact us if you need further assistance.",
                        "Verified whether correct error message displayed for DOB field or not");
            }
        } else {

            possibleFiguresPage.clickGotItButtonForMobile();
            CustomAssertions.assertTrue(customClassObj, possibleFiguresPage.isUserOnPossibleFiguresPage(),
                    "Verified whether the User is on Possible Figures page or not");
            CustomAssertions.assertTrue(customClassObj, possibleFiguresPage.isPremiumFrequencyMonthlySelected(),
                    "Verified whether the Monthly premium option selected or not");


//            possibleFiguresPage.getChosenPlanName();
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPremiumAmount(), currentScenario.getDefaultPossibleFiguresBean().getPremiumAmount(),
                    "Verified whether the Monthly premium displayed correct or not");
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPremiumTerm(), currentScenario.getDefaultPossibleFiguresBean().getPremiumTerm(),
                    "Verified whether the Premium term displayed correct or not");
//            possibleFiguresPage.getSumAssured();
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPolicyTerm(), currentScenario.getDefaultPossibleFiguresBean().getPolicyTerm(),
                    "Verified whether the Policy term displayed correct or not");

            possibleFiguresPage.selectPremiumFrequencyYearly();

            CustomAssertions.assertTrue(customClassObj, possibleFiguresPage.isPremiumFrequencyYearlySelected(),
                    "Verified whether the Yearly premium is selected or not");
//            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPremiumAmount(), currentScenario.getDefaultPossibleFiguresBean().getDefaultAnnualPremium(),
//                    "Verified whether the Anual Premium amount is displayed correct or not");

            //TODO: TC: Update the Premium value less than the default value
            //TODO: TC: Update the Premium value double than the default value
            //TODO: TC: Update the Premium term from drop down
            //TODO: All possible edits with Premium Amount, Term & Frequency

            commonPage.clickNextButton();

            System.out.println();
            //User should redirect to Next page
        }

    }

}
