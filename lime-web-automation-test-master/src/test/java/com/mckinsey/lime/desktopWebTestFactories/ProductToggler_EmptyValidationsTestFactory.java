package com.mckinsey.lime.desktopWebTestFactories;

import com.mckinsey.lime.desktopWebTests.ProductTogglerTest_EmptyValidations;
import org.testng.annotations.Factory;

public class ProductToggler_EmptyValidationsTestFactory {

    @Factory
    public Object[] getProductToggler_EmptyValidationsInstances() {
        return new Object[]{new ProductTogglerTest_EmptyValidations()};
    }


}
