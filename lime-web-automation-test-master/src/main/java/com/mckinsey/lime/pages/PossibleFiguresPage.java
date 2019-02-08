package com.mckinsey.lime.pages;

import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.CommonEnums;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PossibleFiguresPage extends DesktopBasePage {
    public final static String PAGE_NAME;

    static {
        PAGE_NAME = "PossibleFiguresPage";
    }

    PossibleFiguresPage(WebDriver driver) {
        super(driver);
    }

    private final By chosenPlan = LocatorUtils.getLocator(PAGE_NAME, "ChosenPlan");
    private final By premiumAmount = LocatorUtils.getLocator(PAGE_NAME, "PremiumAmount");
    private final By premiumTerm = LocatorUtils.getLocator(PAGE_NAME, "PremiumTerm");
    private final By premiumFrequency_Monthly = LocatorUtils.getLocator(PAGE_NAME, "PremiumFrequency_Monthly");
    private final By premiumFrequency_Yearly = LocatorUtils.getLocator(PAGE_NAME, "PremiumFrequency_Yearly");
    private final By sumAssured = LocatorUtils.getLocator(PAGE_NAME, "SumAssured");
    private final By startAgeForCashBenefit = LocatorUtils.getLocator(PAGE_NAME, "StartAgeForCashBenefit");
    private final By payoutPeriod = LocatorUtils.getLocator(PAGE_NAME, "PayoutPeriod");
    private final By monthlyCashBenefit = LocatorUtils.getLocator(PAGE_NAME, "GuaranteedMonthlyCashBenefit");
    private final By editSumAssuredIcon = LocatorUtils.getLocator(PAGE_NAME, "EditSumAssuredIcon");
    private final By sumAssuredTextField = LocatorUtils.getLocator(PAGE_NAME, "SumAssuredTextField");
    private final By premiumAmountTextField = LocatorUtils.getLocator(PAGE_NAME, "PremiumAmountTextField");
    private final By policyTerm = LocatorUtils.getLocator(PAGE_NAME, "PremiumTerm");
    private final By revertToOriginalValuesLink = LocatorUtils.getLocator(PAGE_NAME, "RevertToOriginalValuesLink");
    private final By editPremiumAmountIcon = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumAmountIcon");
    private final By editPremiumTermIcon = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTermIcon");
    private final By editPolicyTermIcon = LocatorUtils.getLocator(PAGE_NAME, "EditPolicyTermIcon");
    private final By mobile_GotIt = LocatorUtils.getLocator(PAGE_NAME, "Mobile_GotIt");
    private final By editPremiumTerm_YearsDropdown = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_YearsDropdown");
    private final By editPremiumTerm_5Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_5Years");
    private final By editPremiumTerm_10Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_10Years");
    private final By editPremiumTerm_15Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_15Years");
    private final By editPremiumTerm_20Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_20Years");
    private final By editPremiumTerm_25Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_25Years");
    private final By editPremiumTerm_30Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_30Years");
    private final By editPremiumTerm_35Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_35Years");
    private final By editPremiumTerm_3Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_3Years");
    private final By editPremiumTerm_24Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_24Years");
    private final By editPremiumTerm_29Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_29Years");
    private final By editPremiumTerm_12Years = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_12Years");
    private final By editPremiumTerm_AgeDropdown = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_AgeDropdown");
    private final By editPremiumTerm_UpToAge54 = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_UpToAge54");
    private final By editPremiumTerm_UpToAge61 = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_UpToAge61");
    private final By editPremiumTerm_UpToAge64 = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_UpToAge64");
    private final By editPremiumTerm_UpToAge69 = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_UpToAge69");
    private final By editPremiumTerm_UpToAge74 = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_UpToAge74");
    private final By editPremiumTerm_UpToAge84 = LocatorUtils.getLocator(PAGE_NAME, "EditPremiumTerm_UpToAge84");

    private final By editPolicyTerm_13Years = LocatorUtils.getLocator(PAGE_NAME, "EditPolicyTerm_13Years");
    private final By editPolicyTerm_19Years = LocatorUtils.getLocator(PAGE_NAME, "EditPolicyTerm_19Years");
    private final By editPolicyTerm_20Years = LocatorUtils.getLocator(PAGE_NAME, "EditPolicyTerm_20Years");
    private final By editPolicyTerm_21Years = LocatorUtils.getLocator(PAGE_NAME, "EditPolicyTerm_21Years");
    private final By editPolicyTerm_22Years = LocatorUtils.getLocator(PAGE_NAME, "EditPolicyTerm_22Years");
    private final By editPolicyTerm_23Years = LocatorUtils.getLocator(PAGE_NAME, "EditPolicyTerm_23Years");
    private final By editPolicyTerm_24Years = LocatorUtils.getLocator(PAGE_NAME, "EditPolicyTerm_24Years");
    private final By editPolicyTerm_25Years = LocatorUtils.getLocator(PAGE_NAME, "EditPolicyTerm_25Years");

    private final By updatePremiumTermButton = LocatorUtils.getLocator(PAGE_NAME, "UpdatePremiumTermButton");
    private final By closePopup = LocatorUtils.getLocator(PAGE_NAME, "ClosePopup");
    private final By errorMsg_EditedField = LocatorUtils.getLocator(PAGE_NAME, "ErrorMsg_EditedField");

    private final By viewMoreDetailsLink = LocatorUtils.getLocator(PAGE_NAME, "ViewMoreDetailsLink");
    private final By viewMoreDetails_ScenarioA = LocatorUtils.getLocator(PAGE_NAME, "ViewMoreDetails_ScenarioA");
    private final By viewMoreDetails_ScenarioB = LocatorUtils.getLocator(PAGE_NAME, "ViewMoreDetails_ScenarioB");
    private final By viewMoreDetails_GuaranteedMaturityText = LocatorUtils.getLocator(PAGE_NAME, "ViewMoreDetails_GuaranteedMaturityText");
    private final By viewMoreDetails_YieldA = LocatorUtils.getLocator(PAGE_NAME, "ViewMoreDetails_YieldA");
    private final By viewMoreDetails_YieldB = LocatorUtils.getLocator(PAGE_NAME, "ViewMoreDetails_YieldB");
    private final By viewMoreDetails_YearlyCashBenefit = LocatorUtils.getLocator(PAGE_NAME, "ViewMoreDetails_YearlyCashBenefit");
    private final By closeIcon_ViewMoreDetails = LocatorUtils.getLocator(PAGE_NAME, "CloseIcon_ViewMoreDetails");

    private final By editPayoutPeriodIcon = LocatorUtils.getLocator(PAGE_NAME, "EditPayoutPeriodIcon");
    private final By editPayoutPeriod_Dropdown = LocatorUtils.getLocator(PAGE_NAME, "EditPayoutPeriod_Dropdown");
    private final By editPayoutPeriod_Years10 = LocatorUtils.getLocator(PAGE_NAME, "EditPayoutPeriod_Years10");
    private final By editPayoutPeriod_Years20 = LocatorUtils.getLocator(PAGE_NAME, "EditPayoutPeriod_Years20");
    private final By editPayoutPeriod_Years30 = LocatorUtils.getLocator(PAGE_NAME, "EditPayoutPeriod_Years30");

    private final By editStartAgeForCashBenefitIcon = LocatorUtils.getLocator(PAGE_NAME, "EditStartAgeForCashBenefitIcon");
    private final By editStartAgeForCashBenefit_Dropdown = LocatorUtils.getLocator(PAGE_NAME, "EditStartAgeForCashBenefit_Dropdown");
    private final By editStartAgeForCashBenefit_50 = LocatorUtils.getLocator(PAGE_NAME, "EditStartAgeForCashBenefit_50");
    private final By editStartAgeForCashBenefit_55 = LocatorUtils.getLocator(PAGE_NAME, "EditStartAgeForCashBenefit_55");
    private final By editStartAgeForCashBenefit_60 = LocatorUtils.getLocator(PAGE_NAME, "EditStartAgeForCashBenefit_60");
    private final By editStartAgeForCashBenefit_65 = LocatorUtils.getLocator(PAGE_NAME, "EditStartAgeForCashBenefit_65");

    private final By editGuaranteedMonthlyCashBenefit = LocatorUtils.getLocator(PAGE_NAME, "EditMonthlyCashBenefitIcon");
    private final By monthlyCashBenefitTextField = LocatorUtils.getLocator(PAGE_NAME, "MonthlyCashBenefitTextField");

    public String getEditedFieldErrorMessage() {
        return ElementUtils.getText(driver, errorMsg_EditedField);
    }

    public boolean isUserOnPossibleFiguresPage() {
        //TODO
        return true;
    }

    public void clickGotItButtonForMobile() {
        if (TestData.getConfigData().getAppType() == CommonEnums.AppType.MOBILE)
            ElementUtils.clickObject(driver, mobile_GotIt);
    }

    public String getChosenPlanName() {
        return ElementUtils.getText(driver, chosenPlan);
    }

    public String getPremiumAmount() {
        return ElementUtils.getText(driver, premiumAmount)
                .replace("$", "")
                .replace(",", "")
                .trim();
    }

    public String getStartAgeForCashBenefit() {
        return ElementUtils.getText(driver, startAgeForCashBenefit)
                .replace("years old", "")
                .trim();
    }

    public String getPayoutPeriod() {
        return ElementUtils.getText(driver, payoutPeriod)
                .replace("years", "")
                .trim();
    }

    public String getMonthlyCashBenefit() {
        return ElementUtils.getText(driver, monthlyCashBenefit)
                .replace("$", "")
                .replace(",", "")
                .trim();
    }

    public String getPremiumTerm() {
        return ElementUtils.getText(driver, premiumTerm).trim();
    }

    public boolean isPremiumFrequencyMonthlySelected() {
        //TODO
        String color = ElementUtils.getCssAttribute(driver, premiumFrequency_Monthly, "color");
        return color.equalsIgnoreCase("rgba(245, 128, 35, 1)");
//        #f58023
    }

    public boolean isPremiumFrequencyYearlySelected() {
        //TODO
        String color = ElementUtils.getCssAttribute(driver, premiumFrequency_Yearly, "color");
        return color.equalsIgnoreCase("rgba(245, 128, 35, 1)");
    }

    public void selectPremiumFrequencyMonthly() {
        ElementUtils.clickObject(driver, premiumFrequency_Monthly);
    }

    public void selectPremiumFrequencyYearly() {
        ElementUtils.clickObject(driver, premiumFrequency_Yearly);
    }

    public String getSumAssured() {
        //FIXME
        return ElementUtils.getText(driver, sumAssured)
                .replace("$", "")
                .replace(",", "")
                .trim();
    }

    public String getPolicyTerm() {
        return ElementUtils.getText(driver, policyTerm);
    }

    public void clickRevertToOriginalValuesLink() {
        ElementUtils.clickObject(driver, revertToOriginalValuesLink);
    }

    public void clickEditPremiumAmountIcon() {
        ElementUtils.clickObject(driver, editPremiumAmountIcon);
    }

    public void clickEditPremiumTermIcon() {
        ElementUtils.clickObject(driver, editPremiumTermIcon);
    }

    public void clickEditPolicyTermIcon() {
        ElementUtils.clickObject(driver, editPolicyTermIcon);
    }

    public void clickViewMoreDetailsLink() {
        ElementUtils.clickObject(driver, viewMoreDetailsLink);
    }

    public String getViewMoreDetails_ScenarioA() {
        return ElementUtils.getText(driver, viewMoreDetails_ScenarioA)
                .trim();
    }

    public String getViewMoreDetails_ScenarioB() {
        return ElementUtils.getText(driver, viewMoreDetails_ScenarioB)
                .trim();
    }

    public String getViewMoreDetails_GuaranteedMaturity() {
        String text = ElementUtils.getText(driver, viewMoreDetails_GuaranteedMaturityText);
        return text.substring(text.lastIndexOf("=")).
                replace("=", "")
                .trim();
    }

    public String getViewMoreDetails_YieldA() {
        return ElementUtils.getText(driver, viewMoreDetails_YieldA)
                .replace("p.a", "")
                .trim();
    }

    public String getViewMoreDetails_YieldB() {
        return ElementUtils.getText(driver, viewMoreDetails_YieldB)
                .replace("p.a", "")
                .trim();
    }

    public String getViewMoreDetails_YearlyCashBenefit() {
        return ElementUtils.getText(driver, viewMoreDetails_YearlyCashBenefit);
    }

    public void closeViewDeatilsPopup() {
        ElementUtils.clickObject(driver, closeIcon_ViewMoreDetails);
    }

    public void fillPremiumAmount(String premium) {
        ElementUtils.sendKeys(driver, premiumAmountTextField, premium);
    }

    public void clickSumAssuredEditIcon() {
        ElementUtils.clickObject(driver, editSumAssuredIcon);
    }

    public void fillSumAssuredAmount(String sumAssuredValue) {
        ElementUtils.sendKeys(driver, sumAssuredTextField, sumAssuredValue);
    }

    public String getSumAssuredTextBoxValue() {
        return ElementUtils.getHtmlAttribute(driver, sumAssuredTextField, "value");
    }

    public void clickYearsDropdown() {
        ElementUtils.clickObject(driver, editPremiumTerm_YearsDropdown);
    }

    public void select5YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_5Years);
    }

    public void select10YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_10Years);
    }

    public void select15YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_15Years);
    }

    public void select20YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_20Years);
    }

    public void select25YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_25Years);
    }

    public void select30YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_30Years);
    }

    public void select35YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_35Years);
    }

    public void select3YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_3Years);
    }

    public void select24YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_24Years);
    }

    public void select29YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_29Years);
    }

    public void select12YearsFromPremiumTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_12Years);
    }

    public void clickUpToAgeDropdown() {
        ElementUtils.clickObject(driver, editPremiumTerm_AgeDropdown);
    }

    public void selectUpToAge54FromPremiumTermDropdown() {
        clickUpToAgeDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_UpToAge54);
    }

    public void selectUpToAge61FromPremiumTermDropdown() {
        clickUpToAgeDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_UpToAge61);
    }

    public void selectUpToAge64FromPremiumTermDropdown() {
        clickUpToAgeDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_UpToAge64);
    }

    public void selectUpToAge69FromPremiumTermDropdown() {
        clickUpToAgeDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_UpToAge69);
    }

    public void selectUpToAge74FromPremiumTermDropdown() {
        clickUpToAgeDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_UpToAge74);
    }

    public void selectUpToAge84FromPremiumTermDropdown() {
        clickUpToAgeDropdown();
        ElementUtils.clickObject(driver, editPremiumTerm_UpToAge84);
    }

    public void select13YearsFromPolicyTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPolicyTerm_13Years);
    }

    public void select19YearsFromPolicyTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPolicyTerm_19Years);
    }

    public void select20YearsFromPolicyTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPolicyTerm_20Years);
    }

    public void select21YearsFromPolicyTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPolicyTerm_21Years);
    }

    public void select22YearsFromPolicyTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPolicyTerm_22Years);
    }

    public void select23YearsFromPolicyTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPolicyTerm_23Years);
    }

    public void select24YearsFromPolicyTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPolicyTerm_24Years);
    }

    public void select25YearsFromPolicyTermDropdown() {
        clickYearsDropdown();
        ElementUtils.clickObject(driver, editPolicyTerm_25Years);
    }

    public void clickUpdateButton() {
        ElementUtils.clickObject(driver, updatePremiumTermButton);
        waitForLoaders();
    }

    public void closePopup() {
        ElementUtils.clickObject(driver, closePopup);
        waitForLoaders();
    }

    public void clickPayoutYearsDropdown() {
        ElementUtils.clickObject(driver, editPayoutPeriod_Dropdown);
    }

    public void selectYears10FromPayoutPeriodDropdown() {
        clickPayoutYearsDropdown();
        ElementUtils.clickObject(driver, editPayoutPeriod_Years10);
    }

    public void selectYears20FromPayoutPeriodDropdown() {
        clickPayoutYearsDropdown();
        ElementUtils.clickObject(driver, editPayoutPeriod_Years20);
    }

    public void selectYears30FromPayoutPeriodDropdown() {
        clickPayoutYearsDropdown();
        ElementUtils.clickObject(driver, editPayoutPeriod_Years30);
    }

    public void clickStartAgeForCashBenefitDropdown() {
        ElementUtils.clickObject(driver, editStartAgeForCashBenefit_Dropdown);
    }

    public void selectStartAge50ForCashBenefitDropdown() {
        clickStartAgeForCashBenefitDropdown();
        ElementUtils.clickObject(driver, editStartAgeForCashBenefit_50);
    }

    public void selectStartAge55ForCashBenefitDropdown() {
        clickStartAgeForCashBenefitDropdown();
        ElementUtils.clickObject(driver, editStartAgeForCashBenefit_55);
    }

    public void selectStartAge60ForCashBenefitDropdown() {
        clickStartAgeForCashBenefitDropdown();
        ElementUtils.clickObject(driver, editStartAgeForCashBenefit_60);
    }

    public void selectStartAge65ForCashBenefitDropdown() {
        clickStartAgeForCashBenefitDropdown();
        ElementUtils.clickObject(driver, editStartAgeForCashBenefit_65);
    }

    public void clickEditPayoutPeriodIcon() {
        ElementUtils.clickObject(driver, editPayoutPeriodIcon);
    }

    public void clickEditStartAgeForCashBenefitIcon() {
        ElementUtils.clickObject(driver, editStartAgeForCashBenefitIcon);
    }

    public void clickEditGuaranteedMonthlyCashBenefit() {
        ElementUtils.clickObject(driver, editGuaranteedMonthlyCashBenefit);
    }

    public void fillMonthlyCashBenefitField(String valueToUpdate) {
        ElementUtils.sendKeys(driver, monthlyCashBenefitTextField, valueToUpdate);
    }
}
