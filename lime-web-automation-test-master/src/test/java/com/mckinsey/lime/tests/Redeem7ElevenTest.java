package com.mckinsey.lime.tests;

import com.mckinsey.lime.pages.LaunchPageActions;
import com.mckinsey.lime.pages.LoginPageActions;
import com.mckinsey.lime.pages.Redeem7Eleven;
import com.mckinsey.lime.pages.SideMenu;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

@Deprecated
public class Redeem7ElevenTest extends BaseTest {

    UsersDataBean.User user = TestData.getUsersData().getAdmin();

    @AfterTest
    public void afterTest() {
        new SideMenu(getDriver()).clickHamBurger();
        new SideMenu(getDriver()).clickHomeButton();
    }

    @Test
    public void testPreLogin(ITestContext context) throws InterruptedException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        launchAppToEnglish();

        LaunchPageActions launchPage = new LaunchPageActions(getDriver());
        SideMenu sideMenuPageObj = new SideMenu(getDriver());
        Redeem7Eleven redeem7ElevenPageObj = new Redeem7Eleven(getDriver());

        sideMenuPageObj.clickHamBurger();
        sideMenuPageObj.clickRedeam7ElevenMenu();

        Thread.sleep(2000);
        CustomAssertions.assertText(redeem7ElevenPageObj.getPageHeader(), TestData.getRedeam7ElevenData().getPageHeader());
        CustomAssertions.assertTrue(customClassObj, redeem7ElevenPageObj.isDealsTabDisplayed());
        CustomAssertions.assertTrue(customClassObj, redeem7ElevenPageObj.isMyCouponsTabDisplayed());
        CustomAssertions.assertText(redeem7ElevenPageObj.getLoginMessage_Deals(), TestData.getRedeam7ElevenData().getLoginMessage_Deals());
        CustomAssertions.assertTrue(customClassObj, redeem7ElevenPageObj.isLoginButton_DealsDisplayed());
        //Need to update the locator
//        CustomAssertions.assertText(context, redeem7ElevenPageObj.getAvailableCouponsSectionHeader(), TestData.getRedeam7ElevenData().getAvailableCoupounsSectionHeader());

        CustomAssertions.assertTrue(customClassObj, redeem7ElevenPageObj.isFirstAvailableCouponCardDisplayed());
//        CustomAssertions.assertText(context, redeem7ElevenPageObj.getFirstAvailableCardName(), TestData.getRedeam7ElevenData().getFirstAvailableCouponName());
//        CustomAssertions.assertText(context, redeem7ElevenPageObj.getFirstAvailableCardDescription(), TestData.getRedeam7ElevenData().getFirstAvailableCouponDescription());
//        CustomAssertions.assertText(context, redeem7ElevenPageObj.getFirstAvailableCardAmount(), TestData.getRedeam7ElevenData().getFirstAvailableCouponAmount());
//        CustomAssertions.assertText(context, redeem7ElevenPageObj.getFirstAvailableCardCondition(), TestData.getRedeam7ElevenData().getFirstAvailableCouponCondition());

        redeem7ElevenPageObj.clickMyCouponsTab();
        CustomAssertions.assertText(redeem7ElevenPageObj.getLoginMessage_MyCoupons(), TestData.getRedeam7ElevenData().getLoginMessage_MyCoupons());
        CustomAssertions.assertTrue(customClassObj, redeem7ElevenPageObj.isPreLoginButton_MyCouponsDisplayed());

        redeem7ElevenPageObj.clickDealsTab();
        redeem7ElevenPageObj.clickLoginButton_Deals();

        LoginPageActions loginPage = new LoginPageActions(getDriver());

        loginPage.loginIntoTrueApplication(user.getUserName(), user.getPassWord());

        CustomAssertions.assertText(redeem7ElevenPageObj.getTruePoints(), user.getTruePoints());

        sideMenuPageObj.clickHamBurger();
        sideMenuPageObj.clickLogOutButton();

        sideMenuPageObj.clickHamBurger();
        sideMenuPageObj.clickRedeam7ElevenMenu();
        redeem7ElevenPageObj.clickMyCouponsTab();

        redeem7ElevenPageObj.clickPreLoginButton_MyCoupons();

        loginPage.loginIntoTrueApplication(user.getUserName(), user.getPassWord());
        //Need to write logic for verifying the MyCoupon Cell


    }
}