package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.AllocationStatusTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class AllocationStatusTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] allocationStatusTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new AllocationStatusTest(indUser)};
    }
}
