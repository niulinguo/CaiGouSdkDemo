package com.niles.caigousdkdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.TimeUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

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
        final String server = "接口地址";
        final String port = "接口端口";
        final String username = "用户名";
        final String password = "密码";
        final String time = TimeUtils.date2String(new Date());
        final String deviceId = Installation.id(this);
        OkGo.<String>post("http://" + server + ":" + port + "/auth/login")
                .params("username", username)
                .params("password", password)
                .params("time", time)
                .params("timestamp", time)
                .params("deviceId", deviceId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Intent intent = createIntent5(server, port, username, response.body(), time, deviceId);
                        openCaiGou(intent);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
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

    private Intent createIntent5(String ip, String port, String user, String loginInfo, String time, String deviceId) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", user);
            jsonObject.put("login_info", loginInfo);
            jsonObject.put("time", time);
            jsonObject.put("device_id", deviceId);
            jsonObject.put("server", ip);
            jsonObject.put("port", port);
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
