package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.BillDetailsTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class BillDetailsTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] billDetailsTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new BillDetailsTest(indUser)};
    }
}
