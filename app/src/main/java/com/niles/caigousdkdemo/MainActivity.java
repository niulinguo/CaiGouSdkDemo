package com.niles.caigousdkdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.TimeUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    EditText mURLEditView;
    EditText mUsernameView;
    EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mURLEditView = findViewById(R.id.et_url);
        mUsernameView = findViewById(R.id.et_username);
        mPasswordView = findViewById(R.id.et_password);

        mURLEditView.setText(BuildConfig.SERVER_URL);
        mUsernameView.setText(BuildConfig.USERNAME);
        mPasswordView.setText(BuildConfig.PASSWORD);
    }

    public void openPanDian(View view) {
        final String protocol = mURLEditView.getText().toString();
        final String username = mUsernameView.getText().toString();
        final String deviceId = Installation.id(this);

        Intent intent = createPanDianIntent(protocol, username, deviceId);
        openPanDian(intent);
    }

    public void openCaiGou(View view) {
        final String protocol = mURLEditView.getText().toString();
        final String username = mUsernameView.getText().toString();
        final String password = mPasswordView.getText().toString();
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
                        Intent intent = createCaiGouIntent(protocol, username, response.body(), time, deviceId);
                        openCaiGou(intent);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Intent intent = createCaiGouIntent(protocol, username, response.body(), time, deviceId);
                        openCaiGou(intent);
                    }
                });
    }

    private void openPanDian(Intent intent) {
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "请安装盘点App", Toast.LENGTH_SHORT).show();

            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pgyer.com/zzpandian"));
            startActivity(webIntent);
        }
    }

    private void openCaiGou(Intent intent) {
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "请安装采购App", Toast.LENGTH_SHORT).show();

            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pgyer.com/zzcaigou"));
            startActivity(webIntent);
        }
    }

    private Intent createPanDianIntent(String protocol, String user, String deviceId) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", user);
            jsonObject.put("device_id", deviceId);
            jsonObject.put("protocol", protocol);
            final Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.zhu.pandian", "com.zhu.ec.mainmenu.MainActivity"));
            intent.putExtra("JSON_RESULT", jsonObject.toString());
            return intent;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private Intent createCaiGouIntent(String protocol, String user, String loginInfo, String time, String deviceId) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", user);
            jsonObject.put("login_info", loginInfo);
            jsonObject.put("time", time);
            jsonObject.put("device_id", deviceId);
            jsonObject.put("protocol", protocol);

            final Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.zhu.procurement", "com.zhu.ec.mainmenu.MainActivity"));

            String jsonStr = jsonObject.toString();

            intent.putExtra("JSON_RESULT", jsonStr);

            Log.e(TAG, jsonStr);

            return intent;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
