package com.mckinsey.lime.apiTests;

import com.mckinsey.lime.apiTestBeans.CouponsResponse;
import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import com.mckinsey.lime.utils.CustomLogging;
import com.mckinsey.lime.utils.TestNgCustomStrings;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;

@Deprecated
public class CouponsTest extends ApiBaseTest{

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public CouponsTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    @Test(groups = {"apiFlowTest", "contentAPI"}, description = "Test Coupons API response code")
    public void testCouponsResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<CouponsResponse> customResponse_Coupons = ApiRequests.sendCouponsAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Coupons.getResponseCode(), "Verified the COUPONS API response code of : " + indUser.getUserName());

    }

    @Test(groups = {"apiFlowTest", "contentAPI"}, description = "PreLogin: Test Coupons API response code")
    public void preLogin_testCouponsResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        CustomResponse<CouponsResponse> customResponse_Coupons = ApiRequests.preLogin_SendCouponsAPI(customClassObj, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Coupons.getResponseCode(), "Verified the COUPONS API response code of : ");
    }

}