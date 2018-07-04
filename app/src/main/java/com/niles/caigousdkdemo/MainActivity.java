package com.niles.caigousdkdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCaiGou(View view) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        final Uri data = Uri.parse("proucrement://user=admin,password=admin,server=192.168.1.1,port=8080");
        intent.setData(data);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "没有安装采购App", Toast.LENGTH_SHORT).show();
        }
    }
}
