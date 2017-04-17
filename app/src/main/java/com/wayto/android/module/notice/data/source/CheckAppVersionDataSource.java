package com.wayto.android.module.notice.data.source;


import com.wayto.android.base.BaseDataSourse;
import com.wayto.android.module.notice.data.AppVersionEntity;

import java.io.File;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:37
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface CheckAppVersionDataSource extends BaseDataSourse {

    interface CheckAppVersionCallBack {
        void onCheckAppSuccess(AppVersionEntity entity);

        void onCheckAppDataNotAvailable();
    }

    void checkAppVersion(CheckAppVersionCallBack callBack);

    interface DownloadCallBack {
        String getDownloadURL();

        void onDownloadProgress(int percent);

        void onDownloadComplete(File file);

        void onDownloadDataNotAvailable();

        void onDownloadProgress(long bytesRead, long contentLength, boolean done);
    }

    void downloadApk(DownloadCallBack downloadCallBack);
}
