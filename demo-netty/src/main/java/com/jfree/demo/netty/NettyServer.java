package com.jfree.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class NettyServer {

    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }


    public void start() throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap sp = new ServerBootstrap();
            //option配置属性
            sp.channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.TCP_NODELAY, true) // 不延迟，消息立即发送
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//长连接
                    .group(work, boss)
                    .localAddress(this.port)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                            socketChannel.pipeline().addLast("http-codec", new HttpServerCodec());
                            socketChannel.pipeline().addLast("aggregator", new HttpObjectAggregator(8192));
                            socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            socketChannel.pipeline().addLast("myHandler", new MyNettyHandler());

                        }
                    });
            ChannelFuture f = sp.bind().sync();
            Constant.ip = f.channel().localAddress().toString();
            System.out.println("netty服务启动");
            f.channel().closeFuture().sync();
        } finally {
            work.shutdownGracefully().sync();
            boss.shutdownGracefully().sync();
        }
    }
}
