package com.mckinsey.lime.tests;

import com.mckinsey.lime.pages.LoginPageActions;
import com.mckinsey.lime.pages.SideMenu;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogging;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Deprecated
public class LoginTest extends BaseTest {

    LoginPageActions loginPage;

    String username = TestData.getUsersData().getAdmin().getUserName();
    String password = TestData.getUsersData().getAdmin().getPassWord();

    @AfterMethod
    public void afterMethod() {
        new SideMenu(getDriver()).clickHamBurger();
        new SideMenu(getDriver()).clickHomeButton();
    }

    //    @Test(dependsOnMethods = "testLoginWrongPassword")
    @Test(priority = 1)
    public void testSuccessfulLogin(ITestContext context) {
        loginPage = new LoginPageActions(getDriver());
        navigateToLoginPage();

        loginPage.loginIntoTrueApplication(username, password);
        CustomLogging.writeInfoLogToReport("User logged in with User name: " + username + "\n password: " + password + " successfully");
//		loginPage.verifySuccessfulLogin();
    }

    //    @Test(priority = 0)
    public void testLoginWithOutFillingUserNamePwd(ITestContext context) {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        loginPage = new LoginPageActions(getDriver());

        navigateToLoginPage();
        loginPage.clickLogInButton();

        CustomAssertions.assertText(customClassObj, loginPage.getAccountMissingError(), "account field is required.", "Verified error message as ");
        CustomAssertions.assertText(customClassObj, loginPage.getPasswordMissingEoor(), "Password field is required.", "Verified error message as ");
        loginPage.clickCancelButton();
    }

    //    @Test(dependsOnMethods = "testLoginWithOutFillingUserNamePwd")
//    @Test(priority = 0)
    public void testLoginWrongPassword(ITestContext context) {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);
        loginPage = new LoginPageActions(getDriver());

        navigateToLoginPage();

        loginPage.enterUserName(username);
//        loginPage.hideKeyboard();
        loginPage.enterPassword("123");

        loginPage.clickLogInButton();

        CustomAssertions.assertText(customClassObj, loginPage.getWrongPasswordError(), "Invalid account or password.", "Verified error message as ");
        loginPage.clickCancelButton();

    }
}