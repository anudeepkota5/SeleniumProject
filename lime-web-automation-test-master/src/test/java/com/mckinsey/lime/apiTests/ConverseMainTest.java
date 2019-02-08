package com.mckinsey.lime.apiTests;

import com.mckinsey.lime.apiTestBeans.ConverseMainResponse;
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
public class ConverseMainTest extends ApiBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();
    private UsersDataBean.User indUser;

    public ConverseMainTest(UsersDataBean.User indUser) {
        this.indUser = indUser;
    }

    public ConverseMainTest() {
    }

    @Test(groups = {"apiFlowTest", "supportAPI"}, description = "Test Converse API response code")
    public void testConverseMainResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        context.setAttribute(TestNgCustomStrings.TEST_DATA, indUser.getUserName());
        CustomLogging.writeTestDataToReport(indUser.getUserName());

        CustomResponse<ConverseMainResponse> customResponse_ConverseMain = ApiRequests.sendConverseMainAPI(customClassObj, indUser, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_ConverseMain.getResponseCode(), "Verified the CONVERSE main API response code of : " + indUser.getUserName());

    }

    @Test(groups = {"apiFlowTest", "supportAPI"}, description = "Test Converse API response code")
    public void preLogin_testConverseMainResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);

        CustomResponse<ConverseMainResponse> customResponse_ConvergenceMain = ApiRequests.preLogin_SendConverseMainAPI(customClassObj, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_ConvergenceMain.getResponseCode(), "Verified the CONVERSE main API response code of : ");

    }

}