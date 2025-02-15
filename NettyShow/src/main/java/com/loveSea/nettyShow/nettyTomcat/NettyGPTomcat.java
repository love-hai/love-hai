package com.loveSea.nettyShow.nettyTomcat;

import com.loveSea.nettyShow.tomcat.GPServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author xiahaifeng
 */
@Slf4j
public class NettyGPTomcat {
    private int port = 8080;
    private Map<String, NettyGPServlet> servletMapping = new HashMap<>();
    private Properties webxml = new Properties();

    private void init() {
        try {
            String WEB_INF = this.getClass().getResource("/netty/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "nettyweb.properties");
            webxml.load(fis);
            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".class");
                    NettyGPServlet obj = (NettyGPServlet) Class.forName(className).getDeclaredConstructor().newInstance();
                    servletMapping.put(url, obj);
                }
            }
        } catch (Exception e) {
            log.error("init error", e);
        }
    }

    public void start() {
        this.init();
        // boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // work线程
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            // 1. 创建对象
            ServerBootstrap server = new ServerBootstrap();
            // 2. 配置参数
            server.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());
                            socketChannel.pipeline().addLast(new NettyGPTomcatHandler(servletMapping));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 启动服务
            ChannelFuture future = server.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("start error", e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyGPTomcat().start();
    }

}