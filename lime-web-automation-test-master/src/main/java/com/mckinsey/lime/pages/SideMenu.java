package com.mckinsey.lime.pages;

import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.ElementUtils;
import com.mckinsey.lime.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated
public class SideMenu extends DesktopBasePage {

    public static final String PAGENAME;

    static {
        PAGENAME = "SideMenu";
    }

    public final By hamburger = LocatorUtils.getLocator(PAGENAME, "icon_hamburger");
    public final By icon_hamburgerAfterLogin = LocatorUtils.getLocator(PAGENAME, "icon_hamburgerAfterLogin");
    public final By englishLanguage = LocatorUtils.getLocator(PAGENAME, "toggleLink_englishLanguage");
    public final By menuItem_AccountSetting = LocatorUtils.getLocator(PAGENAME, "menuItem_AccountSetting");
    public final By menuItem_PersonalInfo = LocatorUtils.getLocator(PAGENAME, "menuItem_PersonalInfo");
    public final By menuItem_Redeam7Eleven = LocatorUtils.getLocator(PAGENAME, "menuItem_Redeam7Eleven");
    public final By homeButton = LocatorUtils.getLocator(PAGENAME, "home");
    public final By logOut = LocatorUtils.getLocator(PAGENAME, "logOut");
    public final By sideMenu_Home = LocatorUtils.getLocator(PAGENAME, "sideMenu_Home");
    public final By sideMenu_TopUp = LocatorUtils.getLocator(PAGENAME, "sideMenu_TopUp");
    public final By menuItem_History = LocatorUtils.getLocator(PAGENAME, "menuItem_History");
    public final By billingHistory = LocatorUtils.getLocator(PAGENAME, "billingHistory");

    public SideMenu(WebDriver driver) {
        super(driver);
    }

    public void clickHistoryMenu() {
        ElementUtils.clickObject(driver, menuItem_History);
    }

    public void clickBillingHistory() {
        ElementUtils.clickObject(driver, billingHistory);
    }

    public void clickAccountSettingMenu() {
        ElementUtils.clickObject(driver, menuItem_AccountSetting);
    }

    public void clickPersonalInfoMenu() {
        ElementUtils.clickObject(driver, menuItem_PersonalInfo);
    }

    public void clickRedeam7ElevenMenu() {
        CustomLogging.writeToReport("Clicking on Redeem 7-Eleven side menu");
        ElementUtils.clickObject(driver, menuItem_Redeam7Eleven);
        new LaunchPageActions(driver).waitForProgressBar();
    }

    public void clickTopUpMenu() {
        CustomLogging.writeToReport("Clicking on TopUp side menu");
        ElementUtils.clickObject(driver, sideMenu_TopUp);
        new LaunchPageActions(driver).waitForProgressBar();
    }


    public void clickHamBurger() {
        CustomLogging.writeToReport("Clicking on hamBurger icon");
        ElementUtils.clickObject(driver, hamburger);
        hardWait(1);
    }

    public void clickHamburgerAfterLogin() {
        ElementUtils.clickObject(driver, icon_hamburgerAfterLogin);
        hardWait(1);
    }

    public void switchToEnglishLocale() {

        clickHamBurger();
        clickSwitchEnglish();
        clickHomeButton();
    }

    public void clickSwitchEnglish() {

        if (ElementUtils.isElementDisplayed(driver, englishLanguage)) {
            CustomLogging.writeToReport("Clicking on English from side menu");
            ElementUtils.clickObject(driver, englishLanguage);
        }
    }

    public void clickHomeButton() {
        CustomLogging.writeToReport("Clicing on Home menu from side bar");
        ElementUtils.clickObject(driver, sideMenu_Home);
        hardWait(0.5);
    }

    public void clickLogOutButton() {
        CustomLogging.writeToReport("Clicking on LogOut button from side bar");
        ElementUtils.clickObject(driver, logOut);
        new LaunchPageActions(driver).waitForProgressBar();
    }
}
