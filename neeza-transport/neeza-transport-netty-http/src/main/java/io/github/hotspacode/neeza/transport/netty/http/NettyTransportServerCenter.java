package io.github.hotspacode.neeza.transport.netty.http;

import io.github.hotspacode.neeza.deputy.annotation.SpiOrder;
import io.github.hotspacode.neeza.transport.api.TransportServerCenter;

@SpiOrder(SpiOrder.LOWEST_PRECEDENCE - 100)
public class NettyTransportServerCenter implements TransportServerCenter {

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {

    }
}
