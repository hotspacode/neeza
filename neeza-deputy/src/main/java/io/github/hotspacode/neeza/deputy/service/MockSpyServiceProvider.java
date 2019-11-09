package io.github.hotspacode.neeza.deputy.service;


import io.github.hotspacode.neeza.deputy.api.IMockSpyService;
import io.github.hotspacode.neeza.deputy.api.INeezaSerialization;
import io.github.hotspacode.neeza.deputy.common.SpiLoader;

public final class MockSpyServiceProvider {

    private static IMockSpyService instance = null;

    static {
        resolveInstance();
    }

    private static void resolveInstance() {
        instance = SpiLoader.loadHighestPriorityInstance(IMockSpyService.class);
    }

    public static IMockSpyService getInstance() {
        return instance;
    }

    private MockSpyServiceProvider() {
    }
}
