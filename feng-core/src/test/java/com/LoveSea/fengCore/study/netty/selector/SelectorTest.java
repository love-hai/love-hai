package com.LoveSea.fengCore.study.netty.selector;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xiahaifeng
 */
@Slf4j
public class SelectorTest {
    public Selector getSelector() throws Exception {
        // 创建Selector对象
        Selector selector = Selector.open();

        // 创建通道，配置为非阻塞
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);

        ServerSocket serverSocket = server.socket();
        InetSocketAddress address = new InetSocketAddress(8888);
        serverSocket.bind(address);

        // 向Selector注册事件
        server.register(selector, SelectionKey.OP_ACCEPT);
        return selector;
    }

    @Test
    public void listen() {
        try {
            Selector selector = getSelector();
            while (true) {
                // 阻塞等待就绪的Channel
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    process(key);
                }
            }
        } catch (Exception e) {
            log.error("listen error", e);
        }
    }

    public void process(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            channel.register(key.selector(), SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            int len;
            StringBuilder sb = null;
            while ((len = channel.read(buffer)) > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(content);
                buffer.clear();
            }
            if(sb == null){
                channel.close();
                return;
            }
            SelectionKey writeKey = channel.register(key.selector(), SelectionKey.OP_WRITE);
            writeKey.attach(sb.toString());
            log.info("read:{}", sb);
        } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            ByteBuffer buffer = ByteBuffer.wrap(("output: " + content).getBytes());
            channel.write(buffer);
            channel.close();
        }
    }

    @Test
    public void sendMessage() {
        // 向8888 端口发送消息
        try (SocketChannel client = SocketChannel.open()) {
            client.connect(new InetSocketAddress(8888));
            ByteBuffer buffer = ByteBuffer.wrap("Hello World xiahaifeng".getBytes());
            client.write(buffer);
            // 阻塞等待
            // 接受信息
            ByteBuffer readBuffer = ByteBuffer.allocate(10);
            int len;
            StringBuilder sb = null;
            while ((len = client.read(readBuffer)) > 0) {
                readBuffer.flip();
                String content = new String(readBuffer.array(), 0, len);
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(content);
                readBuffer.clear();
            }
            log.info("read:{}", sb);
        } catch (IOException e) {
            log.error("sendMessage error", e);
        }

    }
}