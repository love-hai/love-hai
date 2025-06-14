package com.LoveSea.fengCore.study.socket;

import com.LoveSea.fengCore.commons.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * @author xiahaifeng
 */
@Slf4j
public class WebSocketServerExample extends WebSocketServer {
    public WebSocketServerExample(InetSocketAddress address) {
        super(address);
        log.info(this.getPort() + "");
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        log.info("New connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        log.info("Closed connection: {}", conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        try {
            log.info("Received message: {}", message);
            // 查看命令
            JsonNode jsonObject = JsonUtils.readTree(message);

            JsonNode commandEle = jsonObject.get("command");
            if (null == commandEle) {
                log.error("Command not found in message: {}", message);
                return;
            }
            String command = commandEle.asText();
            JsonNode paramsEle = jsonObject.get("params");
            switch (command) {
                case "list":
                    this.sendList(conn, jsonObject);
                    break;
                case "delete":
                    this.delete(conn, jsonObject);
                    break;
                case "clear":
                    conn.send("Bye, " + conn.getRemoteSocketAddress());
                    break;
                default:
                    conn.send("Unknown command: " + command);
            }
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
        }
    }

    void sendList(WebSocket conn, JsonNode jsonObject) {
    }

    void delete(WebSocket conn, JsonNode jsonObject) {

    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        log.error("Error occurred on connection: {}", conn.getRemoteSocketAddress(), ex);
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started successfully!");
    }

    public static void main(String[] args) {
        int port = getRandomAvailablePort();
        WebSocketServer server = new WebSocketServerExample(new InetSocketAddress(port));
        server.start();
        System.out.println("WebSocket server started on port: " + port);
    }

    public static int getRandomAvailablePort() {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            // 获取分配给 serverSocket 的端口
            return serverSocket.getLocalPort();
        } catch (IOException e) {
            log.error("Error occurred while getting random available port", e);
            return -1; // 返回无效端口表示出错
        }
    }
}