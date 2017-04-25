package com.wayto.android.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.View;

import com.wayto.android.base.DataApplication;
import com.wayto.android.entity.ImageEntity;
import com.wayto.android.entity.VideoEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 常用工具类
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:21
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class IUtil {

    /**
     * 获取字符串资源
     *
     * @param res
     * @return
     */
    public static String getStrToRes(int res) {
        return DataApplication.getInstance().getResources().getString(res);
    }

    /**
     * Url过滤
     *
     * @param url
     * @return
     */
    public static String fitterUrl(String url) {
        if (!url.contains("http://")) {
            url = "file://" + url;
        }
        return url;
    }

    /**
     * 转换 List<String> --> List<ImageEntity>
     *
     * @param stringList
     * @return
     * @Creater chenhaobo
     */
    public static List<ImageEntity> convertStringListToImgList(List<String> stringList) {
        List<ImageEntity> imgList = new ArrayList<>();
        if (stringList != null && stringList.size() > 0) {
            for (int i = 0; i < stringList.size(); i++) {
                ImageEntity img = new ImageEntity();
                img.setKey("");
                img.setUrl(stringList.get(i));
                imgList.add(img);
            }
        }
        return imgList;
    }

    /**
     * 转换 List<String> --> List<VideoEntity>
     *
     * @param stringList
     * @return
     * @Creater zls
     */
    public static List<VideoEntity> convertStringListToVideoList(List<String> stringList) {
        List<VideoEntity> videos = new ArrayList<>();
        if (stringList != null && stringList.size() > 0) {
            for (int i = 0; i < stringList.size(); i++) {
                VideoEntity video = new VideoEntity();
                video.setUrl(stringList.get(i));
                video.setName("");
                videos.add(video);
            }
        }
        return videos;
    }

    /**
     * 截屏方法
     *
     * @return
     */
    public static Bitmap shot(Activity context) {
        View view = context.getWindow().getDecorView();
        Display display = context.getWindowManager().getDefaultDisplay();
        view.layout(0, 0, display.getWidth(), display.getHeight());
        view.setDrawingCacheEnabled(true);//允许当前窗口保存缓存信息，这样getDrawingCache()方法才会返回一个Bitmap
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache());
        saveImage(IFileUtils.getImageCatchDir(),bmp,0);
        return bmp;
    }

    /**
     * 保存图片到指定路径
     * Save image with specified size
     *
     * @param filePath the image file save path 储存路径
     * @param bitmap   the image what be save   目标图片
     * @param size     the file size of image   期望大小
     */
    public static File saveImage(String filePath, Bitmap bitmap, long size) {
        File result = new File(filePath.substring(0, filePath.lastIndexOf("/")));

        if (!result.exists() && !result.mkdirs()) return null;
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new File(filePath);
    }
}
