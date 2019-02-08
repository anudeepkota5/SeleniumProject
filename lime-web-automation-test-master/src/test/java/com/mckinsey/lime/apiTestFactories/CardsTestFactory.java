package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.CardsTest;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.Factory;

@Deprecated
public class CardsTestFactory {

    @Factory(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public Object[] cardsTestInstances(UsersDataBean.User indUser) {
        return new Object[]{new CardsTest(indUser)};
    }
}
