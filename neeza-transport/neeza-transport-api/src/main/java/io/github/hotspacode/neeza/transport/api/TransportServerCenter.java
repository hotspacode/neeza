package io.github.hotspacode.neeza.transport.api;

public interface TransportServerCenter {

    void beforeStart() throws Exception;

    void start() throws Exception;

    void stop() throws Exception;
}
