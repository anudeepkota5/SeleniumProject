package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.PaymentHistoryTest;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class PaymentHistoryTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] paymentHistoryInstances(UsersDataBean.User indUser) {
        return new Object[]{new PaymentHistoryTest(indUser)};
    }
}
