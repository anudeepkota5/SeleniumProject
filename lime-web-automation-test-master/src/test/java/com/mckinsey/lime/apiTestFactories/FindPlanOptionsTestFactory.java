package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.FindPlanOptionsTest;
import org.testng.annotations.Factory;

public class FindPlanOptionsTestFactory {

    @Factory
    public Object[] findPlanOptionsTestInstances() {
        return new Object[]{new FindPlanOptionsTest()};
    }
}
