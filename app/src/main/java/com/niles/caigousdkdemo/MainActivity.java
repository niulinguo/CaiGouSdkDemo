package com.niles.caigousdkdemo;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCaiGou1(View view) {
        Intent intent = createIntent1();
        openCaiGou(intent);
    }

    public void openCaiGou2(View view) {
        Intent intent = createIntent2();
        openCaiGou(intent);
    }

    public void openCaiGou3(View view) {
        Intent intent = createIntent3();
        openCaiGou(intent);
    }

    public void openCaiGou4(View view) {
        Intent intent = createIntent4();
        openCaiGou(intent);
    }

    public void openCaiGou5(View view) {
        Intent intent = createIntent5();
        openCaiGou(intent);
    }

    private void openCaiGou(Intent intent) {
        if (intent == null) {
            Toast.makeText(this, "没有安装采购App(-1)", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "没有安装采购App(-2)", Toast.LENGTH_SHORT).show();
        }
    }

    private Intent createIntent1() {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("proucrement://user=admin,password=admin,server=192.168.1.1,port=8080"));
        return intent;
    }

    private Intent createIntent2() {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("proucrement://"));
        intent.putExtra("user", "admin");
        intent.putExtra("password", "admin");
        intent.putExtra("server", "192.168.1.1");
        intent.putExtra("port", "8080");
        return intent;
    }

    private Intent createIntent3() {
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.zhu.procurement", "com.zhu.ec.mainmenu.MainActivity"));
        intent.putExtra("user", "admin");
        intent.putExtra("password", "admin");
        intent.putExtra("server", "192.168.1.1");
        intent.putExtra("port", "8080");
        return intent;
    }

    private Intent createIntent4() {
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.zhu.procurement", "com.zhu.ec.mainmenu.MainActivity"));
        intent.putExtra("JSON_RESULT", "{" +
                "\"user\"=\"admin\"," +
                "\"password\"=\"admin\"," +
                "\"server\"=\"192.168.1.1\"," +
                "\"port\"=\"8080\"" +
                "}");
        return intent;
    }

    private Intent createIntent5() {
        try {
            final JSONObject loginInfo = new JSONObject();
            loginInfo.put("code", 200);
            loginInfo.put("message", "demo");
            @SuppressLint("SimpleDateFormat") final String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            final String deviceId = UUID.randomUUID().toString();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", "admin");
            jsonObject.put("login_info", loginInfo.toString());
            jsonObject.put("time", time);
            jsonObject.put("device_id", deviceId);
            jsonObject.put("server", "192.168.1.1");
            jsonObject.put("port", "8080");
            final Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.zhu.procurement", "com.zhu.ec.mainmenu.MainActivity"));
            intent.putExtra("JSON_RESULT", jsonObject.toString());
            return intent;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
