package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.PreLogin_TopUpForOthersTest;
import org.testng.annotations.Factory;

@Deprecated
public class PreLogin_TopUpForOthersTestFactory {

    @Factory
    public Object[] preLogin_TopUpForOthersInstances() {
        return new Object[]{new PreLogin_TopUpForOthersTest()};
    }

}
