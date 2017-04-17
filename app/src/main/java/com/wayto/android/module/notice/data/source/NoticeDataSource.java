package com.wayto.android.module.notice.data.source;

import com.wayto.android.module.notice.data.NoticeDetailsEntity;
import com.wayto.android.module.notice.data.NoticeEntity;

import java.util.List;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 10:33
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface NoticeDataSource {

    interface NoticeCallBack{
        void onNoticeSuccess(List<NoticeEntity> entities);
        void onNoticeFailure(int code,String msg);
    }

    interface NoticeDetailsCallBack{
        void onNoticeDetailsStart();
        void onNoticeDetailsEnd();
        void onNoticeDetailsSuccess(NoticeDetailsEntity entity);
        void onNoticeDetailsFailure(int code,String msg);
    }

    void requestNoticeData(NoticeCallBack callBack);

    void requestNoticeDetailsData(String id,NoticeDetailsCallBack callBack);
}
