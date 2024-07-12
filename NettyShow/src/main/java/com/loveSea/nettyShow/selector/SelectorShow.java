package com.loveSea.nettyShow.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author xiahaifeng
 */

public class SelectorShow {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            // 2. 获取通道
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            // 3. 设置为非阻塞
            socketChannel.configureBlocking(false);
            // 4. 绑定连接
            socketChannel.bind(new InetSocketAddress(9999));
            // 5. 将通道注册到选择器
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println("Selector open error" + e.getMessage());
        }
    }
}