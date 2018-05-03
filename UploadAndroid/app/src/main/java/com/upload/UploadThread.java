package com.upload;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ASUS on 2018/5/3.
 */

public class UploadThread extends Thread {
    private String fileName;
    private String url;
    public UploadThread(String url,String fileName){
        this.url = url;
        this.fileName = fileName;
    }
    @Override
    public void run() {
        String boundary = "----WebKitFormBoundaryxULdsVfGqvfVfDxu";
        String prefix="--";
        String end = "\r\n";
        try {
            URL httpURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(prefix+boundary+end);
            out.writeBytes("Content-Disposition:form-data;"+"name=\"file\";filename=\""+"Sky.jpg"+"\""+end);
            out.writeBytes(end);
            FileInputStream fileInputStream=new FileInputStream(new File(fileName));
            byte[] b = new byte[1024*4];
            int len;
            while ((len=fileInputStream.read(b))!=-1){
                out.write(b,0,len);
            }
            out.writeBytes(end);
            out.writeBytes(prefix+boundary+prefix+end);
            out.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str="";
            while(reader.readLine()!=null){
                sb.append(str);
            }
            System.out.println("respose:"+sb.toString());
            if(out!=null){
                out.close();
            }
            if(reader!=null){
                reader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
