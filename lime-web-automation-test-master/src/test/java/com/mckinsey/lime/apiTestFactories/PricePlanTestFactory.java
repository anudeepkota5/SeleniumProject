package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.PricePlanTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class PricePlanTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] pricePlanTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new PricePlanTest(indUser)};
    }
}
