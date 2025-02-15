package com.LoveSea.fengCore.study.netty.tomcat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author xiahaifeng
 */
@Slf4j
public class GPTomcat {

    private int port = 8080;
    private ServerSocket server;
    private Map<String, GPServlet> servletMapping = new HashMap<>();

    private Properties webxml = new Properties();

    private void init() {
        try {
            String WEB_INF = this.getClass().getResource("/netty/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");
            webxml.load(fis);
            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".class");
                    GPServlet obj = (GPServlet) Class.forName(className).getDeclaredConstructor().newInstance();
                    servletMapping.put(url, obj);
                }
            }
        } catch (Exception e) {
            log.error("init error", e);
        }
    }

    public GPTomcat(int port) {
        this.port = port;
    }

    public void start() {
        this.init();
        try {
            server = new ServerSocket(this.port);
            log.info("GPTomcat is start..");
            while (true) {
                Socket socket = server.accept();
                process(socket);
            }
        } catch (Exception e) {
            log.error("start error", e);
        }
    }

    private void process(Socket socket) throws Exception {
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        try (socket) {
            GPRequest request = new GPRequest(is);
            GPResponse response = new GPResponse(os);
            String url = request.getUrl();
            if (servletMapping.containsKey(url)) {
                servletMapping.get(url).service(request, response);
            } else {
                response.write("404 - Not Found");
            }
        } catch (Exception e) {
            log.error("process error", e);
        }
    }

    public static void main(String[] args) {
        new GPTomcat(8080).start();
        // http://127.0.0.1:8080/secondServlet.do
        // http://127.0.0.1:8080/firstServlet.do
    }
}