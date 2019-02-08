package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.TypeTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class TypeTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] typeTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new TypeTest(indUser)};
    }
}
