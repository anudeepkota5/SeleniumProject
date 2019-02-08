package com.mckinsey.lime.mobileWebTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.ProductsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.apiUtils.Utilities;
import com.mckinsey.lime.mobilePages.*;
import com.mckinsey.lime.testDataBeans.CardsDataBean;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Deprecated
public class PayForOthersTest extends MobileWebBaseTest {

    private final UsersDataBean usersData = TestData.getApiUsersData();


    //    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        SideMenu sideMenu = new SideMenu(getDriver());

        sideMenu.navigate2Home();
    }

    @Test(groups = {"mobileWeb"}, description = "PostLogin: Pay for Others Test")
    public void testPayForOthers(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);

        LandingPage landingPage = new LandingPage(getDriver());
        PreLoginPayNowPage payNowPage = new PreLoginPayNowPage(getDriver());
        PaymentsPopUp paymentPopup = new PaymentsPopUp(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        SideMenu sideMenu = new SideMenu(getDriver());
        PayForOthersPage payForOthersPage = new PayForOthersPage(getDriver());

        ConfigDataBean configData = TestData.getConfigData();

        UsersDataBean.User indUser = usersData.getUsers().get(0);

        loginPage.loginIntoTrueApplicationFromLandingPage(indUser.getUserName(), indUser.getPassWord());


        sideMenu.openPayForOthers();

        ProductsResponse.IndividualProduct anyTmhPostPaidNumber = Utilities.getAnyTmhPostPaidNumberWithBalance(customClassObj, false);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, anyTmhPostPaidNumber.getProductId());
        CustomLogging.writeTestDataToReport(anyTmhPostPaidNumber.getProductId());

        payForOthersPage.enterServiceNumber(anyTmhPostPaidNumber.getProductId());
        payForOthersPage.clickNextButton();

        if (payNowPage.isPayButtonEnabled())
            payNowPage.clickPayNow();

        CustomResponse<HashMap> customResponse_AllocationStatus = ApiRequests.sendAllocationStatusAPI_preLogin(customClassObj, Collections.singletonList(anyTmhPostPaidNumber.getAccountId()), configData.getApi_channel(), configData.getApi_platform());
        AtomicBoolean isPaymentAllowed = new AtomicBoolean(true);
        customResponse_AllocationStatus.getResponseObj().values().forEach(item -> {
            if (!item.toString().equalsIgnoreCase("UNBLOCKED"))
                isPaymentAllowed.set(false);
        });
        if (isPaymentAllowed.get()) {
            paymentPopup.clickContinue();

            CardsDataBean.Card successCreditCardData = TestData.getCardsData().getSuccessCard();
            paymentPopup.enterCardNumber(successCreditCardData.getNumber());
            paymentPopup.enterCardHolderName(successCreditCardData.getName());
            paymentPopup.enterExpiryDate(successCreditCardData.getExpiry());
            paymentPopup.enterCVV(successCreditCardData.getSecurityCode());
            paymentPopup.clickPay();
        } else {
            //Check for popup
            CustomAssertions.assertTrue(customClassObj, paymentPopup.isPaymentProgressPopupDisplayed(),
                    "Payment for the selected product is in Pending state: " + anyTmhPostPaidNumber.getProductId());
            paymentPopup.closePaymentProgressPopup();
        }

    }

}