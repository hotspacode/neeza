package io.github.hotspacode.neeza.transport.netty.http.netty;

import io.github.hotspacode.neeza.core.config.NeezaMockConfig;
import io.github.hotspacode.neeza.core.util.StringUtil;
import io.github.hotspacode.neeza.transport.api.command.CommandHandler;
import io.github.hotspacode.neeza.transport.api.command.CommandRequest;
import io.github.hotspacode.neeza.transport.api.command.CommandResponse;
import io.github.hotspacode.neeza.transport.api.util.CommandUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.HttpData;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.*;

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


    private void writeErrorResponse(int statusCode, String message, ChannelHandlerContext ctx) {
        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.valueOf(statusCode),
                //todo change to config properties
                Unpooled.copiedBuffer(message, Charset.forName(NeezaMockConfig.DEFAULT_CHARSET)));

        httpResponse.headers().set("Content-Type", "text/plain; charset=" + NeezaMockConfig.DEFAULT_CHARSET);
        ctx.write(httpResponse);

        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    private void writeResponse(CommandResponse response, ChannelHandlerContext ctx, boolean keepAlive)
            throws Exception {
        byte[] body;
        if (response.isSuccess()) {
            if (response.getResult() == null) {
                body = new byte[] {};
            } else {
                Encoder encoder = pickEncoder(response.getResult().getClass());
                if (encoder == null) {
                    CommandCenterLog.warn("Error when encoding object",
                            new IllegalStateException("No compatible encoder"));
                    return;
                }
                body = encoder.encode(response.getResult());
            }
        } else {
            body = response.getException().getMessage().getBytes(CommandUtil.REQUEST_TARGET);
        }

        HttpResponseStatus status = response.isSuccess() ? OK : BAD_REQUEST;

        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
                Unpooled.copiedBuffer(body));

        httpResponse.headers().set("Content-Type", "text/plain; charset=" + CommandUtil.REQUEST_TARGET);

        //if (keepAlive) {
        //    httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, httpResponse.content().readableBytes());
        //    httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        //}
        //ctx.write(httpResponse);
        //if (!keepAlive) {
        //    ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        //}
        httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, httpResponse.content().readableBytes());
        httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
        ctx.write(httpResponse);
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    private CommandRequest parseRequest(FullHttpRequest request) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
        CommandRequest serverRequest = new CommandRequest();
        Map<String, List<String>> paramMap = queryStringDecoder.parameters();
        // Parse request parameters.
        if (!paramMap.isEmpty()) {
            for (Map.Entry<String, List<String>> p : paramMap.entrySet()) {
                if (!p.getValue().isEmpty()) {
                    serverRequest.addParam(p.getKey(), p.getValue().get(0));
                }
            }
        }
        // Deal with post method, parameter in post has more privilege compared to that in querystring
        if (request.method().equals(HttpMethod.POST)) {
            // support multi-part and form-urlencoded
            HttpPostRequestDecoder postRequestDecoder = null;
            try {
                postRequestDecoder = new HttpPostRequestDecoder(request);
                for (InterfaceHttpData data : postRequestDecoder.getBodyHttpDatas()) {
                    data.retain(); // must retain each attr before destroy
                    if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                        if (data instanceof HttpData) {
                            HttpData httpData = (HttpData) data;
                            try {
                                String name = httpData.getName();
                                String value = httpData.getString();
                                serverRequest.addParam(name, value);
                            } catch (IOException e) {
                            }
                        }
                    }
                }
            } finally {
                if (postRequestDecoder != null) {
                    postRequestDecoder.destroy();
                }
            }
        }
        // Parse command name.
        String target = parseTarget(queryStringDecoder.rawPath());
        serverRequest.addMetadata(CommandUtil.REQUEST_TARGET, target);
        // Parse body.
        if (request.content().readableBytes() <= 0) {
            serverRequest.setBody(null);
        } else {
            byte[] body = new byte[request.content().readableBytes()];
            request.content().getBytes(0, body);
            serverRequest.setBody(body);
        }
        return serverRequest;
    }

    private String parseTarget(String uri) {
        if (StringUtil.isEmpty(uri)) {
            return "";
        }

        // Remove the / of the uri as the target(command name)
        // Usually the uri is start with /
        int start = uri.indexOf('/');
        if (start != -1) {
            return uri.substring(start + 1);
        }

        return uri;
    }

    private CommandHandler getHandler(String commandName) {
        if (StringUtil.isEmpty(commandName)) {
            return null;
        }
        return HttpServer.handlerMap.get(commandName);
    }

}
