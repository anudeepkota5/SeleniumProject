package com.mckinsey.lime.desktopWebTestFactories;

import com.mckinsey.lime.desktopWebTests.PlanDetailsTest;
import org.testng.annotations.Factory;

public class PlanDetailsTestFactory {

    @Factory
    public Object[] planDetailTestInstances() {
        return new Object[]{new PlanDetailsTest()};
    }


}
