package com.loveSea.nettyShow.rpc.consumer.proxy;

import com.loveSea.nettyShow.rpc.protocol.InvokerProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiahaifeng
 */
@Slf4j
public class RpcProxy {
    public static <T> T create(Class<T> clazz) {
        MethodProxy methodProxy = new MethodProxy(clazz);
        Class<?>[] interfaces = clazz.isInterface() ? new Class[]{clazz} : clazz.getInterfaces();
        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, methodProxy);
        return result;
    }

    @Slf4j
    public static class MethodProxy implements InvocationHandler {
        private Class<?> clazz;
        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            } else {
                return rpcInvoke(proxy, method, args);
            }
        }
        private Object rpcInvoke(Object proxy, Method method, Object[] args) {
            InvokerProtocol invokerProtocol = new InvokerProtocol();
            invokerProtocol.setClassName(this.clazz.getName());
            invokerProtocol.setMethodName(method.getName());
            invokerProtocol.setValues(args);
            invokerProtocol.setParames(method.getParameterTypes());

            final RpcProxyHandler consumerHandler = new RpcProxyHandler();
            EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
            try{
                Bootstrap b = new Bootstrap();
                b.group(eventLoopGroup)
                        .channel(NioSocketChannel.class)
                        .option( ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline()
                                        .addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                                        .addLast("frameEncoder", new LengthFieldPrepender(4))
                                        .addLast("encoder", new ObjectEncoder())
                                        .addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                        .addLast("handler",consumerHandler);
                            }
                        });
         ChannelFuture f = b.connect("localhost", 8080).sync();
                f.channel().writeAndFlush(invokerProtocol).sync();
                f.channel().closeFuture().sync();

            }catch (Exception e) {
                log.error("error", e);
            }finally {
                eventLoopGroup.shutdownGracefully();
            }
            return consumerHandler.getResponse();
        }
    }

}