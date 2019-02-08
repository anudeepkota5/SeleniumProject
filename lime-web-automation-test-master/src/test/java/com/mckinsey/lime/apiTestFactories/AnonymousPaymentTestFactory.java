package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.paymentAPIs.AnonymousPaymentTest;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class AnonymousPaymentTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] anonymousPaymentTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new AnonymousPaymentTest(indUser)};
    }
}
