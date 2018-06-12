package com.niles.caigousdkdemo;

import android.support.multidex.MultiDexApplication;

import com.alibaba.fastjson.JSONObject;
import com.awcs.bankassets.application.MyApplication;
import com.zhu.core.app.Latte;
import com.zhu.ec.database.DatabaseManager;
import com.zhu.ec.login.setting.SettingDelegate;
import com.zhu.ec.utils.Config;

/**
 * Created by Negro
 * Date 2018/6/11
 * Email niulinguo@163.com
 */
public class MyApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
//                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(0)
                .withHttpConnTimeout(60)
                .withJavascriptInterface("latte")
                .configure();

        // 初始化保存的ip与port
        final JSONObject ipSetting = SettingDelegate.loadIpSetting();
        if (ipSetting != null) {
            final String ip = ipSetting.getString("ip");
            final int port = ipSetting.getIntValue("port");
            Latte.getConfigurator().withApiHost(ip + ":" + port);
        } else if (Config.sEnv.hasDefaultIP()) {
            Latte.getConfigurator().withApiHost(SettingDelegate.DEFAULT_IP + ":" + SettingDelegate.DEFAULT_PORT);
        } else {
            Latte.getConfigurator().withApiHost("");
        }

        DatabaseManager.getInstance().init(this);
        MyApplication.init(this);
    }
}
