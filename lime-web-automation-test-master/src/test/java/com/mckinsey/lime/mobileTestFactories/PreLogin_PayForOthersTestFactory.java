package com.mckinsey.lime.mobileTestFactories;

import com.mckinsey.lime.mobileWebTests.PreLogin_PayForOthersTest;
import org.testng.annotations.Factory;

@Deprecated
public class PreLogin_PayForOthersTestFactory {

    @Factory
    public Object[] preLogin_PayForOthersInstances() {
        return new Object[]{new PreLogin_PayForOthersTest()};
    }

}
