package com.mckinsey.lime.mobileWebTests;

import com.mckinsey.lime.mobilePages.LandingPage;
import com.mckinsey.lime.mobilePages.LoginPage;
import com.mckinsey.lime.mobilePages.SideMenu;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogging;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

@Deprecated
public class LoginTest extends MobileWebBaseTest {

    final String username = TestData.getUsersData().getUser1().getUserName();
    final String password = TestData.getUsersData().getUser1().getPassWord();

    //    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        new SideMenu(getDriver()).clickHamBurger();
        new SideMenu(getDriver()).clickHomeButton();
    }

    //    @Test(dependsOnMethods = "testLoginWrongPassword")
//    @Test(priority = 1, groups = {"mobileWeb"})
    public void testSuccessfulLogin(ITestContext context) {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.loginIntoTrueApplicationFromLandingPage(username, password);
        CustomLogging.writeInfoLogToReport("User logged in with User name: " + username + "\n password: " + password + " successfully");
//		loginPage.verifySuccessfulLogin();
    }

    @Test(priority = 0, groups = {"mobileWeb"}, description = "Login WithOut Filling UserName Pwd")
    public void testLoginWithOutFillingUserNamePwd(ITestContext context) {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);
        CustomLogging.witeInfo(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());

        new LandingPage(getDriver()).navigateToLoginPage();
        loginPage.clickLogInButton();
        loginPage.switchToLogInFrame();
        CustomAssertions.assertText(customClassObj, loginPage.getErrorMessageForUserName(), "Please enter your TrueID as username.", "Verified error message as ");
        CustomAssertions.assertText(customClassObj, loginPage.getErrorMessageForPassword(), "Please create a password.", "Verified error message as ");
        loginPage.closeLoginPopup();
    }

    //    @Test(dependsOnMethods = "testLoginWithOutFillingUserNamePwd")
    @Test(priority = 0, groups = {"mobileWeb"}, description = "Login with Wrong Password")
    public void testLoginWrongPassword(ITestContext context) {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);
        LoginPage loginPage = new LoginPage(getDriver());

        new LandingPage(getDriver()).navigateToLoginPage();

        loginPage.enterUserName(username);
        loginPage.hideKeyboard();
        loginPage.enterPassword("123");

        loginPage.clickLogInButton();
        loginPage.switchToLogInFrame();

        CustomAssertions.assertText(customClassObj, loginPage.getErrorMessageForLoginPopup(), "Please enter the correct username or password and try again.", "Verified error message as ");
        loginPage.closeLoginPopup();

    }
}