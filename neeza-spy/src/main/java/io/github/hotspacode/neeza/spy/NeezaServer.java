package io.github.hotspacode.neeza.spy;

import io.github.hotspacode.neeza.base.util.NeezaConstant;
import io.github.hotspacode.neeza.transport.api.init.TransportServerCenterInitHandler;
import io.github.hotspacode.neeza.transport.client.http.TransportClient;

public class NeezaServer {
    private static volatile TransportClient transportClient = null;
    private static volatile TransportServerCenterInitHandler initHandler = null;

    public synchronized static void start() {
        System.getProperties().setProperty(NeezaConstant.SIMPLE_MOCK_VM_PACKAGE_NAME, "io/github/hotspacode/neeza/test");

        if (null == initHandler) {
            initHandler = new TransportServerCenterInitHandler();
            initHandler.init();
        }

        if (null == transportClient) {
            initTransportClient();
        }
    }

    protected static TransportClient getTransportClient() {
        if (transportClient == null) {
            initTransportClient();
        }
        return transportClient;
    }

    protected static synchronized void initTransportClient() {
        if (null == transportClient) {
            transportClient = new TransportClient();
        }
    }
}
