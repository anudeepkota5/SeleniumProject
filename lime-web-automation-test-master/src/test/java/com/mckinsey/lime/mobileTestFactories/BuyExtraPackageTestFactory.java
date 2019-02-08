package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.BuyExtraPackageTest;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class BuyExtraPackageTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] buyExtraPackageInstances(UsersDataBean.User indUser) {
        return new Object[]{new BuyExtraPackageTest(indUser)};
    }

}
