package com.mckinsey.lime.desktopWebTestFactories;

import com.mckinsey.lime.desktopWebTests.ComparePlansTest;
import org.testng.annotations.Factory;

public class ComparePlansTestFactory {

    @Factory
    public Object[] comparePlanInstances() {
        return new Object[]{new ComparePlansTest()};
    }


}
