package com.jinhui.easy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import com.jinhui.easy.utils.IFileUtils;
import com.jinhui.easy.utils.IUtil;

import java.lang.reflect.Method;


/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/5/3 22:01
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class ShotService extends Service {

    private Bitmap mScreenBitmap;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        shot();
    }

    private void shot() {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        float[] dims = {mDisplayMetrics.widthPixels, mDisplayMetrics.heightPixels};
        Class<?> demo = null;
        try {
            demo = Class.forName("android.view.Surface");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Method method = demo.getMethod("screenshot", new Class[]{int.class, int.class});
            mScreenBitmap = (Bitmap) method.invoke(demo.newInstance(), (int) dims[0], (int) dims[1]);
            //这里其实可以直接用null替换demo.newInstance()，因为screenshot是静态方法，所以第一个invoke的第一个参数会被自动忽略~所以其实你填什么都没关系。
            //获取的返回值是个bitmap，然后我们就可以为所欲为了~
            IUtil.saveImage(IFileUtils.getImageCatchDir(), mScreenBitmap, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
