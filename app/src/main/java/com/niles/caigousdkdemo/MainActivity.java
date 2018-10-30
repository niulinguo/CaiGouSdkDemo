package com.niles.caigousdkdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCaiGou(View view) {
        final String protocol = BuildConfig.SERVER_URL;
        final String username = BuildConfig.USERNAME;
        final String password;
        try {
            password = Base64.encodeToString(
                    EncryptUtils.encryptAES(
                            BuildConfig.PASSWORD.getBytes("UTF-8"),
                            BuildConfig.AES_PASSWORD.getBytes("UTF-8"),
                            "AES/CBC/PKCS5Padding",
                            BuildConfig.AES_IV.getBytes("UTF-8")
                    ),
                    Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        final String time = TimeUtils.date2String(new Date());
        final String deviceId = Installation.id(this);
        OkGo.<String>post(protocol + "/auth/login")
                .params("username", username)
                .params("password", password)
                .params("time", time)
                .params("timestamp", time)
                .params("deviceId", deviceId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Intent intent = createIntent5(protocol, username, response.body(), time, deviceId);
                        openCaiGou(intent);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Intent intent = createIntent5(protocol, username, response.body(), time, deviceId);
                        openCaiGou(intent);
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

    private Intent createIntent5(String protocol, String user, String loginInfo, String time, String deviceId) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", user);
            jsonObject.put("login_info", loginInfo);
            jsonObject.put("time", time);
            jsonObject.put("device_id", deviceId);
            jsonObject.put("protocol", protocol);
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
