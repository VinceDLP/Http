package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ASUS on 2018/3/25.
 */

public class HttpThread1 extends Thread{
    String url;

    String editText;

    public HttpThread1(String url,String editText){
        this.url = url;
        this.editText = editText;
    }
    private void doGet(){
        url=url+"?editText="+editText;
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str=reader.readLine())!=null){
                sb.append(str);
            }
            System.out.println("result:"+sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        doGet();
    }
}
