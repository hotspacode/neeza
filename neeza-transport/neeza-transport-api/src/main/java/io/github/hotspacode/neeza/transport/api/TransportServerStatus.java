package io.github.hotspacode.neeza.transport.api;

public class TransportServerStatus {
    private static int realPort = 0;

    public static void setRealPort(int realPort) {
        TransportServerStatus.realPort = realPort;
    }

    public static int getRealPort() {
        return realPort;
    }
}
