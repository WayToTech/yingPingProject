package com.jinhui.easy.module.home.data.datasource;

import com.jinhui.easy.base.BaseDataSourse;
import com.jinhui.easy.module.home.data.HomeEntity;

import java.io.File;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/12 22:29
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface HomeDataSource extends BaseDataSourse {

    interface HomeCallBack {
        void onHomeSuccess(HomeEntity entity);

        void onHomeFailure(int code, String msg);
    }

    void requestMember(HomeCallBack callBack);

    interface RecordMsgCallBack {
        void onRecordSuccess();

        void onRecordFailure(String msg);
    }

    void recordMsg(String title,String context, File file,RecordMsgCallBack callBack);

}
