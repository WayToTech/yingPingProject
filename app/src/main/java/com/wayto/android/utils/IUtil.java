package com.wayto.android.utils;

import com.wayto.android.base.DataApplication;
import com.wayto.android.entity.ImageEntity;
import com.wayto.android.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

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
}
