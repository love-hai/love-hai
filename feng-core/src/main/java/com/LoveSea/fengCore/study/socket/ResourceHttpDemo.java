package com.LoveSea.fengCore.study.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xiahaifeng
 */
@Component
@Slf4j
public class ResourceHttpDemo {

    private int http_port;

    public int getPort() {
        return http_port;
    }

    public ResourceHttpDemo() {
        Thread thread1 = new Thread(() -> {
            try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                 Selector selector = Selector.open()) {
                // 绑定到一个空闲端口
                serverSocketChannel.bind(new InetSocketAddress(0));
                serverSocketChannel.configureBlocking(false);
                // 将 serverSocketChannel 注册到 Selector，监听接入事件
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                int port = serverSocketChannel.socket().getLocalPort();  // 获取绑定的端口
                this.http_port = port;
                log.info("端口号：" + port);
                while (true) {
                    try {
                        // 选择就绪的通道
                        int count = selector.select();
                        if (count == 0) {
                            continue;
                        }
                        Set<SelectionKey> selectedKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectedKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            iterator.remove();
                            try {
                                if (key.isAcceptable()) {
                                    handleAccept(key);
                                } else if (key.isReadable()) {
                                    asyncHandleRead(key);
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } catch (Exception e) {
                        log.error("发生异常", e);
                    }
                }
            } catch (Exception e) {
                log.error("检查端口是否被占用时发生异常", e);
            }

        });
        thread1.start();
    }

    private final int bufferSize = 1024;
    private final String localCharset = "UTF-8";

    private void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel;
        clientChannel = serverSocketChannel.accept();
        clientChannel.configureBlocking(false);
        // 将客户端连接注册到 Selector，监听读取事件
        clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        log.info("Client connected: " + clientChannel.getRemoteAddress());
    }

    private void asyncHandleRead(SelectionKey key) throws IOException {
        try {
            handleRW(key);
        } catch (Exception e) {
            log.error("处理客户端请求时发生异常", e);
            try {
                key.channel().close();
            } catch (IOException ex) {
                log.error("关闭客户端连接时发生异常", ex);
            }
        }
    }

    // 处理客户端请求（读取数据）
    private void handleRW(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        if (socketChannel.read(buffer) == -1) {
            socketChannel.close();
        } else {
            // 将 buffer 转为读状态
            buffer.flip();
            // 将 buffer 中接收到的内容按编码格式保存
            String line = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
            // 控制台打印请求头
            String[] reqMessage = line.split("\r\n");
            for (String mes : reqMessage) {
                log.info(mes);
                // 遇到空行说明报文头已经打印完
                if (mes.isEmpty()) {
                    break;
                }
            }
            String[] httpInfo = reqMessage[0].split(" ");
            String method = httpInfo[0];
            String url = httpInfo[1];
            String httpVersion = httpInfo[2];
            log.info("Method: {}; URL: {}; HTTP Version: {}", method, url, httpVersion);
            // 返回数据给客户端
            StringBuilder builder = new StringBuilder();
            // 响应报文首行，200 表示处理成功
            builder.append("HTTP/1.1 200 OK\r\n");
            builder.append("Content-Type:text/html;charset=").append(localCharset).append("\r\n");
            // 报文头结束后加一个空行
            builder.append("\r\n");
            if (url.contains("download")) {
                String html = "<html><head><title>Download</title></head><body><h1>Download</h1><p>Download page</p></body></html>";
                builder.append(html);
            }
            buffer = ByteBuffer.wrap(builder.toString().getBytes(localCharset));
            socketChannel.write(buffer);
            // 关闭 Socket
            socketChannel.close();
        }
    }

    // 处理写数据（响应客户端）
    private void handleWrite(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        if (!clientChannel.isConnected()) {
            return;
        }
        String request = (String) key.attachment();
        log.info("Request received:\n" + request);
        String responseMessage = "xhf";
        clientChannel.write(ByteBuffer.wrap("HTTP/1.1 200 OK\n".getBytes()));
        clientChannel.write(ByteBuffer.wrap("Content-Type: text/plain; charset=utf-8\n".getBytes()));
        clientChannel.write(ByteBuffer.wrap("Access-Control-Allow-Origin: *\n".getBytes()));
        clientChannel.write(ByteBuffer.wrap(("Content-Length: " + responseMessage.length() + "\n").getBytes()));
        clientChannel.write(ByteBuffer.wrap("Connection: keep-alive\n".getBytes()));
        clientChannel.write(ByteBuffer.wrap("\n".getBytes()));
        clientChannel.write(ByteBuffer.wrap(responseMessage.getBytes()));
        clientChannel.write(ByteBuffer.wrap("Connection: close\n".getBytes()));
        // 响应发送完成后，关闭连接
        clientChannel.close();
        log.info("Response sent to client");
    }
}