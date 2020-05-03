package com.study.base.net.d3_tcp_bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author xq
 * @data 2020/5/3
 **/
public class BioServer {
    public static void main(String[] args) {
        int port = 8088;
        try {
            final ServerSocket socket = new ServerSocket(port);
            final Socket clientSocket = socket.accept();
            System.out.println("接受连接，客户端信息==> " + clientSocket);

            //写
            OutputStream out = clientSocket.getOutputStream();
            out.write("服务侧写的消息\r\n".getBytes(Charset.forName("UTF-8")));
            out.flush();

            Thread.sleep(1000);

            //读
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.printf("%s\n", line);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}