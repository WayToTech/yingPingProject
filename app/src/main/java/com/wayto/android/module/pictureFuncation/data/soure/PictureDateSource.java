package com.wayto.android.module.pictureFuncation.data.soure;

import com.wayto.android.module.pictureFuncation.data.PictureEntity;

import java.util.List;

/**
 *
 * 
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:32
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
*/
public interface PictureDateSource {

    interface QueryAlbumCallBack {
        void getAlbumSuccess(List<PictureEntity> lists);

        void getAlbumFailure();
    }

    void queryAlbumAction(QueryAlbumCallBack callBack);
}
