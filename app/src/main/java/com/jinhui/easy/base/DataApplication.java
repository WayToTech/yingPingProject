package com.jinhui.easy.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import cn.sharesdk.framework.ShareSDK;


/**
 * Application全局类
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:12
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class DataApplication extends Application {
    private String TAG = getClass().getSimpleName();

    private static DataApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ShareSDK.initSDK(this);
    }

    public static DataApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        /*分包处理 DEX*/
        MultiDex.install(this);
    }
}
