package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class Redeem7Eleven extends DesktopBasePage {
    public static final String PAGENAME;

    static {
        PAGENAME = "Redeem7Eleven";
    }

    private final By pageHeader = LocatorUtils.getLocator(PAGENAME, "pageHeader");
    private final By dealsTab = LocatorUtils.getLocator(PAGENAME, "dealsTab");
    private final By myCouponsTab = LocatorUtils.getLocator(PAGENAME, "myCouponsTab");
    private final By loginMessage_Deals = LocatorUtils.getLocator(PAGENAME, "preLoginMessage_Deals");
    private final By loginButton_Deals = LocatorUtils.getLocator(PAGENAME, "preLoginButton_Deals");
    private final By loginMessage_MyCoupons = LocatorUtils.getLocator(PAGENAME, "preLoginMessage_MyCoupons");
    private final By preLoginButton_MyCoupons = LocatorUtils.getLocator(PAGENAME, "preLoginButton_MyCoupons");
    private final By deals_AvailableCoupons_SectionHeader = LocatorUtils.getLocator(PAGENAME, "deals_AvailableCoupons_SectionHeader");
    private final By deals_AvailableCouponCard = LocatorUtils.getLocator(PAGENAME, "deals_AvailableCouponCard");
    private final By deals_AvailableCouponCard_Image = LocatorUtils.getLocator(PAGENAME, "deals_AvailableCouponCard_Image");
    private final By deals_AvailableCouponCard_Name = LocatorUtils.getLocator(PAGENAME, "deals_AvailableCouponCard_Name");
    private final By deals_AvailableCouponCard_Description = LocatorUtils.getLocator(PAGENAME, "deals_AvailableCouponCard_Description");
    private final By deals_AvailableCouponCard_Amount = LocatorUtils.getLocator(PAGENAME, "deals_AvailableCouponCard_Amount");
    private final By deals_AvailableCouponCard_Condition = LocatorUtils.getLocator(PAGENAME, "deals_AvailableCouponCard_Condition");
    private final By truePoints = LocatorUtils.getLocator(PAGENAME, "truePoints");

    public Redeem7Eleven(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return ElementUtils.getText(driver, pageHeader);
    }

    public void clickDealsTab() {
        ElementUtils.clickObject(driver, dealsTab);
        hardWait(0.5);
    }

    public boolean isDealsTabDisplayed() {
        return ElementUtils.isElementDisplayed(driver, dealsTab);
    }

    public void clickMyCouponsTab() {
        ElementUtils.clickObject(driver, myCouponsTab);
        new LaunchPageActions(driver).waitForProgressBar();
    }

    public boolean isMyCouponsTabDisplayed() {
        return ElementUtils.isElementDisplayed(driver, myCouponsTab);
    }

    public String getLoginMessage_Deals() {
        return ElementUtils.getText(driver, loginMessage_Deals);
    }

    public void clickLoginButton_Deals() {
        ElementUtils.clickObject(driver, loginButton_Deals);
        hardWait(10);
    }

    public boolean isLoginButton_DealsDisplayed() {
        return ElementUtils.isElementDisplayed(driver, loginButton_Deals);
    }

    public String getLoginMessage_MyCoupons() {
        return ElementUtils.getText(driver, loginMessage_MyCoupons);
    }

    public void clickPreLoginButton_MyCoupons() {
        ElementUtils.clickObject(driver, preLoginButton_MyCoupons);
        hardWait(10);
    }

    public boolean isPreLoginButton_MyCouponsDisplayed() {
        return ElementUtils.isElementDisplayed(driver, preLoginButton_MyCoupons);
    }

    public String getAvailableCouponsSectionHeader() {
        return ElementUtils.getText(driver, deals_AvailableCoupons_SectionHeader);
    }

    public boolean isFirstAvailableCouponCardDisplayed() {
        return ElementUtils.isElementDisplayed(driver, deals_AvailableCouponCard);
    }

    public String getFirstAvailableCardName() {
        return ElementUtils.getText(driver, deals_AvailableCouponCard_Name);
    }

    public String getFirstAvailableCardDescription() {
        return ElementUtils.getText(driver, deals_AvailableCouponCard_Description);
    }

    public String getFirstAvailableCardAmount() {
        return ElementUtils.getText(driver, deals_AvailableCouponCard_Amount);
    }

    public String getFirstAvailableCardCondition() {
        return ElementUtils.getText(driver, deals_AvailableCouponCard_Condition);
    }

    public String getTruePoints() {
        return ElementUtils.getText(driver, truePoints);
    }

}
