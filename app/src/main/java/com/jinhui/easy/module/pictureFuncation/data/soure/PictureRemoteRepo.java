package com.jinhui.easy.module.pictureFuncation.data.soure;

import android.database.Cursor;
import android.media.ExifInterface;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.jinhui.easy.base.DataApplication;
import com.jinhui.easy.module.pictureFuncation.data.PictureEntity;
import com.jinhui.easy.utils.IFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询相册
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:32
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class PictureRemoteRepo implements PictureDateSource {
    private final String TAG = getClass().getSimpleName();

    private static PictureRemoteRepo remoteRepo;

    public static PictureRemoteRepo newInstance() {
        if (remoteRepo == null) {
            remoteRepo = new PictureRemoteRepo();
        }
        return remoteRepo;
    }

    @Override
    public void queryAlbumAction(final QueryAlbumCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor mCursor = DataApplication.getInstance().getContentResolver()
                        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                new String[]{MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media.TITLE},
                                MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                                new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_ADDED + " desc");
                List<PictureEntity> entities = new ArrayList<>();
                while (mCursor.moveToNext()) {
                    try {
                        // 获取图片的路径
                        String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        // 获取该图片的父路径名
                        String parentName = new File(path).getParentFile().getName();
                        /*只过滤相册*/
                        if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(parentName) && "Camera".equals(parentName) || "catch".equals(parentName)) {
                            ExifInterface exifInterface = new ExifInterface(path);
                            String date = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
                            if (!TextUtils.isEmpty(date)) {
                                String newDate = date.substring(0, date.indexOf(" ")).replace(":", "-");
                                PictureEntity entity = new PictureEntity();
                                entity.setUrl(path);
                                entity.setDate(newDate + " " + date.substring(date.indexOf(" "), date.length()));
                                entities.add(entity);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mCursor.close();
                String catchPath =  IFileUtils.getSDROOT() + File.separator + IFileUtils.FILE_ROOT_DIRECTORY + File.separator + IFileUtils.PROJECT_ROOT_DIRECTORY + File.separator + IFileUtils.IMAGE_CATCH_DIR;
                File[] files = new File(catchPath).listFiles();
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        if (!file.isDirectory()) {
                            PictureEntity entity = new PictureEntity();
                            entity.setUrl(file.getAbsolutePath());
                            entity.setDate("1968-02-12");
                            entities.add(0,entity);
                        }
                    }
                }
                callBack.getAlbumSuccess(entities);
            }
        }).start();
    }
}
