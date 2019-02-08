package com.mckinsey.lime.desktopWebTestFactories;

import com.mckinsey.lime.dataPrividers.ProductToggler;
import com.mckinsey.lime.desktopWebTests.ProductTogglerTestLatest_DataDriven;
import com.mckinsey.lime.mobileTestFactories.DataProviders;
import com.mckinsey.lime.testDataBeans.ProductTogglerTestScenario;
import org.testng.annotations.Factory;

public class ProductTogglerDataDrivenTestFactory {

    @Factory(dataProvider = "ProductToggler", dataProviderClass = ProductToggler.class)
    public Object[] getAQuoteInstances(ProductTogglerTestScenario currentScenario) {
        return new Object[]{new ProductTogglerTestLatest_DataDriven(currentScenario)};
    }


}
