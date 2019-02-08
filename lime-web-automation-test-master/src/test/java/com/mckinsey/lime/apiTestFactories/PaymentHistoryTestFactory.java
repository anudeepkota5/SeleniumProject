package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.PaymentHistoryTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class PaymentHistoryTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] paymentHistoryTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new PaymentHistoryTest(indUser)};
    }
}
