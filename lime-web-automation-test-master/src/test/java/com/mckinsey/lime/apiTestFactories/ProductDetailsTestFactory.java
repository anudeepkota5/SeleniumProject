package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.ProductDetailsTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class ProductDetailsTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] allocationStatusTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new ProductDetailsTest(indUser)};
    }
}
