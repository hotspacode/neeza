package io.github.hotspacode.neeza.transport.netty.http;

import io.github.hotspacode.neeza.transport.api.init.TransportServerCenterInitHandler;
import org.junit.Test;

/**
 * @author moxingwang
 */
public class NettyServerTest {
    @Test
    public void testServer() {
        TransportServerCenterInitHandler initHandler = new TransportServerCenterInitHandler();
        initHandler.init();
        System.out.println("netty server init test started !");
    }
}
