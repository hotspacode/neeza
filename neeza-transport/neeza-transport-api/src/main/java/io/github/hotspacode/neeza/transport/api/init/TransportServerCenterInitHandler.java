package io.github.hotspacode.neeza.transport.api.init;

import io.github.hotspacode.neeza.deputy.common.SpiLoader;
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
            e.printStackTrace();
        }

        //todo log info
    }

}
