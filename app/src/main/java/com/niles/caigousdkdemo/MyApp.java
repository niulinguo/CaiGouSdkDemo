package com.niles.caigousdkdemo;

import android.support.multidex.MultiDexApplication;

import com.zhu.ec.ProcurementInitializer;

/**
 * Created by Negro
 * Date 2018/6/11
 * Email niulinguo@163.com
 */
public class MyApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ProcurementInitializer.init(this);
    }
}
