package com.mckinsey.lime.tests;

import com.mckinsey.lime.pages.BillingHistoryPageActions;
import com.mckinsey.lime.pages.LaunchPageActions;
import com.mckinsey.lime.pages.LoginPageActions;
import com.mckinsey.lime.pages.SideMenu;
import com.mckinsey.lime.testDataBeans.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

@Deprecated
public class BillingHistoryTest extends BaseTest {

    String username = TestData.getUsersData().getAdmin().getUserName();
    String password = TestData.getUsersData().getAdmin().getPassWord();

    String usernameSmartChoice = "0813511783";
    String passwordSmartChoice = "11111111";

    LoginPageActions loginPage;
    LaunchPageActions launchPage;
    SideMenu sideMenu;
    BillingHistoryPageActions billingHistory;

    @Test
    public void testBillingHistoryWhenNoPostPaidAccountsExist() {
        loginPage = new LoginPageActions(getDriver());
        launchPage = new LaunchPageActions(getDriver());
        sideMenu = new SideMenu(getDriver());
        billingHistory = new BillingHistoryPageActions(getDriver());

        navigateToLoginPage();
        loginPage.loginIntoTrueApplication(username, password);
        sideMenu.clickHamburgerAfterLogin();
        sideMenu.clickHistoryMenu();
        sideMenu.clickBillingHistory();
        Assert.assertEquals(billingHistory.getNoPostPaidAccountsErrorMessage(), "No postpaid accounts");
        sideMenu.clickHamburgerAfterLogin();
        sideMenu.clickLogOutButton();
    }

    @Test(dependsOnMethods = "testBillingHistoryWhenNoPostPaidAccountsExist")
    public void testBillingHistorySmartChoice() {
        loginPage = new LoginPageActions(getDriver());
        launchPage = new LaunchPageActions(getDriver());
        sideMenu = new SideMenu(getDriver());
        billingHistory = new BillingHistoryPageActions(getDriver());

        navigateToLoginPage();
        loginPage.loginIntoTrueApplication(usernameSmartChoice, passwordSmartChoice);
        sideMenu.clickHamburgerAfterLogin();
        sideMenu.clickHistoryMenu();
        sideMenu.clickBillingHistory();
        billingHistory.selectSmartChoiceProductFromBillingHistoryDropdown();
        System.out.println(billingHistory.dropdownSelectedBillingHistoryProduct());
        Assert.assertEquals(billingHistory.dropdownSelectedBillingHistoryProduct(), "4PValue True Smart Choice (Postpaid) ");
    }

}