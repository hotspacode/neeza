package io.github.hotspacode.neeza.transport.netty.http;

import io.github.hotspacode.neeza.core.concurrent.NamedThreadFactory;
import io.github.hotspacode.neeza.base.annotation.SpiOrder;
import io.github.hotspacode.neeza.base.common.SpiLoader;
import io.github.hotspacode.neeza.transport.api.TransportServerCenter;
import io.github.hotspacode.neeza.transport.api.command.CommandHandler;
import io.github.hotspacode.neeza.transport.api.command.CommandHandlerProvider;
import io.github.hotspacode.neeza.transport.netty.http.netty.HttpServer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpiOrder(SpiOrder.LOWEST_PRECEDENCE - 100)
public class NettyTransportServerCenter implements TransportServerCenter {
    private final HttpServer server = new HttpServer();

    private final ExecutorService pool = Executors.newSingleThreadExecutor(
            new NamedThreadFactory("neeza-netty-transport-executor"));

    @Override
    public void beforeStart() throws Exception {
        Map<String, CommandHandler> handlers = CommandHandlerProvider.getInstance().namedHandlers();
        server.registerCommands(handlers);
    }

    @Override
    public void start() throws Exception {
        pool.submit(() -> {
            try {
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        });
    }

    @Override
    public void stop() throws Exception {
        server.close();
        pool.shutdownNow();
    }
}
