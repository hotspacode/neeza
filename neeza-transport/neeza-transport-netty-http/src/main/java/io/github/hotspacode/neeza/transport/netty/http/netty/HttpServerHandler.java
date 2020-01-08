package io.github.hotspacode.neeza.transport.netty.http.netty;

import io.github.hotspacode.neeza.core.util.StringUtil;
import io.github.hotspacode.neeza.transport.api.command.CommandRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpUtil;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;

/**
 * @author moxingwang
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest httpRequest = (FullHttpRequest)msg;
        try {


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
