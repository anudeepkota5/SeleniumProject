package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.TopUpForOthersTest;
import org.testng.annotations.Factory;

@Deprecated
public class TopUpForOthersTestFactory {

    @Factory
    public Object[] topUpForOthersInstances() {
        return new Object[]{new TopUpForOthersTest()};
    }

}
