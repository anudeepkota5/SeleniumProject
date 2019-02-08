package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.TrueCareTest;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class TrueCareTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] trueCareInstances(UsersDataBean.User indUser) {
        return new Object[]{new TrueCareTest(indUser)};
    }
}
