package com.mckinsey.lime.desktopWebTestFactories;

import com.mckinsey.lime.desktopWebTests.SmartFinderTest;
import org.testng.annotations.Factory;

public class SmartFinderTestFactory {

    @Factory
    public Object[] landingInstances() {
        return new Object[]{new SmartFinderTest()};
    }


}
