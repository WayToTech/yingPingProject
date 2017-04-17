package com.wayto.android.vendor.retrofit.download;

import android.os.Looper;
import android.os.Message;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:44
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public abstract class UploadProgressHandler extends ProgressHandler {

    private static final int UPLOAD_PROGRESS = 0;
    protected ResponseHandler mHandler = new ResponseHandler(this, Looper.getMainLooper());

    @Override
    protected void sendMessage(ProgressBean progressBean) {
        mHandler.obtainMessage(UPLOAD_PROGRESS, progressBean).sendToTarget();

    }

    @Override
    protected void handleMessage(Message message) {
        switch (message.what) {
            case UPLOAD_PROGRESS:
                ProgressBean progressBean = (ProgressBean) message.obj;
                onProgress(progressBean.getBytesRead(), progressBean.getContentLength(), progressBean.isDone());
        }
    }

}
