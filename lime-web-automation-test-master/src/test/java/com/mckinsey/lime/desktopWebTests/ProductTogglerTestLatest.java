package com.mckinsey.lime.desktopWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.DobRulesResponse;
import com.mckinsey.lime.apiTestBeans.GetProductsApiResponse;
import com.mckinsey.lime.apiTestBeans.ProductDetailsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.guiDataBeans.GetAQuoteBean;
import com.mckinsey.lime.guiDataBeans.PossibleFiguresBean;
import com.mckinsey.lime.pages.*;
import com.mckinsey.lime.testDataBeans.ProductTogglerScenario;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataEnums.PossibleFiguresEditableScenarios;
import com.mckinsey.lime.testDataEnums.PremiumTerm;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.*;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class ProductTogglerTestLatest extends DesktopWebBaseTest {

    private final ProductTogglerScenario currentScenario;

    public ProductTogglerTestLatest(ProductTogglerScenario currentScenario) {
        this.currentScenario = currentScenario;
    }

    //    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        DesktopPageObjects.killPageObjects();
        if (result.getStatus() == ITestResult.FAILURE) {
            //Do some thing
        }
    }

    @Test(groups = {}, description = "Product Toggler Wizard")
    public void testGetAQuotePage(ITestContext context) throws IOException {
        CustomLogging.witeInfo("Test Method");
        String dobValue = JavaUtils.getFormatedWith2Digits(currentScenario.getDob().getAge().getDayOfMonth()) + "/" + JavaUtils.getFormatedWith2Digits(currentScenario.getDob().getAge().getMonthValue()) + "/" + currentScenario.getDob().getAge().getYear();
        CustomLogging.writeTestDataToReport(currentScenario.getCurrentPlan() + "<br/>" + currentScenario.getDob() + " | " + dobValue + "<br/>" + currentScenario.getGender() + "<br/>Smoke: " + currentScenario.getSmoke() + "<br/>" + currentScenario.getEditScenario());

        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());
        PlanDetailsPage planDetailsPage = DesktopPageObjects.planDetailsPage(getDriver());

        CustomResponse<ProductDetailsResponse> productDetailsCustomResponse = ApiRequests.sendProductDetailsAPI(customClassObj);
        ProductDetailsResponse responseObj = productDetailsCustomResponse.getResponseObjList().get(0);

        currentScenario.getCurrentPlan().getPlanType().clickPlanType(getDriver());

        ourPlansPage.clickPlanCard(currentScenario.getCurrentPlan().getPlanTitle(), currentScenario.getCurrentPlan().getPlanSubTitle());

        planDetailsPage.clickGetAQuoteButton();

        DobRulesResponse dobRulesResponse = ApiRequests.sendDobRulesAPI(customClassObj).getResponseObjList().get(0);
        ProductDetailsResponse.IndProductDetails currentProductDetails = responseObj.getProductDetailsByTitles(currentScenario.getCurrentPlan().getPlanTitle(), currentScenario.getCurrentPlan().getPlanSubTitle());
        //TODO: GetAQuoteBean should create from UI values
        GetAQuoteBean getAQuoteBean = fillGetAQuotePage(customClassObj, currentProductDetails);

        Optional<GetProductsApiResponse.Summary.PlanPossibleFigures> possibleFiguresApiResponse = submitGetAQuotePage(customClassObj, currentProductDetails, dobRulesResponse, getAQuoteBean);

        if (possibleFiguresApiResponse.isPresent()) {

            GetProductsApiResponse.Summary.PlanPossibleFigures planPossibleFigures = fillPossibleFiguresPage(customClassObj, getAQuoteBean);
            submitPossibleFiguresPage(customClassObj, planPossibleFigures);
            submitRiderPage(customClassObj);
        }

//        commonPage.clickHomeNavigationIcon();

    }

    private GetAQuoteBean fillGetAQuotePage(CustomClass customClassObj, ProductDetailsResponse.IndProductDetails currentProductDetails) {

        GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(getDriver());

        CustomAssertions.assertText(customClassObj, getAQuotePage.getPageHeader(), "Get a quote now.", "Verified whether the Page header is displayed proper or not");
        CustomAssertions.assertText(customClassObj, getAQuotePage.getPlanName(), currentScenario.getCurrentPlan().getPlanTitle(), "Verified whether the Plan Name displayed proper or not");
//        getAQuotePage.clickViewDetailsButton();
        //Need to verify the plan Name in ProductDetails Page
//        planDetailsPage.clickGetAQuoteButton();
//        CustomAssertions.assertText(customClassObj, getAQuotePage.getPageHeader(), "Get a quote now .", "Verified whether the Page header is displayed proper or not");


        LocalDate age = currentScenario.getDob().getAge();
        String formattedDay = JavaUtils.getFormatedWith2Digits(age.getDayOfMonth());
        getAQuotePage.selectDay(formattedDay);
        String formattedMonth = JavaUtils.getFormatedWith2Digits(age.getMonthValue());
        getAQuotePage.selectMonth(formattedMonth);
        getAQuotePage.selectYear(age.getYear());
//        CustomLogging.writeTestDataToReport(formattedDay + "/" + formattedMonth + "/" + age.getYear());

        currentScenario.getGender().selectGender(getDriver());

        currentScenario.getSmoke().selectSmoke(getDriver());

        return new GetAQuoteBean(currentProductDetails, formattedDay, formattedMonth, String.valueOf(age.getYear()), currentScenario.getGender(), currentScenario.getSmoke());


    }

    private Optional<GetProductsApiResponse.Summary.PlanPossibleFigures> submitGetAQuotePage(CustomClass customClassObj, ProductDetailsResponse.IndProductDetails currentProductDetails, DobRulesResponse dobRulesResponse, GetAQuoteBean getAQuoteBean) throws IOException {
        Optional<GetProductsApiResponse.Summary.PlanPossibleFigures> possibleFiguresDefaults = Optional.empty();
        boolean success = false;

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(getDriver());

        commonPage.clickNextButton();

        String currentPlanCode = currentProductDetails.getPlanCode();
        int maxAgeLimit = dobRulesResponse.getMaxAgeLimit(currentPlanCode);
        int minAgeLimit = dobRulesResponse.getMinAgeLimit(currentPlanCode);

        long filledAgeInDays = ChronoUnit.DAYS.between(currentScenario.getDob().getAge(), LocalDate.now());
        long maxDaysAllowedInDays = ChronoUnit.DAYS.between(LocalDate.now().minus(Period.of(maxAgeLimit, 0, 0)), LocalDate.now());
        long minDaysAllowedInDays = ChronoUnit.DAYS.between(LocalDate.now().minus(Period.of(minAgeLimit, 0, 0)), LocalDate.now());

        CustomLogging.writeInfoLogToReport("filledAgeInDays: " + filledAgeInDays + " | maxDaysAllowedInDays: " + maxDaysAllowedInDays + " | minDaysAllowedInDays: " + minDaysAllowedInDays);

        if (filledAgeInDays >= maxDaysAllowedInDays) {
            if (maxAgeLimit != 62) {
                CustomAssertions.assertText(customClassObj, getAQuotePage.getDobErrorMessage(),
                        "Oops. The maximum age to apply online for this plan is " + maxAgeLimit + ". You may want to explore other plans or click help to contact us if you need further assistance.",
                        "Verified whether correct error message displayed for DOB field or not");
                System.out.println(); // Still same error message of 62 years
            } else {
                CustomAssertions.assertText(customClassObj, getAQuotePage.getDobErrorMessage(),
                        "Oops. For online application, you need to be below age 62. Click help to contact us if you need further assistance.",
                        "Verified whether correct error message displayed for DOB field or not");
            }

        } else if (filledAgeInDays < minDaysAllowedInDays) {
            if (minAgeLimit != 18) {
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
            success = true;
            CustomResponse<GetProductsApiResponse> getProductsApiResponse = ApiRequests.sendGetProductsAPI(customClassObj, getAQuoteBean);
            possibleFiguresDefaults = Optional.of(getProductsApiResponse.getResponseObj().getSummary().get(0).getOutputs().get(0));

        }

        return possibleFiguresDefaults;
    }

    private GetProductsApiResponse.Summary.PlanPossibleFigures fillPossibleFiguresPage(CustomClass customClassObj, GetAQuoteBean filledGetAQuoteBean) throws IOException {
        PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(getDriver());

        CustomResponse<GetProductsApiResponse> getProductsApiResponse = ApiRequests.sendGetProductsAPI(customClassObj, filledGetAQuoteBean);
        GetProductsApiResponse.Summary.PlanPossibleFigures planPossibleFigures = getProductsApiResponse.getResponseObj().getSummary().get(0).getOutputs().get(0);

        PossibleFiguresBean possibleFiguresBeanForSendingUpdateAPI = new PossibleFiguresBean.Builder()
                .premiumAmount(planPossibleFigures.getMonthlyPremium())
                .sumAssured(planPossibleFigures.getSumAssured())
                .premiumTerm(PremiumTerm.valueOf(planPossibleFigures.getPremiumTerm()))
                .policyTerm(planPossibleFigures.getPolicyTerm())
                .Build();
        //need to set policy term type, PremiumMode, PremiumTermType
        possibleFiguresPage.clickGotItButtonForMobile();

        CustomAssertions.assertTrue(customClassObj, possibleFiguresPage.isUserOnPossibleFiguresPage(),
                "Verified whether the User is on Possible Figures page or not");

        CustomAssertions.assertTrue(customClassObj, possibleFiguresPage.isPremiumFrequencyMonthlySelected(),
                "Verified whether the Monthly premium option selected or not");
//            possibleFiguresPage.getChosenPlanName();

        ProductTogglerScenario.EditScenario editScenario = currentScenario.getEditScenario();
        editScenario.getEditable().clickEditIcon(getDriver());
        editScenario.getEditableScenario().fillField(getDriver(), filledGetAQuoteBean);

        if (editScenario.getEditableScenario() == PossibleFiguresEditableScenarios.SA_GRATER_THAN_MAX) {
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getEditedFieldErrorMessage(),
                    "You should not exceed the amount that is shown above.",
                    "Verified whether the error message ios displayed correct or not:");
            String maxSumAssuredFromConfigFile = TestData.getPlansConfigData().getMaxSumAssured(currentScenario.getCurrentPlan().getPlanTitle(), currentScenario.getCurrentPlan().getPlanSubTitle());
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getSumAssuredTextBoxValue(),
                    maxSumAssuredFromConfigFile,
                    "Verified whether the correct amount is auto filled or not");

            possibleFiguresPage.clickUpdateButton();

            possibleFiguresBeanForSendingUpdateAPI = new PossibleFiguresBean.Builder()
                    .premiumAmount(possibleFiguresBeanForSendingUpdateAPI.getPremiumAmount())
                    .sumAssured(maxSumAssuredFromConfigFile)
                    .premiumTerm(possibleFiguresBeanForSendingUpdateAPI.getPremiumTerm())
                    .policyTerm(possibleFiguresBeanForSendingUpdateAPI.getPolicyTerm())
                    .Build();

        } else if (editScenario.getEditableScenario() == PossibleFiguresEditableScenarios.SA_LESS_THAN_MIN) {
            possibleFiguresPage.clickUpdateButton();
            String minSumAssuredFromConfigFile = TestData.getPlansConfigData().getMinSumAssured(currentScenario.getCurrentPlan().getPlanTitle(), currentScenario.getCurrentPlan().getPlanSubTitle());
            //FIXME
            possibleFiguresBeanForSendingUpdateAPI = new PossibleFiguresBean.Builder()
                    .premiumAmount(possibleFiguresBeanForSendingUpdateAPI.getPremiumAmount())
                    .sumAssured(String.valueOf(Integer.valueOf(minSumAssuredFromConfigFile) - 100))
                    .premiumTerm(possibleFiguresBeanForSendingUpdateAPI.getPremiumTerm())
                    .policyTerm(possibleFiguresBeanForSendingUpdateAPI.getPolicyTerm())
                    .Build();

        } else if (editScenario.getEditableScenario() == PossibleFiguresEditableScenarios.PREMIUM_FREQUENCY_YEARLY) {
            CustomAssertions.assertTrue(customClassObj, possibleFiguresPage.isPremiumFrequencyYearlySelected(),
                    "Verified whether the YEARLY is selected or not:");
            //TODO: set possibleFiguresBeanForSendingUpdateAPI with with yearly
        } else if (editScenario.getEditableScenario() == PossibleFiguresEditableScenarios.PREMIUM_GRATER_THAN_MAX_SA) {
            possibleFiguresPage.clickUpdateButton();
//            possibleFiguresBeanForSendingUpdateAPI.setPremiumAmount(maxSumAssuredFromConfigFile);
        } else if (editScenario.getEditableScenario() == PossibleFiguresEditableScenarios.PREMIUM_LESS_THAN_MIN_SA) {
            possibleFiguresPage.clickUpdateButton();
//            possibleFiguresBeanForSendingUpdateAPI.setPremiumAmount(maxSumAssuredFromConfigFile);
        } else if (editScenario.getEditableScenario() == PossibleFiguresEditableScenarios.NO_CHANGE) {
            // No need to send update API request
            return planPossibleFigures;
        }
        GetProductsApiResponse.Summary.PlanPossibleFigures getProductsApiResponseCustomResponse = ApiRequests.sendEditGetProductsAPI(customClassObj, filledGetAQuoteBean, possibleFiguresBeanForSendingUpdateAPI)
                .getResponseObj().getSummary().get(0).getOutputs().get(0);

        return getProductsApiResponseCustomResponse;

    }

    private void submitPossibleFiguresPage(CustomClass customClassObj, GetProductsApiResponse.Summary.PlanPossibleFigures filledPossibleFiguresBean) {
        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(getDriver());

        //TODO: assert all values
        String str = "";
        if (possibleFiguresPage.isPremiumFrequencyMonthlySelected())
            str = filledPossibleFiguresBean.getMonthlyPremium();
        else
            str = filledPossibleFiguresBean.getAnnualPremium();
        CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPremiumAmount(), str,
                "Verified whether the Monthly premium displayed correct or not");
//            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPremiumTerm(), possibleFiguresBeanMonthly.getPremiumTerm(),
//                    "Verified whether the Premium term displayed correct or not: " + indProductDetails.getName() + " | " + indProductDetails.getSubTitle());

//            possibleFiguresPage.getSumAssured();
        CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPolicyTerm(), filledPossibleFiguresBean.getPolicyTerm(),
                "Verified whether the Policy term displayed correct or not");


        commonPage.clickNextButton();

    }

    private void submitRiderPage(CustomClass customClassObj) {
        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(getDriver());
        WantARiderPage wantARiderPage = DesktopPageObjects.wantARiderPage(getDriver());
        ProductTogglerSummaryPage productTogglerSummaryPage = DesktopPageObjects.productTogglerSummaryPage(getDriver());


        CustomAssertions.assertText(customClassObj, wantARiderPage.getPageHeader(), "Do you want a rider?", "Verified whether the page header displayed correct or not");
        wantARiderPage.selectNoThanks();

        commonPage.clickNextButton();
        CustomAssertions.assertText(customClassObj, productTogglerSummaryPage.getPageHeader(), "Here's your plan!",
                "Verified whether the page header displayed correct or not");

        System.out.println();

    }
}
