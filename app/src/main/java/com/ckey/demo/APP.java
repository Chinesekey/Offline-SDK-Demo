package com.ckey.demo;

import android.app.Application;

import com.dictionary.sdk.global.ChinesekeyConfigure;

public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //根据设备号初始化字典sdk
        ChinesekeyConfigure.preInit(this, "device012", this.getPackageName());
    }
}
