package com.mckinsey.lime.apiTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.TranslationsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;

@Deprecated
public class TranslationsTest extends ApiBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();

    @Test(groups = {"apiFlowTest", "contentAPI"}, description = "Test Translations API response code")
    public void testTranslationsResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);
        CustomResponse<TranslationsResponse> customResponse_Translations = ApiRequests.sendTranslationsAPI(customClassObj, configData.getApi_channel(), configData.getApi_platform());

        CustomAssertions.assertResponseCodeAs200(customResponse_Translations.getResponseCode(), "Verified the PRODUCTS API response code of ");

    }
}
