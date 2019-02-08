package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.PreLogin_PayNowTest;
import org.testng.annotations.Factory;

@Deprecated
public class PreLogin_PayNowTestFactory {

    @Factory
    public Object[] preLogin_PayNowInstances() {
        return new Object[]{new PreLogin_PayNowTest()};
    }

}
