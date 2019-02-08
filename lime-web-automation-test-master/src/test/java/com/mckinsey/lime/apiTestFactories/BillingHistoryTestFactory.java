package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.BillingHistoryTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class BillingHistoryTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] billingHistoryTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new BillingHistoryTest(indUser)};
    }
}
