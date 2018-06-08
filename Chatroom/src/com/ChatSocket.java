package com;

import java.io.*;
import java.net.Socket;

public class ChatSocket extends Thread{

    Socket socket;
    public ChatSocket(Socket s){
        this.socket = s;
    }
/*    Count c = new Count();
    int count = c.count;*/

    public void out(String out) {
        try {
            socket.getOutputStream().write((out+"\n").getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        out("你已经连接到本服务器了");
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(),"UTF-8"));
            String line = "你已连接服务器";
            //接收文字
            socket.getOutputStream().write((line+"\n").getBytes("utf-8"));
            while (socket.getInputStream().available() == 0) {
                byte[] b = new byte[256];
                socket.getInputStream().read(b);
                line = new String(b);
                System.out.println(line);
                socket.getOutputStream().write(("$$$"+"\n").getBytes("utf-8"));
                ChatManage.getChatManage().publish(this, line);
            }

/*            while ((line = br.readLine()) != null) {
                System.out.println("111111111111111");
                System.out.println(line);
                ChatManage.getChatManage().publish(this, line);
                System.out.println("222222222222222");
            }*/
            //接收图片文件
            //读取客户端发来的数据
            InputStream is=socket.getInputStream();
            //将读取到的数据存储到
            File dir=new File("d:\\pic");
            if (!dir.exists()) {
                dir.mkdirs();

            }

            String ip=socket.getInetAddress().getHostName();
            System.out.println(ip+"......connected");
            File file=new File(dir, ip+".jpg");
            FileOutputStream fos=new FileOutputStream(file);
            byte[] buf=new byte[1024];
            int len=0;
            while ((len=is.read())!=-1) {
                fos.write(buf, 0, len);

            }
//获取socket输出流，将上传成功的消息返回给客户端
            OutputStream os=socket.getOutputStream();
            os.write("上传成功！".getBytes());

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            System.out.println("用户断开连接");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("用户断开连接");
            //断开用户-1
/*            count--;
            c.show(count);*/
        }

    }
}
