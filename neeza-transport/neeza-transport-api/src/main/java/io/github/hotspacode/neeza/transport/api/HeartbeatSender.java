package io.github.hotspacode.neeza.transport.api;


public interface HeartbeatSender {


    boolean sendHeartbeat() throws Exception;

    /**
     * 心跳间隔（毫秒）
     *
     * @return
     */
    long intervalMilliseconds();
}
