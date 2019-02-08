package com.mckinsey.lime.apiTestFactories;

import com.mckinsey.lime.apiTests.TranslationsTest;
import org.testng.annotations.Factory;

@Deprecated
public class TranslationsTestFactory {

    @Factory
    public Object[] translationsTestInstances() {
        return new Object[]{new TranslationsTest()};
    }
}
