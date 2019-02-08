package com.mckinsey.lime.desktopWebTestFactories;

import com.mckinsey.lime.desktopWebTests.ProductsOverviewTest;
import org.testng.annotations.Factory;

public class OurPlansTestFactory {

    @Factory
    public Object[] landingInstances() {
        return new Object[]{new ProductsOverviewTest()};
    }


}
