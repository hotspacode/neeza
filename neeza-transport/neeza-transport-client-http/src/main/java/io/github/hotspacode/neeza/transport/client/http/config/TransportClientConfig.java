package io.github.hotspacode.neeza.transport.client.http.config;

import io.github.hotspacode.neeza.base.util.NeezaConstant;

/**
 * @author moxingwang
 */
public final class TransportClientConfig {
    private static final TransportClientConfig config = new TransportClientConfig();

    private TransportClientConfig() {
    }

    private String serverIp = "localhost";
    private int serverPort = NeezaConstant.DEFAULT_SERVER_PORT;

    public static TransportClientConfig getConfig() {
        return config;
    }

    public static String getServerIp() {
        return config.serverIp;
    }

    public static void setServerIp(String serverIp) {
        config.serverIp = serverIp;
    }

    public static int getServerPort() {
        return config.serverPort;
    }

    public static void setServerPort(int serverPort) {
        config.serverPort = serverPort;
    }
}
