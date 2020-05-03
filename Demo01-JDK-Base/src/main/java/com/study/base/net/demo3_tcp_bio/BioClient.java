package com.study.base.net.demo3_tcp_bio;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;

/**
 * @author xq
 * @data 2020/5/3
 **/
public class BioClient {
    static String IP = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        int port = 8088;
        try (Socket socket = new Socket(IP, port)) {
            //读消息
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = br.readLine();
                System.out.printf("%s\n", line);
            }

            //写消息
            {
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                pw.println("客户端的消息1");
                pw.flush();

                OutputStream out = socket.getOutputStream();
                out.write("客户端的消息2\r\n".getBytes(Charset.forName("UTF-8")));
                out.flush();
            }
        } catch (SocketException e) {
            System.out.printf("%s\n", "服务器断开了你的连接");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接失败");
        }
    }
}
