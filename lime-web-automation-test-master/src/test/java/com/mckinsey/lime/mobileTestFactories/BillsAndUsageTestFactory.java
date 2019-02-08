package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.BillsAndUsageTest;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class BillsAndUsageTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] billsAndUsageInstances(UsersDataBean.User indUser) {
        return new Object[]{new BillsAndUsageTest(indUser)};
    }

}
