package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.ChangePricePlanTest;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class ChangePricePlanTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] changePricePlanInstances(UsersDataBean.User indUser) {
        return new Object[]{new ChangePricePlanTest(indUser)};
    }
}
