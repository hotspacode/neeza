package io.github.hotspacode.neeza.transport.netty.http;

import io.github.hotspacode.neeza.transport.api.HeartbeatSender;

public class NettyHeartbeatSender implements HeartbeatSender {
    @Override
    public boolean sendHeartbeat() throws Exception {
        return false;
    }

    @Override
    public long intervalMilliseconds() {
        return 0;
    }
}
