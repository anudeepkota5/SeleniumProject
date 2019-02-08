package com.mckinsey.lime.desktopWebTestFactories;

import com.mckinsey.lime.desktopWebTests.LandingTest;
import org.testng.annotations.Factory;

public class LandingTestFactory {

    @Factory
    public Object[] landingInstances() {
        return new Object[]{new LandingTest()};
    }


}
