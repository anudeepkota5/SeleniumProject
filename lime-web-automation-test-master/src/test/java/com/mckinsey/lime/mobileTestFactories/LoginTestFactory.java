package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.LoginTest;
import org.testng.annotations.Factory;

@Deprecated
public class LoginTestFactory {

    @Factory
    public Object[] loginInstances() {
        return new Object[]{new LoginTest()};
    }

}
