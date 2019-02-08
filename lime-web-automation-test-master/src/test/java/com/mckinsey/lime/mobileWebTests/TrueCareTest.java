package com.mckinsey.lime.mobileWebTests;

import com.mckinsey.lime.mobilePages.LandingPage;
import com.mckinsey.lime.mobilePages.LoginPage;
import com.mckinsey.lime.mobilePages.SideMenu;
import com.mckinsey.lime.mobilePages.TrueCarePage;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.io.IOException;

@Deprecated
public class TrueCareTest extends MobileWebBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    public UsersDataBean.User indUser;

    public TrueCareTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    //    @AfterMethod
    public void afterMethod(ITestContext context, XmlTest xmlTest, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LandingPage landingPage = new LandingPage(getDriver());

            landingPage.logOutFromApp();
        }
    }

    @Test(groups = {"mobileWeb"}, description = "Change Price Plan Screen")
    public void testTrueCare(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, getDriver(), context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        LoginPage loginPage = new LoginPage(getDriver());
        SideMenu sideMenu = new SideMenu(getDriver());
        TrueCarePage trueCarePage = new TrueCarePage(getDriver());

        loginPage.loginIntoTrueApplicationFromLandingPage(indUser.getUserName(), indUser.getPassWord());

        sideMenu.navigate2TrueCare();

        trueCarePage.clickManageMyPackage();
        trueCarePage.clickBackIcon();

        trueCarePage.clickReportProblem();
        trueCarePage.clickBackIcon();

        trueCarePage.clickTrackDeviceRepair();
        trueCarePage.clickBackIcon();

        trueCarePage.clickRedeemAt7Eleven();
        trueCarePage.clickBackIcon();

        trueCarePage.clickStartChat();
        //TODO: Need to fix
//        trueCarePage.enterChatMessage("Hey");
//        trueCarePage.sendMessage();

//        new LandingPage(getDriver()).logOutFromApp();

    }
}
