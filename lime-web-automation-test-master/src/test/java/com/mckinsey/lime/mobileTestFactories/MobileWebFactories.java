package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.*;
import com.mckinsey.lime.testDataBeans.TestData;
import com.mckinsey.lime.testDataBeans.UsersDataBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import java.util.List;

@Deprecated
public class MobileWebFactories {
    private static final UsersDataBean apiUsersData = TestData.getApiUsersData();

    @DataProvider(name = "dp", parallel = true)
    public static Object[][] dataProvider() {
        List<UsersDataBean.User> users = apiUsersData.getUsers();
        Object[][] dataArray = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            Object[] test = {users.get(i)};
            dataArray[i] = test;
        }
        return dataArray;
    }

    @Factory
    public Object[] preLogin_PayForOthersInstances() {
        return new Object[]{new PreLogin_PayForOthersTest()};
    }

    @Factory
    public Object[] preLogin_PayNowInstances() {
        return new Object[]{new PreLogin_PayNowTest()};
    }

    @Factory
    public Object[] preLogin_TopUpForOthersInstances() {
        return new Object[]{new PreLogin_TopUpForOthersTest()};
    }

    @Factory
    public Object[] loginInstances() {
        return new Object[]{new LoginTest()};
    }

    @Factory(dataProvider = "dp")
    public Object[] billsAndUsageInstances(UsersDataBean.User indUser) {
        return new Object[]{new BillsAndUsageTest(indUser)};
    }

    @Factory
    public Object[] payForOthersTestInstances() {
        return new Object[]{new PayForOthersTest()};
    }

    @Factory
    public Object[] topUpForOthersInstances() {
        return new Object[]{new TopUpForOthersTest()};
    }

    @Factory(dataProvider = "dp")
    public Object[] buyExtraPackageInstances(UsersDataBean.User indUser) {
        return new Object[]{new BuyExtraPackageTest(indUser)};
    }

    @Factory(dataProvider = "dp")
    public Object[] changePricePlanInstances(UsersDataBean.User indUser) {
        return new Object[]{new ChangePricePlanTest(indUser)};
    }

    @Factory(dataProvider = "dp")
    public Object[] paymentHistoryInstances(UsersDataBean.User indUser) {
        return new Object[]{new PaymentHistoryTest(indUser)};
    }

    @Factory(dataProvider = "dp")
    public Object[] billingHistoryInstances(UsersDataBean.User indUser) {
        return new Object[]{new BillingHistoryTest(indUser)};
    }

    @Factory(dataProvider = "dp")
    public Object[] trueCareInstances(UsersDataBean.User indUser) {
        return new Object[]{new TrueCareTest(indUser)};
    }
}
