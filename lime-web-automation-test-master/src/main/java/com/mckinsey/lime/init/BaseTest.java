package com.mckinsey.lime.init;

public class BaseTest {
    private boolean anyAssertionFailed;
    public boolean isAnyAssertionFailed() {
        return anyAssertionFailed;
    }

    public void setAnyAssertionFailed(boolean anyAssertionFailed) {
        this.anyAssertionFailed = anyAssertionFailed;
    }

}
