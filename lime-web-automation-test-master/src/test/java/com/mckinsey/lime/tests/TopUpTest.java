package com.mckinsey.lime.tests;

import com.mckinsey.lime.pages.LaunchPageActions;
import com.mckinsey.lime.pages.PaymentFlow;
import com.mckinsey.lime.pages.SideMenu;
import com.mckinsey.lime.pages.TopUpNow;
import com.mckinsey.lime.testDataBeans.CardsDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Deprecated
public class TopUpTest extends BaseTest {

    @BeforeMethod
    public void beforeMethod() {
        launchAppToEnglish();
    }

    @AfterMethod
    public void afterMethod() {
        new PaymentFlow(getDriver()).clickClose();
        new TopUpNow(getDriver()).clickBackButton();
        new TopUpNow(getDriver()).clickBackButton();
        new TopUpNow(getDriver()).clearMSISDN();
    }

    @Test(priority = 1)
    public void testTopUp_PreLogin(ITestContext context) {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        LaunchPageActions launchPage = new LaunchPageActions(getDriver());
        TopUpNow topUpNowPage = new TopUpNow(getDriver());
        PaymentFlow paymentFlowPage = new PaymentFlow(getDriver());
        SideMenu sideMenuPage = new SideMenu(getDriver());

        sideMenuPage.clickHamBurger();
        sideMenuPage.clickTopUpMenu();


//        topUpNowPage.getPageHeader();

        topUpNowPage.enterMSISDN(TestData.getUsersData().getUser5().getPrePaidProduct().getProductId());
        topUpNowPage.clickAmount50();
        topUpNowPage.clickTopUpButton();

        paymentFlowPage.clickConfirmButton();

        CardsDataBean.Card creditCard = TestData.getCardsData().getSuccessCard();
        paymentFlowPage.enterCreditCardNumber(creditCard.getNumber());
        paymentFlowPage.enterCreditCardName(creditCard.getName());
        paymentFlowPage.enterexpiryDate(creditCard.getExpiry());
        paymentFlowPage.enterSecurityCode(creditCard.getSecurityCode());

        paymentFlowPage.clickpayButton();

        CustomAssertions.assertTrue(customClassObj, paymentFlowPage.isSuccessIconDisplayed());


    }

    @Test(priority = 2)
    public void testTopUp_More_PreLogin(ITestContext context) {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        LaunchPageActions launchPage = new LaunchPageActions(getDriver());
        TopUpNow topUpNowPage = new TopUpNow(getDriver());
        PaymentFlow paymentFlowPage = new PaymentFlow(getDriver());
        SideMenu sideMenuPage = new SideMenu(getDriver());

        sideMenuPage.clickHamBurger();
        sideMenuPage.clickTopUpMenu();


//        topUpNowPage.getPageHeader();

        topUpNowPage.enterMSISDN(TestData.getUsersData().getUser5().getPrePaidProduct().getProductId());
        topUpNowPage.clickAmountMore();
        topUpNowPage.clickTopUpButton();

        topUpNowPage.enterTopUpMoreAmount("100");
        topUpNowPage.clickTopUpMoreButton();

        paymentFlowPage.clickConfirmButton();

        CardsDataBean.Card creditCard = TestData.getCardsData().getSuccessCard();
        paymentFlowPage.enterCreditCardNumber(creditCard.getNumber());
        paymentFlowPage.enterCreditCardName(creditCard.getName());
        paymentFlowPage.enterexpiryDate(creditCard.getExpiry());
        paymentFlowPage.enterSecurityCode(creditCard.getSecurityCode());

        paymentFlowPage.clickpayButton();

        CustomAssertions.assertTrue(customClassObj, paymentFlowPage.isSuccessIconDisplayed());

    }
}
