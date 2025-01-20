package com.LoveSea.fengCore.study.socket;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Optional;

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
        log.info("Closed connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        try {
            log.info("Received message: " + message);
            // 查看命令
            JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
            JsonElement commandEle = jsonObject.get("command");
            if (null == commandEle) {
                log.error("Command not found in message: " + message);
                return;
            }
            String command = commandEle.getAsString();
            JsonElement paramsEle = jsonObject.get("params");
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
            log.error("Error processing message: " + message, e);
        }
    }

    void sendList(WebSocket conn, JsonObject jsonObject) {
        JsonElement paramsEle = jsonObject.get("params");
        JsonObject params = paramsEle.getAsJsonObject();
        String searchValue = Optional.ofNullable(params.get("searchValue")).map(JsonElement::getAsString).orElse("");
        long minDownloadId = params.get("minDownloadId").getAsLong();
        JsonArray jsonArray = new JsonArray();
        for (int i = 10; i > 0; i--) {
            JsonObject downloadItem = new JsonObject();
            downloadItem.addProperty("id", i);
            downloadItem.addProperty("name", "name" + i);
            downloadItem.addProperty("url", "url" + i);
            downloadItem.addProperty("downloadTime", "2021-07-01 12:00:00");
            jsonArray.add(downloadItem);
        }
        jsonObject.add("response", jsonArray);
        jsonObject.addProperty("success", true);
        conn.send(jsonObject.toString());
    }

    void delete(WebSocket conn, JsonObject jsonObject) {
        JsonElement paramsEle = jsonObject.get("params");
        JsonObject params = paramsEle.getAsJsonObject();
        long id = params.get("id").getAsLong();
        jsonObject.addProperty("success", true);
        conn.send(jsonObject.toString());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        log.error("Error occurred on connection: " + conn.getRemoteSocketAddress(), ex);
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