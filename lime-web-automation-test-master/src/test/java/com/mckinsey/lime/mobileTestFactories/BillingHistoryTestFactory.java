package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.BillingHistoryTest;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class BillingHistoryTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] billingHistoryInstances(UsersDataBean.User indUser) {
        return new Object[]{new BillingHistoryTest(indUser)};
    }


}
