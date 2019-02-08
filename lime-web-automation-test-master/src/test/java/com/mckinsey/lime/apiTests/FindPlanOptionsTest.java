package com.mckinsey.lime.apiTests;

import com.mckinsey.lime.apiTestBeans.CustomResponse;
import com.mckinsey.lime.apiTestBeans.FindPlanOptionsResponse;
import com.mckinsey.lime.apiUtils.ApiRequests;
import com.mckinsey.lime.testDataBeans.ConfigDataBean;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testUtils.CustomAssertions;
import com.mckinsey.lime.utils.CustomClass;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;

public class FindPlanOptionsTest extends ApiBaseTest {

    private final ConfigDataBean configData = TestData.getConfigData();


    @Test(groups = {"apiFlowTest"}, description = "Test Toppings API response code")
    public void testToppingsResponseCode(ITestContext context) throws IOException {
        CustomClass customClassObj = new CustomClass(new Object() {
        }, null, context, this);

        CustomResponse<FindPlanOptionsResponse> customResponse_Products = ApiRequests.sendFindPlanOptionsAPI(customClassObj);

        FindPlanOptionsResponse findPlanOptionsResponse = customResponse_Products.getResponseObjList().get(0);
//        findPlanOptionsResponse.forEach((key, value) -> {
//            System.out.println(key);
//            System.out.println(value);
//        });
        CustomAssertions.assertResponseCodeAs200(customResponse_Products.getResponseCode(), "Verified the PRODUCTS API response code as ");

    }

}
