package com.mckinsey.lime.tests;

import com.mckinsey.lime.pages.LaunchPageActions;
import com.mckinsey.lime.pages.LoginPageActions;
import com.mckinsey.lime.pages.SideMenu;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.testUtils.CustomAssertions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Deprecated
public class PersonalInfo extends BaseTest {

    LoginPageActions loginPage;
    LaunchPageActions launchPage;
    SideMenu sideMenu;
    com.mckinsey.lime.pages.PersonalInfo personalInfoPage;

    @AfterMethod
    public void afterMethod() {
        new SideMenu(getDriver()).clickHamBurger();
        new SideMenu(getDriver()).clickLogOutButton();
    }

    @Test
    public void testPersonalInfo(ITestContext context) {
        loginPage = new LoginPageActions(getDriver());
        launchPage = new LaunchPageActions(getDriver());
        sideMenu = new SideMenu(getDriver());
        personalInfoPage = new com.mckinsey.lime.pages.PersonalInfo(getDriver());

        UsersDataBean.User currentUser = TestData.getUsersData().getAdmin();
        String username = currentUser.getUserName();
        String password = currentUser.getPassWord();

        navigateToLoginPage();

        loginPage.loginIntoTrueApplication(username, password);

        sideMenu.clickHamBurger();
        sideMenu.clickAccountSettingMenu();
        sideMenu.clickPersonalInfoMenu();

        launchPage.waitForProgressBar();
        System.out.println(personalInfoPage.getNameValue());
        CustomAssertions.assertText(currentUser.getDisplayName(), personalInfoPage.getDisplayNameValue());
        CustomAssertions.assertText(currentUser.getName(), personalInfoPage.getNameValue().trim());
        CustomAssertions.assertText(personalInfoPage.getPrivilegeValue(), currentUser.getPrivilege());

        updateDisplayName("Test");
        //revert the updated Display name back to original
        updateDisplayName(currentUser.getDisplayName());

    }

    private void updateDisplayName(String newDisplayName) {
        personalInfoPage.clickEditDisplayNameLink();

        personalInfoPage.enterNewDisplayName(newDisplayName);
        personalInfoPage.clickSaveDisplayName();
        launchPage.waitForProgressBar();
        CustomAssertions.assertText(newDisplayName, personalInfoPage.getDisplayNameValue());
    }

    public void getElementLocations() {
        loginPage = new LoginPageActions(getDriver());
        launchPage = new LaunchPageActions(getDriver());
        sideMenu = new SideMenu(getDriver());
        personalInfoPage = new com.mckinsey.lime.pages.PersonalInfo(getDriver());

        UsersDataBean.User currentUser = TestData.getUsersData().getAdmin();
        String username = currentUser.getUserName();
        String password = currentUser.getPassWord();

        navigateToLoginPage();

        loginPage.loginIntoTrueApplication(username, password);

//        launchPage.clickHamBurger();
        sideMenu.clickAccountSettingMenu();
        sideMenu.clickPersonalInfoMenu();

        launchPage.waitForProgressBar();
        System.out.println(personalInfoPage.getNameValue());
        CustomAssertions.assertText(currentUser.getDisplayName(), personalInfoPage.getDisplayNameValue());
        CustomAssertions.assertText(currentUser.getName(), personalInfoPage.getNameValue().trim());
        CustomAssertions.assertText(currentUser.getPrivilege(), personalInfoPage.getPrivilegeValue());

    }
}
