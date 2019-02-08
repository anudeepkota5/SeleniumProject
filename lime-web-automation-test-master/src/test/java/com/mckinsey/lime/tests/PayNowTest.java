package com.mckinsey.lime.tests;

import com.mckinsey.lime.pages.LaunchPageActions;
import com.mckinsey.lime.pages.PayNow;
import com.mckinsey.lime.pages.PaymentFlow;
import com.mckinsey.lime.pages.TopUpNow;
import com.mckinsey.lime.testDataBeans.CardsDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


@Deprecated
public class PayNowTest extends BaseTest {


    @AfterMethod
    public void afterMethod() {
        new PaymentFlow(getDriver()).clickClose();
        new TopUpNow(getDriver()).clickBackButton();
        new LaunchPageActions(getDriver()).clearMsisdnorServiceNumber();
    }

    @Test
    public void testPayNowWithoutOTP(ITestContext context) {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        LaunchPageActions launchPage = new LaunchPageActions(getDriver());
        PayNow payNowPage = new PayNow(getDriver());
        PaymentFlow paymentFlowPage = new PaymentFlow(getDriver());

        launchAppToEnglish();

        launchPage.enterMsisdnorServiceNumber(TestData.getUsersData().getUser3().getTmhPostPaidProduct().getProductId());
        launchPage.clickPayNowButton();

//        CustomAssertions.assertText(context, payNowPage.getPageHeader(), "Pay Now");
        CustomAssertions.assertTrue(customClassObj, payNowPage.isBackButtonDisplayed());
        CustomAssertions.assertText(String.valueOf(payNowPage.numberOfProducts()), "1");
        CustomAssertions.assertTrue(customClassObj, payNowPage.isProductCardDisplayed());
        CustomAssertions.assertText(payNowPage.getProductServiceNumber(), TestData.getUsersData().getUser3().getTmhPostPaidProduct().getProductId());

        payNowPage.clickPayButton();

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
