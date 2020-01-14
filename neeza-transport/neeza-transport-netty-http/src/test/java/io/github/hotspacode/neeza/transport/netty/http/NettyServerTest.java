package io.github.hotspacode.neeza.transport.netty.http;

import io.github.hotspacode.neeza.transport.api.init.TransportServerCenterInitHandler;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author moxingwang
 */
public class NettyServerTest {
    public static void main(String[] args) {
        TransportServerCenterInitHandler initHandler = new TransportServerCenterInitHandler();
        initHandler.init();
        System.out.println("netty server init test started !");
//        Thread.sleep(100000000);
    }

    @Test
    public void testServer() throws InterruptedException {
        TransportServerCenterInitHandler initHandler = new TransportServerCenterInitHandler();
        initHandler.init();
        System.out.println("netty server init test started !");
//        Thread.sleep(100000000);
        return;
    }
}

