package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.TopUpValidityTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class TopUpValidityTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] topUpValidityTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new TopUpValidityTest(indUser)};
    }
}
