package com.upload;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.io.File;

/**
 * Created by ASUS on 2018/5/3.
 */

public class UploadActivity extends Activity{
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="http://192.168.174.1:8080/upload/servlet/Upload";
                File file= Environment.getExternalStorageDirectory();
                File fileAbs=new File(file,"Sky.jpg");
                String fileName = "";
                UploadThread thread = new UploadThread(url,fileName);
                thread.start();
            }
        });
    }
}
