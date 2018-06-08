package com;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerListener extends Thread{
    Count c = new Count();
    int count = c.count;
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("server accept");
            while (true){

                Socket socket = serverSocket.accept();
                ChatSocket cs = new ChatSocket(socket);
                count++;
                c.show(count);
                cs.start();
                ChatManage.getChatManage().add(cs);
/*                ServerSocket serverSocket = new ServerSocket(12345);
                System.out.println("server accept");
                Socket socket = serverSocket.accept();

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(),"utf-8"));

                String line = "你已连接服务器";
                socket.getOutputStream().write((line+"\n").getBytes("utf-8"));
                while (socket.getInputStream().available() == 0) {
                    byte[] b = new byte[256];
                    socket.getInputStream().read(b);
                    line = new String(b);
                    System.out.println(line);
                    socket.getOutputStream().write(("$$$"+"\n").getBytes("utf-8"));
                }*/

                // ChatSocket chatSocket = new ChatSocket(socket);
                // chatSocket.start();
                //ChatManage.getChatManage().add(chatSocket);
            }
        } catch (IOException e) {
            System.out.println("用户断开连接");
        }
    }
}
