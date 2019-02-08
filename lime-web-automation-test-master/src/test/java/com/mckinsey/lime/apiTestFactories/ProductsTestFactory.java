package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.ProductsTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class ProductsTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] productsTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new ProductsTest(indUser)};
    }
}
