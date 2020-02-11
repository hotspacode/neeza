package io.github.hotspacode.neeza.transport.api.init;

import io.github.hotspacode.neeza.base.common.SpiLoader;
import io.github.hotspacode.neeza.base.log.NeezaLog;
import io.github.hotspacode.neeza.transport.api.TransportServerCenter;

public class TransportServerCenterInitHandler {
    public void init() {
        TransportServerCenter serverCenter = SpiLoader.loadHighestPriorityInstance(TransportServerCenter.class);

        if (serverCenter == null) {
            return;
        }

        try {
            serverCenter.beforeStart();
            serverCenter.start();
        } catch (Exception e) {
            NeezaLog.warn("neeza server start fail",e);
        }
    }

}
