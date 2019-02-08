package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.RequestTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class RequestTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] requestTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new RequestTest(indUser)};
    }
}
