package server;

import server.service.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner {
    private boolean started = false;
    private static final int PORT = 12138;
    ServerSocket serverSocket = null;

    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);

            System.out.println("服务器已启动!");
            while (true) {
                Socket socket = serverSocket.accept();
                // 接受到请求，新开一个线程处理
                System.out.println("一个客户端已连接！");
                new Client(socket);
            }
        } catch (IOException e) {
            System.out.println("服务器开启失败，该端口已被占用，请重试！");
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerRunner serviceRunner = new ServerRunner();
        serviceRunner.start();
    }
}

