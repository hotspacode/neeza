package io.github.hotspacode.neeza.spy;

import io.github.hotspacode.neeza.base.util.NeezaConstant;
import io.github.hotspacode.neeza.transport.api.init.TransportServerCenterInitHandler;
import io.github.hotspacode.neeza.transport.client.http.TransportClient;

public class NeezaServer {
    private static volatile TransportClient transportClient = null;
    private static volatile TransportServerCenterInitHandler initHandler = null;
    private static String serverIp;
    private static Integer serverPort;

    public synchronized static void start(String serverIp,Integer serverPort) {
        System.getProperties().setProperty(NeezaConstant.SIMPLE_MOCK_VM_PACKAGE_NAME, "io/github/hotspacode/neeza/test");

        NeezaServer.serverIp = serverIp;
        NeezaServer.serverPort = serverPort;

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

    protected static String getServerIp() {
        return serverIp;
    }


    protected static Integer getServerPort() {
        return serverPort;
    }

}
