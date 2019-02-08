package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.FindPlanResultsTest;
import org.testng.annotations.Factory;

public class FindPlanResultsTestFactory {

    @Factory
    public Object[] findPlanResultsTestInstances() {
        return new Object[]{new FindPlanResultsTest()};
    }
}
