package com.mckinsey.lime.desktopWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.DobRulesResponse;
import com.mckinsey.lime.apiTestBeans.ProductDetailsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.guiDataBeans.GetAQuoteBean;
import com.mckinsey.lime.guiDataBeans.PossibleFiguresBean;
import com.mckinsey.lime.pages.*;
import com.mckinsey.lime.testDataBeans.PossibleFiguresEditScenario;
import com.mckinsey.lime.testDataBeans.ProductTogglerTestScenario;
import com.mckinsey.lime.testDataBeans.RiderInfo;
import com.mckinsey.lime.testDataBeans.RiderScenario;
import com.mckinsey.lime.testDataEnums.*;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogging;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.List;

public class ProductTogglerTestLatest_DataDriven extends DesktopWebBaseTest {

    private final ProductTogglerTestScenario currentScenario;

    public ProductTogglerTestLatest_DataDriven(ProductTogglerTestScenario currentScenario) {
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
//        String dobValue = JavaUtils.getFormatedWith2Digits(currentScenario.getDob().getAge().getDayOfMonth()) + "/" + JavaUtils.getFormatedWith2Digits(currentScenario.getDob().getAge().getMonthValue()) + "/" + currentScenario.getDob().getAge().getYear();
        CustomLogging.writeTestDataToReport(currentScenario.getTcNo() + ". " + currentScenario.getPlan() + "<br/>" +
                currentScenario.getDob_day() + "/" + currentScenario.getDob_month() + "/" + currentScenario.getDob_year() + " | " + currentScenario.getGender() + " | " + currentScenario.getSmoke() + "<br/>" +
                currentScenario.getEditScenarios());
        CustomLogging.writeInfoLogToReport(currentScenario.toString());

        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        OurPlansPage ourPlansPage = DesktopPageObjects.ourPlansPage(getDriver());
        PlanDetailsPage planDetailsPage = DesktopPageObjects.planDetailsPage(getDriver());
        PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(getDriver());

        CustomResponse<ProductDetailsResponse> productDetailsCustomResponse = ApiRequests.sendProductDetailsAPI(customClassObj);
        ProductDetailsResponse responseObj = productDetailsCustomResponse.getResponseObjList().get(0);

        ourPlansPage.clickPlanCard(currentScenario.getPlan());

        planDetailsPage.clickGetAQuoteButton();

        DobRulesResponse dobRulesResponse = ApiRequests.sendDobRulesAPI(customClassObj).getResponseObjList().get(0);
        ProductDetailsResponse.IndProductDetails currentProductDetails = responseObj.getProductDetailsByTitles(currentScenario.getPlan());

        GetAQuoteBean getAQuoteBean = fillGetAQuotePage(customClassObj, currentProductDetails);

        boolean validSubmitionOfPreToggler = submitGetAQuotePage(customClassObj, currentProductDetails, dobRulesResponse);

        if (validSubmitionOfPreToggler) {
            possibleFiguresPage.clickGotItButtonForMobile();
            PossibleFiguresBean.ViewDetails viewDetails = currentScenario.getDefaultPossibleFiguresBean().getViewDetails();
            PossibleFiguresBean defaultExpValues = new PossibleFiguresBean.Builder().premiumAmount(currentScenario.getDefaultPossibleFiguresBean().getPremiumAmount())
                    .premiumTerm(currentScenario.getDefaultPossibleFiguresBean().getPremiumTerm())
                    .premiumFrequency(currentScenario.getDefaultPossibleFiguresBean().getPremiumFrequency())
                    .policyTerm(currentScenario.getDefaultPossibleFiguresBean().getPolicyTerm())
                    .sumAssured(currentScenario.getDefaultPossibleFiguresBean().getSumAssured())
                    .startAgeForCashBenefit(currentScenario.getDefaultPossibleFiguresBean().getStartAgeForCashBenefit())
                    .payoutPeriod(currentScenario.getDefaultPossibleFiguresBean().getPayoutPeriod())
                    .monthlyCashBenefit(currentScenario.getDefaultPossibleFiguresBean().getMonthlyCashBenefit())
                    .viewDetails(viewDetails)
                    .Build();
            //Check Default values
            assertPossibleFiguresPage(customClassObj, defaultExpValues);
            //Update fields as per excel sheet
            for (int i = 0; i < currentScenario.getEditScenarios().size(); i++) {

                PossibleFiguresBean filledPossibleFiguresBean = editPossibleFiguresPage(customClassObj, getAQuoteBean, currentScenario.getEditScenarios().get(i));
                PossibleFiguresBean.ViewDetails updatedViewDetails = currentScenario.getUpdatedPossibleFiguresBeans().get(i).getViewDetails();
                //CHeck updated values
                PossibleFiguresBean updatedExpValues = new PossibleFiguresBean.Builder()
                        .premiumAmount(currentScenario.getUpdatedPossibleFiguresBeans().get(i).getPremiumAmount())
                        .premiumTerm(currentScenario.getUpdatedPossibleFiguresBeans().get(i).getPremiumTerm())
                        .premiumFrequency(currentScenario.getUpdatedPossibleFiguresBeans().get(i).getPremiumFrequency())
                        .policyTerm(currentScenario.getUpdatedPossibleFiguresBeans().get(i).getPolicyTerm())
                        .sumAssured(currentScenario.getUpdatedPossibleFiguresBeans().get(i).getSumAssured())
                        .startAgeForCashBenefit(currentScenario.getUpdatedPossibleFiguresBeans().get(i).getStartAgeForCashBenefit())
                        .payoutPeriod(currentScenario.getUpdatedPossibleFiguresBeans().get(i).getPayoutPeriod())
                        .monthlyCashBenefit(currentScenario.getUpdatedPossibleFiguresBeans().get(i).getMonthlyCashBenefit())
                        .viewDetails(updatedViewDetails)
                        .Build();
                assertPossibleFiguresPage(customClassObj, updatedExpValues);
            }
            commonPage.clickNextButton();

            submitRiderPage(customClassObj);

//            submitSummaryPage(customClassObj);
        }

//        commonPage.clickHomeNavigationIcon();

    }

    private GetAQuoteBean fillGetAQuotePage(CustomClass customClassObj, ProductDetailsResponse.IndProductDetails currentProductDetails) {

        GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(getDriver());

        CustomAssertions.assertText(customClassObj, getAQuotePage.getPageHeader(), "Get a quote now.", "Verified whether the Page header is displayed proper or not");
        CustomAssertions.assertText(customClassObj, getAQuotePage.getPlanName(), currentScenario.getPlan().getPlanTitle(), "Verified whether the Plan Name displayed proper or not");
//        getAQuotePage.clickViewDetailsButton();
        //Need to verify the plan Name in ProductDetails Page
//        planDetailsPage.clickGetAQuoteButton();
//        CustomAssertions.assertText(customClassObj, getAQuotePage.getPageHeader(), "Get a quote now .", "Verified whether the Page header is displayed proper or not");


        /*LocalDate age = currentScenario.getDob().getAge();
        String formattedDay = JavaUtils.getFormatedWith2Digits(age.getDayOfMonth());
        getAQuotePage.selectDay(formattedDay);
        String formattedMonth = JavaUtils.getFormatedWith2Digits(age.getMonthValue());
        getAQuotePage.selectMonth(formattedMonth);
        getAQuotePage.selectYear(age.getYear());*/
//        CustomLogging.writeTestDataToReport(formattedDay + "/" + formattedMonth + "/" + age.getYear());

        getAQuotePage.selectDay(currentScenario.getDob_day());
        getAQuotePage.selectMonth(currentScenario.getDob_month());
        getAQuotePage.selectYear(currentScenario.getDob_year());

        currentScenario.getGender().selectGender(getDriver());

        currentScenario.getSmoke().selectSmoke(getDriver());

        return new GetAQuoteBean(currentProductDetails, currentScenario.getDob_day(), currentScenario.getDob_month(), currentScenario.getDob_year(), currentScenario.getGender(), currentScenario.getSmoke());
    }

    private boolean submitGetAQuotePage(CustomClass customClassObj, ProductDetailsResponse.IndProductDetails currentProductDetails, DobRulesResponse dobRulesResponse) {

        boolean success = false;

        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        GetAQuotePage getAQuotePage = DesktopPageObjects.getAQuotePage(getDriver());

        commonPage.clickNextButton();

        String currentPlanCode = currentProductDetails.getPlanCode();
        int maxAgeLimit = dobRulesResponse.getMaxAgeLimit(currentPlanCode);
        int minAgeLimit = dobRulesResponse.getMinAgeLimit(currentPlanCode);

        /*long filledAgeInDays = ChronoUnit.DAYS.between(currentScenario.getDob().getAge(), LocalDate.now());
        long maxDaysAllowedInDays = ChronoUnit.DAYS.between(LocalDate.now().minus(Period.of(maxAgeLimit, 0, 0)), LocalDate.now());
        long minDaysAllowedInDays = ChronoUnit.DAYS.between(LocalDate.now().minus(Period.of(minAgeLimit, 0, 0)), LocalDate.now());

        CustomLogging.writeInfoLogToReport("filledAgeInDays: " + filledAgeInDays + " | maxDaysAllowedInDays: " + maxDaysAllowedInDays + " | minDaysAllowedInDays: " + minDaysAllowedInDays);*/

//        if (filledAgeInDays >= maxDaysAllowedInDays) {
        if (false) {
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

        } else if (false) {
//        } else if (filledAgeInDays < minDaysAllowedInDays) {
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
        }

        return success;
    }

    private PossibleFiguresBean editPossibleFiguresPage(CustomClass customClassObj, GetAQuoteBean filledGetAQuoteBean, PossibleFiguresEditScenario editScenario) throws IOException {
        PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(getDriver());

//        CustomResponse<GetProductsApiResponse> getProductsApiResponse = ApiRequests.sendGetProductsAPI(customClassObj, filledGetAQuoteBean);
//        GetProductsApiResponse.Summary.PlanPossibleFigures planPossibleFigures = getProductsApiResponse.getResponseObj().getSummary().get(0).getOutputs().get(0);

        editScenario.getEditField().clickEditIcon(getDriver());
        String valueToUpdate = editScenario.getValueToUpdate();
        String update1, update2 = null;
        if (valueToUpdate.contains(",")) {
            String[] split = valueToUpdate.split(",");
            update1 = split[0];
            update2 = split[1].trim();
        } else
            update1 = valueToUpdate;

        String errorDetail = editScenario.getErrorDetails();
        switch (editScenario.getEditField()) {
            case PREMIUM_TERM:
                PremiumTerm.valueOf(update1).selectPremium(getDriver());
                possibleFiguresPage.clickUpdateButton();
                if (!errorDetail.isEmpty()) {
                    String error = errorDetail.split("::")[0].trim();

                    CustomAssertions.assertText(customClassObj, possibleFiguresPage.getEditedFieldErrorMessage(), error,
                            "Verified whether the error message ios displayed correct or not");
//                        CustomAssertions.assertText(customClassObj, possibleFiguresPage.getSumAssuredTextBoxValue(),
//                                TestData.getPlansConfigData().getMaxSumAssured(currentScenario.getCurrentPlan().getPlanTitle(), currentScenario.getCurrentPlan().getPlanSubTitle()),
//                                "Verified whether the correct amount is auto filled or not:");

//                    PremiumTerm.valueOf(update2).selectPremium(getDriver());
                    String postErrorAction= "";
                    try {
                        postErrorAction = errorDetail.split("::")[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    if (postErrorAction.equalsIgnoreCase("CLOSE"))
                        possibleFiguresPage.closePopup();
                    else
                        possibleFiguresPage.clickUpdateButton();
                }
                CustomLogging.writeInfoLogToReport("Updated Premium Term field");
                break;
            case POLICY_TERM:

                PolicyTerm.valueOf(valueToUpdate).selectPolicy(getDriver());
                possibleFiguresPage.clickUpdateButton();
                CustomLogging.writeInfoLogToReport("Updated Policy Term field");
                break;
            case SUM_ASSURED:
                possibleFiguresPage.fillSumAssuredAmount(valueToUpdate);
                possibleFiguresPage.clickUpdateButton();
                if (!errorDetail.isEmpty()) {
                    String error = errorDetail.split("::")[0].trim();

                    CustomAssertions.assertText(customClassObj, possibleFiguresPage.getEditedFieldErrorMessage(), error,
                            "Verified whether the error message ios displayed correct or not");
//                        CustomAssertions.assertText(customClassObj, possibleFiguresPage.getSumAssuredTextBoxValue(),
//                                TestData.getPlansConfigData().getMaxSumAssured(currentScenario.getCurrentPlan().getPlanTitle(), currentScenario.getCurrentPlan().getPlanSubTitle()),
//                                "Verified whether the correct amount is auto filled or not:");

//                    PremiumTerm.valueOf(update2).selectPremium(getDriver());
                    String postErrorAction= "";
                    try {
                        postErrorAction = errorDetail.split("::")[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    if (postErrorAction.equalsIgnoreCase("CLOSE"))
                        possibleFiguresPage.closePopup();
                    else
                        possibleFiguresPage.clickUpdateButton();
                }
                CustomLogging.writeInfoLogToReport("Updated Sum Assured field");
                break;
            case PREMIUM_FREQUENCY:
                PremiumFrequency.valueOf(valueToUpdate).selectFrequency(getDriver());
                CustomLogging.writeInfoLogToReport("Updated Premium Frequency field");
                break;
            case NO_CHANGE:
                break;
            case PREMIUM:
                possibleFiguresPage.fillPremiumAmount(valueToUpdate);
                possibleFiguresPage.clickUpdateButton();
                if (!errorDetail.isEmpty()) {
                    String error = errorDetail.split("::")[0].trim();

                    CustomAssertions.assertText(customClassObj, possibleFiguresPage.getEditedFieldErrorMessage(), error,
                            "Verified whether the error message ios displayed correct or not");
//                        CustomAssertions.assertText(customClassObj, possibleFiguresPage.getSumAssuredTextBoxValue(),
//                                TestData.getPlansConfigData().getMaxSumAssured(currentScenario.getCurrentPlan().getPlanTitle(), currentScenario.getCurrentPlan().getPlanSubTitle()),
//                                "Verified whether the correct amount is auto filled or not:");

//                    PremiumTerm.valueOf(update2).selectPremium(getDriver());
                    String postErrorAction= "";
                    try {
                        postErrorAction = errorDetail.split("::")[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    if (postErrorAction.equalsIgnoreCase("CLOSE"))
                        possibleFiguresPage.closePopup();
                    else
                        possibleFiguresPage.clickUpdateButton();
                }
                CustomLogging.writeInfoLogToReport("Updated Premium Amount field");
                break;
            case MONTHLY_CASH_BENEFIT:

                possibleFiguresPage.fillMonthlyCashBenefitField(valueToUpdate);
                possibleFiguresPage.clickUpdateButton();
                if (!errorDetail.isEmpty()) {
                    String error = errorDetail.split("::")[0].trim();

                    CustomAssertions.assertText(customClassObj, possibleFiguresPage.getEditedFieldErrorMessage(), error,
                            "Verified whether the error message ios displayed correct or not");
//                        CustomAssertions.assertText(customClassObj, possibleFiguresPage.getSumAssuredTextBoxValue(),
//                                TestData.getPlansConfigData().getMaxSumAssured(currentScenario.getCurrentPlan().getPlanTitle(), currentScenario.getCurrentPlan().getPlanSubTitle()),
//                                "Verified whether the correct amount is auto filled or not:");

//                    PremiumTerm.valueOf(update2).selectPremium(getDriver());
                    String postErrorAction= "";
                    try {
                        postErrorAction = errorDetail.split("::")[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    if (postErrorAction.equalsIgnoreCase("CLOSE"))
                        possibleFiguresPage.closePopup();
                    else
                        possibleFiguresPage.clickUpdateButton();
                }
                CustomLogging.writeInfoLogToReport("Updated Monthly Cash benefit field");
                break;
            case PAYOUT_PERIOD:

                PayoutPeriod.valueOf(update1).selectPayout(getDriver());
                possibleFiguresPage.clickUpdateButton();
                if (!errorDetail.isEmpty()) {
                    String error = errorDetail.split("::")[0].trim();

                    CustomAssertions.assertText(customClassObj, possibleFiguresPage.getEditedFieldErrorMessage(), error,
                            "Verified whether the error message ios displayed correct or not");
//                        CustomAssertions.assertText(customClassObj, possibleFiguresPage.getSumAssuredTextBoxValue(),
//                                TestData.getPlansConfigData().getMaxSumAssured(currentScenario.getCurrentPlan().getPlanTitle(), currentScenario.getCurrentPlan().getPlanSubTitle()),
//                                "Verified whether the correct amount is auto filled or not:");

//                    PremiumTerm.valueOf(update2).selectPremium(getDriver());
                    String postErrorAction= "";
                    try {
                        postErrorAction = errorDetail.split("::")[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    if (postErrorAction.equalsIgnoreCase("CLOSE"))
                        possibleFiguresPage.closePopup();
                    else
                        possibleFiguresPage.clickUpdateButton();
                }

                CustomLogging.writeInfoLogToReport("Updated payout Period field");
                break;
            case START_AGE_CASH_BENEFIT:
                StartAgeForCashBenefit.valueOf(valueToUpdate).selectStartAge(getDriver());
                possibleFiguresPage.clickUpdateButton();

                if (!errorDetail.isEmpty()) {
                    String error = errorDetail.split("::")[0].trim();

                    CustomAssertions.assertText(customClassObj, possibleFiguresPage.getEditedFieldErrorMessage(), error,
                            "Verified whether the error message ios displayed correct or not");
//                        CustomAssertions.assertText(customClassObj, possibleFiguresPage.getSumAssuredTextBoxValue(),
//                                TestData.getPlansConfigData().getMaxSumAssured(currentScenario.getCurrentPlan().getPlanTitle(), currentScenario.getCurrentPlan().getPlanSubTitle()),
//                                "Verified whether the correct amount is auto filled or not:");

//                    PremiumTerm.valueOf(update2).selectPremium(getDriver());
                    String postErrorAction= "";
                    try {
                        postErrorAction = errorDetail.split("::")[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    if (postErrorAction.equalsIgnoreCase("CLOSE"))
                        possibleFiguresPage.closePopup();
                    else
                        possibleFiguresPage.clickUpdateButton();
                }

                CustomLogging.writeInfoLogToReport("Updated Start Age For Cash Benefit field");
                break;

        }
        /*PossibleFiguresBean actualValues = new PossibleFiguresBean.Builder()
                .premiumAmount(possibleFiguresPage.getPremiumAmount())
                .premiumTerm(PremiumTerm.getByGuiValue(possibleFiguresPage.getPremiumTerm()))
                .premiumFrequency(PremiumFrequency.EMPTY)
                .policyTerm(possibleFiguresPage.getPolicyTerm())
                .sumAssured("")
                .Build();
        return actualValues;*/
        return null;


    }

    private void assertPossibleFiguresPage(CustomClass customClassObj, PossibleFiguresBean expBean) {
        PossibleFiguresPage possibleFiguresPage = DesktopPageObjects.possibleFiguresPage(getDriver());

        CustomAssertions.assertTrue(customClassObj, possibleFiguresPage.isUserOnPossibleFiguresPage(),
                "Verified whether the User is on Possible Figures page or not");


        //TODO: assert all values
        if (!expBean.getPremiumAmount().isEmpty())
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPremiumAmount(), expBean.getPremiumAmount(),
                    "Verified whether the Premium displayed correct or not");
//            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPremiumTerm(), possibleFiguresBeanMonthly.getPremiumTerm(),
//                    "Verified whether the Premium term displayed correct or not: " + indProductDetails.getName() + " | " + indProductDetails.getSubTitle());

        if (!expBean.getPolicyTerm().isEmpty())
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPolicyTerm(), expBean.getPolicyTerm(),
                    "Verified whether the Policy term displayed correct or not");

        if (!expBean.getSumAssured().isEmpty())
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getSumAssured(), expBean.getSumAssured(),
                    "Verified whether the Sum Assured value displayed correct or not");

        if (expBean.getStartAgeForCashBenefit() != StartAgeForCashBenefit.EMPTY)
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getStartAgeForCashBenefit(), expBean.getStartAgeForCashBenefit().getExpGUIValue(),
                    "Verified whether the Start Age For Cash Benefit value displayed correct or not");

        if (expBean.getPayoutPeriod() != PayoutPeriod.EMPTY)
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getPayoutPeriod(), expBean.getPayoutPeriod().getExpGUIValue(),
                    "Verified whether the Payout Period value displayed correct or not");

        if (!expBean.getMonthlyCashBenefit().isEmpty())
            CustomAssertions.assertText(customClassObj, possibleFiguresPage.getMonthlyCashBenefit(), expBean.getMonthlyCashBenefit(),
                    "Verified whether the Monthly Cash Benefit value displayed correct or not");

        PossibleFiguresBean.ViewDetails viewDetails = expBean.getViewDetails();
        if (viewDetails != null) {

            possibleFiguresPage.clickViewMoreDetailsLink();

            if (!viewDetails.getScenarioA().isEmpty())
                CustomAssertions.assertText(customClassObj, possibleFiguresPage.getViewMoreDetails_ScenarioA(), viewDetails.getScenarioA(),
                        "Verified whether the View Details Scenario A displayed correct or not");
            if (!viewDetails.getScenarioB().isEmpty())
                CustomAssertions.assertText(customClassObj, possibleFiguresPage.getViewMoreDetails_ScenarioB(), viewDetails.getScenarioB(),
                        "Verified whether the View Details Scenario B displayed correct or not");

            if (!viewDetails.getGuaranteedMaturity().isEmpty())
                CustomAssertions.assertText(customClassObj, possibleFiguresPage.getViewMoreDetails_GuaranteedMaturity(), viewDetails.getGuaranteedMaturity(),
                        "Verified whether the View Details Guaranteed Maturity displayed correct or not");

            if (!viewDetails.getYieldA().isEmpty())
                CustomAssertions.assertText(customClassObj, possibleFiguresPage.getViewMoreDetails_YieldA(), viewDetails.getYieldA(),
                        "Verified whether the View Details Yield A displayed correct or not");
            if (!viewDetails.getYieldB().isEmpty())
                CustomAssertions.assertText(customClassObj, possibleFiguresPage.getViewMoreDetails_YieldB(), viewDetails.getYieldB(),
                        "Verified whether the View Details Yield B displayed correct or not");

            if (!viewDetails.getYearlyCashBenefit().isEmpty())
                CustomAssertions.assertText(customClassObj, possibleFiguresPage.getViewMoreDetails_YearlyCashBenefit(), viewDetails.getYearlyCashBenefit(),
                        "Verified whether the View Details Yearly Cash Benefit displayed correct or not");

            possibleFiguresPage.closeViewDeatilsPopup();
        }

    }

    private void submitRiderPage(CustomClass customClassObj) {
        CommonPage commonPage = DesktopPageObjects.commonPage(getDriver());
        WantARiderPage wantARiderPage = DesktopPageObjects.wantARiderPage(getDriver());
        SelectedRiderPage selectedRiderPage = DesktopPageObjects.selectedRiderPage(getDriver());

        CustomAssertions.assertText(customClassObj, wantARiderPage.getPageHeader(), "Do you want a rider?", "Verified whether the page header displayed correct or not");
        List<RiderScenario> riderScenarios = currentScenario.getRiderScenarios();

        riderScenarios.stream()
                .forEach(currentRider -> {
                    currentRider.getRider().selectRider(getDriver());
                    commonPage.clickNextButton();

                    /*CustomAssertions.assertText(customClassObj,
                            selectedRiderPage.getSelectedRiderPremiumMessage(), currentRider.getRiderMessage(),
                            "Verified the Rider message");*/
                    if (currentRider.getAcceptRider().equalsIgnoreCase("Y"))
                        selectedRiderPage.clickYesPleaseProceedButton();
                    else if (currentRider.getAcceptRider().equalsIgnoreCase("N"))
                        selectedRiderPage.clickNoThanksButton();
                    else if (currentRider.getAcceptRider().equalsIgnoreCase("CHANGE")) {
                        wantARiderPage.selectWantToChangeAmount();
                        commonPage.clickNextButton();
                    }

                    if (currentScenario.getRiderEditScenarios().size() > 0) {
                        currentScenario.getRiderEditScenarios().forEach(currentRiderEditScenario -> {
                            if (currentRiderEditScenario.getEditField().equalsIgnoreCase("AMOUNT")) {
                                currentRider.getRider().editAmount(getDriver(), currentRiderEditScenario.getValueToEdit());
                            }
                            if (!currentRiderEditScenario.getErrorMessage().isEmpty()) {
                                CustomAssertions.assertText(customClassObj, wantARiderPage.getEditedFieldErrorMessage(),
                                        currentRiderEditScenario.getErrorMessage(),
                                        "Verified whether the error message ios displayed correct or not");
                                wantARiderPage.clickUpdateButton();
                            }

                            int index = currentScenario.getRiderEditScenarios().indexOf(currentRiderEditScenario);
                            RiderInfo currentRiderInfo = currentScenario.getUpdatedRiderInfos().get(index);
                            selectedRiderPage.getSumAssuredAmount();

                            CustomAssertions.assertText(customClassObj, selectedRiderPage.getPremiumAmount(),
                                    currentRiderInfo.getPremiumAmount(),
                                    "Verified the rider premium amount");
                        });
                    }

                    commonPage.clickNextButton();
                });


        System.out.println();

    }

    private void submitSummaryPage(CustomClass customClassObj) {
        ProductTogglerSummaryPage productTogglerSummaryPage = DesktopPageObjects.productTogglerSummaryPage(getDriver());

        CustomAssertions.assertText(customClassObj, productTogglerSummaryPage.getPageHeader(), "Here's your plan!",
                "Verified whether the page header displayed correct or not");

        System.out.println();

    }
}
