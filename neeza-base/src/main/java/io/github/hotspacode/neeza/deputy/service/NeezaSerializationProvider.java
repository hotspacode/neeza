package io.github.hotspacode.neeza.base.service;


import io.github.hotspacode.neeza.base.api.INeezaSerialization;
import io.github.hotspacode.neeza.base.common.SpiLoader;

public final class NeezaSerializationProvider {

    private static INeezaSerialization instance = null;

    static {
        resolveInstance();
    }

    private static void resolveInstance() {
        instance = SpiLoader.loadHighestPriorityInstance(INeezaSerialization.class);
    }

    public static INeezaSerialization getInstance() {
        return instance;
    }

    private NeezaSerializationProvider() {
    }
}
