package io.github.hotspacode.neeza.spy.provider;


import io.github.hotspacode.neeza.base.api.IMockPushSpyService;
import io.github.hotspacode.neeza.base.common.SpiLoader;

public final class MockPushSpyServiceProvider {

    private static IMockPushSpyService instance = null;

    static {
        resolveInstance();
    }

    private static void resolveInstance() {
        instance = SpiLoader.loadHighestPriorityInstance(IMockPushSpyService.class);
    }

    public static IMockPushSpyService getInstance() {
        return instance;
    }

    private MockPushSpyServiceProvider() {
    }
}
