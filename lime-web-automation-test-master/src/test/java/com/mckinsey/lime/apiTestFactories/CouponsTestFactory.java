package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.CouponsTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class CouponsTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] couponsTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new CouponsTest(indUser)};
    }
}
