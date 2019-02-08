package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.PayForOthersTest;
import org.testng.annotations.Factory;

@Deprecated
public class PayForOthersTestFactory {

    @Factory
    public Object[] payForOthersTestInstances() {
        return new Object[]{new PayForOthersTest()};
    }

}
