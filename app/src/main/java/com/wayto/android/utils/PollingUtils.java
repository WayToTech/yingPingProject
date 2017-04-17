package com.wayto.android.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.wayto.android.base.DataApplication;

/**
 * 轮询工具类
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:22
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class PollingUtils {
    /**
     * 开户轮询
     *
     * @param minute 分钟
     */
    public static void startPollingService(int minute) {
        //获取AlarmManager系统服务
        AlarmManager manager = (AlarmManager) DataApplication.getInstance().getSystemService(Context.ALARM_SERVICE);
        //包装需要执行Service的Intent
        Intent intent = new Intent("");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(DataApplication.getInstance(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), minute * 1000 * 60, pendingIntent);
    }

    /**
     * 停止轮询
     */
    public static void stopPollingService() {
        AlarmManager manager = (AlarmManager) DataApplication.getInstance().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(DataApplication.getInstance(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //取消正在执行的服务
        manager.cancel(pendingIntent);
    }
}
