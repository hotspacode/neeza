package io.github.hotspacode.neeza.transport.netty.http.netty;

import io.github.hotspacode.neeza.base.util.StringUtil;
import io.github.hotspacode.neeza.transport.api.command.CommandHandler;
import io.github.hotspacode.neeza.transport.api.config.TransportConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author moxingwang
 */
public class HttpServer {
    private static final Integer DEFAULT_PORT = 8818;

    private Channel channel;

    final static Map<String, CommandHandler> handlerMap = new ConcurrentHashMap<String, CommandHandler>();


    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            int port;
            try {
                if (StringUtil.isEmpty(TransportConfig.getPort())) {
                    port = DEFAULT_PORT;
                } else {
                    port = Integer.valueOf(TransportConfig.getPort());
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Illegal port: " + TransportConfig.getPort());
            }


            ChannelFuture channelFuture = null;
            int retryCount = 0;
            while (true) {
                int newPort = getNewPort(port, retryCount);

                try {
                    // Bind and start to accept incoming connections.
                    channelFuture = b.bind(DEFAULT_PORT).sync(); // (7)
                    break;
                } catch (Exception e) {
                    TimeUnit.MILLISECONDS.sleep(30);
                    retryCount++;
                }
            }


            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            channelFuture.channel().closeFuture().sync();

            channel = channelFuture.channel();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }


    public void close() {
        if (null != channel) {
            channel.close();
        }
    }

    private int getNewPort(int basePort, int retryCount) {
        return basePort + retryCount / 3;
    }

    public void registerCommand(String commandName, CommandHandler handler) {
        if (StringUtil.isEmpty(commandName) || handler == null) {
            return;
        }

        if (handlerMap.containsKey(commandName)) {
            return;
        }

        handlerMap.put(commandName, handler);
    }

    public void registerCommands(Map<String, CommandHandler> handlerMap) {
        if (handlerMap != null) {
            for (Map.Entry<String, CommandHandler> e : handlerMap.entrySet()) {
                registerCommand(e.getKey(), e.getValue());
            }
        }
    }

}
