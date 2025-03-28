package com.loveSea.nettyShow.rpc.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;


/**
 * @author xiahaifeng
 */
@Slf4j
public class RpcRegistry {
    private int port;

    public RpcRegistry(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 自定义协议解码器
                            /*
                             * 入参有5个，分别解释如下
                             * maxFrameLength：框架的最大长度。如果帧的长度大于此值，则将抛出TooLongFrameException。
                             * lengthFieldOffset：长度字段的偏移量：即对应的长度字段在整个消息数据中得位置
                             * lengthFieldLength：长度字段的长度。如：长度字段是int型表示，那么这个值就是4（long型就是8）
                             * lengthAdjustment：要添加到长度字段值的补偿值
                             * */
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            // 自定义协议编码器
                            pipeline.addLast(new LengthFieldPrepender(4));
                            // 实参处理
                            pipeline.addLast("encoder", new ObjectEncoder());
                            pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                            // 自定义的handler
                            pipeline.addLast(new RegistryHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = server.bind(port).sync();
            log.info("GP RPC Registry start listen at {}", port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("GP RPC Registry start error", e);
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new RpcRegistry(8080).start();
    }
}