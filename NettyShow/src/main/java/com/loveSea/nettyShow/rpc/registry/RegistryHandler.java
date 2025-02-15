package com.loveSea.nettyShow.rpc.registry;

import com.loveSea.nettyShow.rpc.protocol.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiahaifeng
 */
@Slf4j
public class RegistryHandler extends ChannelInboundHandlerAdapter {
    private List<String> classNames = new ArrayList<>();

    private Map<String, Object> registryMap = new ConcurrentHashMap<>();

    public RegistryHandler() {
        scanClass("com.loveSea.nettyShow.rpc.provider");
        doRegistry();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        InvokerProtocol protocol = (InvokerProtocol) msg;
        if (registryMap.containsKey(protocol.getClassName())) {
            Object clazz = registryMap.get(protocol.getClassName());
            Method method = clazz.getClass().getMethod(protocol.getMethodName(), protocol.getParames());
            result = method.invoke(clazz, protocol.getValues());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void scanClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = null;
        if (url != null) {
            dir = new File(url.getFile());
        }
        if (dir != null) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.isDirectory()) {
                    scanClass(packageName + "." + file.getName());
                } else {
                    classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
                }

            }
        }


    }

    private void doRegistry() {
        if (classNames.isEmpty()) {
            return;
        }
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Class<?> i = clazz.getInterfaces()[0];
                registryMap.put(i.getName(), clazz.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                log.error("doRegistry error", e);
            }
        }
    }
}