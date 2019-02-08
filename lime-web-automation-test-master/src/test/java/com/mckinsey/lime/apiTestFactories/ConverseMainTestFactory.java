package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.ConverseMainTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class ConverseMainTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] converseMainTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new ConverseMainTest(indUser)};
    }
}
