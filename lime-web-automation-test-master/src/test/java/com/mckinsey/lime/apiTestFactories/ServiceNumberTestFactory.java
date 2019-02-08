package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.ServiceNumberTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class ServiceNumberTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] serviceNumberTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new ServiceNumberTest(indUser)};
    }
}
