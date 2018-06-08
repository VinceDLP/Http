package client;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Maincliet{
    public static void main(String []args){
        Date date = new Date();
        try {
            BufferedWriter bw;
            BufferedReader br;
            Socket s = new Socket("127.0.0.1",12345);
            br = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            bw = new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream()));
            String line = null;
            Scanner xy= new Scanner(System.in);
            System.out.print("请输入你的名字：");
            String name = xy.next();
            while((line = br.readLine())!=null){
                System.out.println("he:"+line);
                while (true){
                    System.out.println("输入1为发送文字，输入2为发送图片");
                    String i = xy.next();
                    switch (i){
                        case "1":
                            System.out.println("输入“esc”退出发送文字界面");
                            while (true){
/*                                while (s.getInputStream().available() == 0) {
                                    byte[] b = new byte[256];
                                    s.getInputStream().read(b);
                                    line = new String(b);
                                    System.out.println(line);
                                    接收文字，目前无法并行
                                }*/
                                String x = xy.next();
                                String str = name+date.toString()+":"+x;
                                s.getOutputStream().write(str.getBytes());
                                if (x.equals("esc")){
                                    break;
                                }
                            }
                            break;
                        case "2":
                            FileInputStream fis=new FileInputStream("d:\\015.jpg");
//获取socket输出流,将图片数据发送到服务端
                            OutputStream os=s .getOutputStream();
                            byte[] buf=new byte[1024];
                            int len=0;
                            while ((len=fis.read(buf))!=-1) {
                                os.write(buf, 0, len);

                            }
//通知服务端数据发送完毕，让服务端停止读取。
                            s.shutdownOutput();
//读取服务端返回的内容，
                            InputStream in=s.getInputStream();
                            byte[] bufIn=new byte[1024];
                            int lenin=in.read(buf);
                            String text=new String(buf,0,lenin);
                            System.out.print(text);
                            break;
                        default:
                            System.out.println("结束");
                            break;
                    }
                }
/*                System.out.print("请输入文字：");
                while (true){
                    String x = xy.next();
                    String str = name+date.toString()+":"+x;
                    s.getOutputStream().write(str.getBytes());
                }*/
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*        try {
            Socket s = new Socket("127.0.0.1",12345);
            br = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            bw = new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream()));
            String line = null;
            while((line = br.readLine())!=null && !line.equals("$$$")){
                System.out.println("he:"+line);
                Scanner xy = new Scanner(System.in);
                String str = xy.nextLine();
                s.getOutputStream().write(str.getBytes());
            }
            br.close();
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
