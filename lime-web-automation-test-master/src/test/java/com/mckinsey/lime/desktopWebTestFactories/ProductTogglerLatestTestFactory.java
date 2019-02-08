package com.mckinsey.lime.desktopWebTestFactories;

import com.mckinsey.lime.dataPrividers.ProductTogglerAllScenarios;
import com.mckinsey.lime.desktopWebTests.ProductTogglerTestLatest;
import com.mckinsey.lime.testDataBeans.ProductTogglerScenario;
import org.testng.annotations.Factory;

public class ProductTogglerLatestTestFactory {

    @Factory(dataProvider = "ProductTogglerAllScenarios", dataProviderClass = ProductTogglerAllScenarios.class)
    public Object[] getProductTogglerInstances(ProductTogglerScenario currentScenario) {
        return new ProductTogglerTestLatest[]{new ProductTogglerTestLatest(currentScenario)};
    }


}
