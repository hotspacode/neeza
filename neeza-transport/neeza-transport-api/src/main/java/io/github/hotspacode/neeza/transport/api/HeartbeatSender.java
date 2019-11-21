package io.github.hotspacode.neeza.transport.api;

public interface HeartbeatSender {

    boolean sendHeartbeat() throws Exception;

    long intervalMilliseconds();
}
