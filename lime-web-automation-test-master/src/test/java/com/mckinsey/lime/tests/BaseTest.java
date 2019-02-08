package com.mckinsey.lime.tests;

import com.mckinsey.lime.apiUtils.ApiCallDetails;
import com.mckinsey.lime.pages.LaunchPageActions;
import com.mckinsey.lime.pages.SideMenu;
import com.mckinsey.lime.utils.CommonEnums;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

class BaseTest extends TestSessionInitiator {

    public String testData = "";
    public List<ApiCallDetails> apiCallDetails = new ArrayList<>();

    void navigateToLoginPage() {

        launchAppToEnglish();
        LaunchPageActions launchPage = new LaunchPageActions(getDriver());

        launchPage.navigateToLoginPage();
        CustomLogging.writeInfoLogToReport("Navigated to login page");
    }

    void launchApp() {

        LaunchPageActions launchPage = new LaunchPageActions(getDriver());

        launchPage.waitForProgressBar();

        //TODO: Need to delete this logic by adding capability to accept all Permissions
        if (Utilities.getDeviceOS() == CommonEnums.DeviceType.IOS) {
            launchPage.acceptNotifications();
            CustomLogging.writeInfoLogToReport("Notification accepted");
        }
    }

    void launchAppToEnglish() {
        launchApp();

        SideMenu sideMenuPage = new SideMenu(getDriver());

        sideMenuPage.switchToEnglishLocale();
        CustomLogging.writeInfoLogToReport("switched to English locale");
    }
}
