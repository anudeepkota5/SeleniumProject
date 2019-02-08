package com.mckinsey.lime.mobilePages;

import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.utils.CommonEnums;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.ElementUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class SideMenu extends MobileBasePage {

    private final By hamburger = By.id("header_side_bar_mobile_hamburger");
    private final By englishLanguage = By.id("header_side_bar_mobile_en");
    private final By thaiLanguage = By.id("header_side_bar_mobile_th}");
    private final By menuItem_AccountSetting = By.xpath("//div[./label[@text='WEB__ACCOUNT_SETTING']]");
    private final By menuItem_eBill = By.id("header_side_bar_mobile_WEB__ACCOUNT_BILLING_0");
    private final By menuItem_manageBusinessNumber = By.id("header_side_bar_mobile_BUSINESS_SIM__MANAGE_BUSINESS_TITLE_1");
    private final By menuItem_Redeam7Eleven = By.xpath("//div[@text='WEB__SEVEN_ELEVEN']");
    private final By menuItem_payForOthers = By.xpath("//div[@text='WEB__PAY_TOPUP_FOR_OTHER']");
    private final By sideMenu_Home = By.xpath("//div[@text='WEB__HEADER_DROPDOWN_HOME']");
    private final By sideMenu_TopUp = By.xpath("//div[@text='WEB__TOP_UP']");
    private final By menuItem_History = By.xpath("//div[./label[@text='WEB__BILL_PAYMENT_HISTORY']]");
    private final By menuItem_PaymentHistory = By.id("header_side_bar_mobile_WEB__BILL_PAYMENT_HISTORY_PAYMENT_0");
    private final By menuItem_buyExtraPackage = By.xpath("//div[@text='WEB__EXTRA_PACKAGE_SIDE']");
    private final By menuItem_changePricePlan = By.xpath("//div[@text='WEB__CHANGE_PACKAGE']");
    private final By menuItem_trueCare = By.xpath("//div[@text='WEB__TRUE_CARE_CHAT']");
    private final By menuItem_billingHistory = By.id("header_side_bar_mobile_WEB__BILL_PAYMENT_HISTORY_BILLING_1");
    private final By menuItem_BillsAndUsage = By.xpath("//div[@text='WEB__BILL_USAGE']");

    public SideMenu(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    private void clickHistoryMenu() {
        ElementUtils.clickObject(driver, menuItem_History);
    }

    private void clickAccountSettingMenu() {
        ElementUtils.clickObject(driver, menuItem_AccountSetting);
    }

    public void clickRedeam7ElevenMenu() {
        CustomLogging.writeToReport("Clicking on Redeem 7-Eleven side menu");
        ElementUtils.clickObject(driver, menuItem_Redeam7Eleven);
        waitForLoaders();
    }

    public void clickTopUpMenu() {
        CustomLogging.writeToReport("Clicking on TopUp side menu");
        ElementUtils.clickObject(driver, sideMenu_TopUp);
        waitForLoaders();
    }


    public void clickHamBurger() {
        CustomLogging.writeToReport("Clicking on hamBurger icon");
        hardWait(1);
        ElementUtils.clickObject(driver, hamburger);
        hardWait(1);
    }

    public void switchToEnglishLocale() {

        clickHamBurger();

        clickSwitchEnglish();
        clickHamBurger();
//        clickHomeButton();
        waitForPageLoaderToComplete();
        CustomLogging.writeInfoLogToReport("switched to English locale");
        if (TestData.getConfigData().getDeviceType() == CommonEnums.DeviceType.IOS) {
            hardWait(1);
            ElementUtils.acceptAlert(driver, TestData.getConfigData().getExplicitTimeOutType2());
        }


    }

    public void clickSwitchEnglish() {

        if (!isEnglishSelected()) {
            CustomLogging.writeToReport("Clicking on English from side menu");
            ElementUtils.clickObject(driver, englishLanguage);
        }
    }

    public boolean isEnglishSelected() {
        if (ElementUtils.getVisibleElement(driver, englishLanguage).getAttribute("class").contains("_2pddHQMPxCZW5HOSxzEoGh")) {
            return true;
        } else
            return false;
    }

    public void clickHomeButton() {
        CustomLogging.writeToReport("Clicking on Home menu from side bar");
        ElementUtils.clickObject(driver, sideMenu_Home);
        waitForPageLoaderToComplete();

    }

    public void openPayForOthers() {
        clickHamBurger();

        CustomLogging.writeToReport("Opening PayForOthers side menu");
        ElementUtils.clickObject(driver, menuItem_payForOthers);
        waitForLoaders();
    }

    public void navigate2Ebill() {
        clickHamBurger();
        clickAccountSettingMenu();
        clickEbillMenu();
    }

    public void clickEbillMenu() {
        ElementUtils.clickObject(driver, menuItem_eBill);
        waitForLoaders();
    }

    public void clickPaymentHistoryMenu() {
        ElementUtils.clickObject(driver, menuItem_PaymentHistory);
        waitForLoaders();
    }

    public void clickBuyExtraPackageMenu() {
        ElementUtils.clickObject(driver, menuItem_buyExtraPackage);
        waitForLoaders();
    }

    public void clickChangePricePlanMenu() {
        ElementUtils.clickObject(driver, menuItem_changePricePlan);
        waitForPageLoaderToComplete();
    }

    public void clickTrueCareMenu() {
        ElementUtils.clickObject(driver, menuItem_trueCare);
        waitForPageLoaderToComplete();
    }


    public void clickBillingHistoryMenu() {
        ElementUtils.clickObject(driver, menuItem_billingHistory);
        waitForLoaders();
    }

    public void navigate2PaymentHistory() {
        clickHamBurger();
        clickHistoryMenu();
        clickPaymentHistoryMenu();
    }


    public void navigate2BuyExtraPackage() {
        clickHamBurger();
        clickBuyExtraPackageMenu();
    }

    public void navigate2ChangePricePlan() {
        CustomLogging.writeInfoLogToReport("Navigating to Change Package from side menu");
        clickHamBurger();
        clickChangePricePlanMenu();
    }

    public void navigate2TrueCare() {
        clickHamBurger();
        clickTrueCareMenu();
    }

    public void navigate2BillingHistory() {
        clickHamBurger();
        clickHistoryMenu();
        clickBillingHistoryMenu();
    }

    public void navigate2Home() {
        clickHamBurger();
        clickHomeButton();
    }
}
