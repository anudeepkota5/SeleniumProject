package com.mckinsey.lime.tests;

import com.mckinsey.lime.apiTestBeans.BillDetailsResponse;
import com.mckinsey.lime.pages.BillsAndUsagePage;
import com.mckinsey.lime.pages.LoginPageActions;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;

@Deprecated
public class BillAndPayTest extends BaseTest {

    private LoginPageActions loginPage;

    @Test
    public void testBillAndPay_smartPlanLabels(ITestContext context) throws Exception {
        loginPage = new LoginPageActions(getDriver());

        UsersDataBean.User currentUser = TestData.getUsersData().getUser3();
        String username = currentUser.getUserName();
        String password = currentUser.getPassWord();

        navigateToLoginPage();

        loginPage.loginIntoTrueApplication(username, password);

        testBillsAndUsage(context, currentUser, false);

    }

    @Test
    public void testBillAndPay_sharePlanLabels(ITestContext context) throws Exception {
        loginPage = new LoginPageActions(getDriver());

        UsersDataBean.User currentUser = TestData.getUsersData().getUser3();
        String username = currentUser.getUserName();
        String password = currentUser.getPassWord();

        navigateToLoginPage();

        loginPage.loginIntoTrueApplication(username, password);

        testBillsAndUsage(context, currentUser, true);

    }

    @Test
    public void clickBillAndPay() throws Exception {
        loginPage = new LoginPageActions(getDriver());

        UsersDataBean.User currentUser = TestData.getUsersData().getUser3();
        String username = currentUser.getUserName();
        String password = currentUser.getPassWord();

        navigateToLoginPage();

        loginPage.loginIntoTrueApplication(username, password);

        clickBillsAndUsageTabs(currentUser, false);

    }

    private void testBillsAndUsage(ITestContext context, UsersDataBean.User currentUser, boolean isSharePlan) throws IOException, JSONException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);
        BillsAndUsagePage billAndPayPage = new BillsAndUsagePage(getDriver());

//        TODO: Need to update the logic
//        ProductsResponse productsResponse = ApiResponses.getResponse_Products(currentUser);

        CustomAssertions.assertText(customClassObj, billAndPayPage.getBillingSummarySectionHeader(), "Billing Summary", "Verified section header as");
        billAndPayPage.clickExpandMibileBillDetailsIcon();
        CustomAssertions.assertText(customClassObj, billAndPayPage.getCurrentPlanNameText(), "4G Super Smart 899", "Verified current plan name as");
        CustomAssertions.assertText(customClassObj, billAndPayPage.getExtraPackagesText(), "(4 Extra Packages)", "Verified extra package text as");
        CustomAssertions.assertText(customClassObj, billAndPayPage.getCurrentUsageTabText(), "Current Usage \uE901", "Verified current usage tab text as");
        CustomAssertions.assertText(customClassObj, billAndPayPage.getBillDetailsTabext(), "Bill Details", "Verified bill details tab text as");
//        CustomLogging.witeInfo(billAndPayPage.getBillDetails_ViewPaymentHistoryLinkText());
        Assert.assertEquals(billAndPayPage.getCurrentUsage_talkText(), "Talk 50 from 80 min");
        Assert.assertEquals(billAndPayPage.getCurrentUsage_dataText(), "Data 20 from 100 GB");


//        ElementUtils.shortSwipeUp(getDriver());
        Assert.assertEquals(billAndPayPage.getCurrentUsage_buyExtraPackPopuUpText(), "Buy extra price packs!");
        Assert.assertEquals(billAndPayPage.getCurrentUsage_seeOtherExtraPackagesText(), "See other extra packages");
        if (isSharePlan)
            Assert.assertEquals(billAndPayPage.getCurrentUsage_sharedNumbersLinkText(), "Shared numbers 2 \uE919");


        billAndPayPage.clickBillDetailsTab();

        //TODO: Need to update the logic
//        String accountId = productsResponse.getProducts().getTmhPostpaid().get(0).getAccountId();
//        BillDetailsResponse invoicesResponse = ApiResponses.getResponse_Invoices(accountId);
        BillDetailsResponse invoicesResponse = null;

//        Assert.assertEquals(billAndPayPage.getBillDetails_pendingBill1Text(), "฿" + invoicesResponse.getBillDetailsByAccoutID(accountId).getUnpaidAmount());

        billAndPayPage.clickSearch_CurrentPlanDetailsIcon();

        Assert.assertEquals(billAndPayPage.getPackageDetailsTabText(), "Package Detail \uE901");

        Assert.assertEquals(billAndPayPage.getPackageDetails_voiceLabelText(), "Voice");
        Assert.assertEquals(billAndPayPage.getPackageDetails_dataLabelText(), "Data");
        Assert.assertEquals(billAndPayPage.getPackageDetails_wifiLabelText(), "Free WIFI");
        Assert.assertEquals(billAndPayPage.getPackageDetails_smsMmsLabelText(), "SMS/MMS");
        billAndPayPage.clickExtraPackageTab();
        Assert.assertEquals(billAndPayPage.getExtraPackage_activeSectionText(), "Active");
        Assert.assertEquals(billAndPayPage.getExtraPackage_inActiveSectionText(), "Inactive");
    }

    private void clickBillsAndUsageTabs(UsersDataBean.User currentUser, boolean isSharePlan) throws IOException, JSONException {
        BillsAndUsagePage billAndPayPage = new BillsAndUsagePage(getDriver());


//        billAndPayPage.clickExpandMibileBillDetailsIcon();

        billAndPayPage.clickBillDetailsTab();


//        billAndPayPage.clickSearch_CurrentPlanDetailsIcon();
    }
}
