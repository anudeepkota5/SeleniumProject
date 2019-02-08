package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.paymentAPIs.TokensTest;
import org.testng.annotations.Factory;

@Deprecated
public class TokensTestFactory {

    @Factory
    public Object[] tokensTestInstances() {
        return new Object[]{new TokensTest()};
    }
}
