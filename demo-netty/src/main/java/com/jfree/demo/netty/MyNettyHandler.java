package com.jfree.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.springframework.util.StringUtils;


public class MyNettyHandler extends SimpleChannelInboundHandler<Object> {

    private ChannelHandlerContext ctx;

    private String token;

    private WebSocketServerHandshaker handshaker;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建立连接，通道开启！");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("messageReceived之后-----");
        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Constant.removeValue(this);
        ctx.close();
        System.out.println("客户端连接异常");
        System.out.println("err=" + cause.toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Constant.removeValue(this);
        System.out.println("与客户端断开连接，通道关闭！");
        ctx.close();
    }


    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object obj) throws Exception {

        if (obj instanceof FullHttpRequest) {
            this.ctx = ctx;
            FullHttpRequest request = (FullHttpRequest) obj;
            handlerHttpRequest(ctx, request);
        } else if (obj instanceof PingWebSocketFrame) { // 判断是否是Ping消息
            System.out.println("---发送ping---");
            PingWebSocketFrame pin = (PingWebSocketFrame) obj;
            ctx.channel().writeAndFlush(new PongWebSocketFrame(pin.content().retain()));
            return;
        } else if (obj instanceof TextWebSocketFrame) {
                     //后面要修改到业务线程池中处理
            new BusinessActionModHandler(this, obj).doWork();

        } else if (obj instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) obj).retain());
            return;
        } else {
            System.out.println("不接受此次传输值");
        }

    }


    public int handlerHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String token = null;
        try {
            token = ParaAnalysis.parse(request).get("token");
        } catch (Exception e) {
            //log
        }
        if (!request.getDecoderResult().isSuccess() || !("websocket".equals(request.headers().get("Upgrade"))) || StringUtils.isEmpty(token)) {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            System.out.println("客户端或者URL异常");
            return -1;
        }
        this.token = token;
        Constant.addLine(token, this);
        // 正常WebSocket的Http连接请求，构造握手响应返回
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://" + request.headers().get(HttpHeaders.Names.HOST), null, false);
        handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null) { // 无法处理的websocket版本
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else { // 向客户端发送websocket握手,完成握手
            handshaker.handshake(ctx.channel(), request);
            // 记录管道处理上下文，便于服务器推送数据到客户端
        }

        return 0;
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
        // 返回应答给客户端
        if (response.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
            HttpHeaders.setContentLength(response, response.content().readableBytes());
        }

        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(response);
        if (!HttpHeaders.isKeepAlive(request) || response.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }


    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public WebSocketServerHandshaker getHandshaker() {
        return handshaker;
    }

    public void setHandshaker(WebSocketServerHandshaker handshaker) {
        this.handshaker = handshaker;
    }
}
