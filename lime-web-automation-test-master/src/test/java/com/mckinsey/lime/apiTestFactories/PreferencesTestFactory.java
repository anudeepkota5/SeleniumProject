package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.PreferencesTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class PreferencesTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] preferencesTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new PreferencesTest(indUser)};
    }
}
