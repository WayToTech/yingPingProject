package com.jinhui.easy.module.conference.data.dataSource;

import com.jinhui.easy.module.conference.data.ConferenceDetailsEntity;
import com.jinhui.easy.module.conference.data.ConferenceEntity;

import java.util.List;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 0:33
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface ConferenceDatasource {

    interface ConferenceCallBack {
        void onConferenceSuccess(List<ConferenceEntity> entities);

        void onConferenceFailure(int code, String msg);
    }

    interface ConferenceDetailsCallBack{
        void onConferenceDetailsStart();
        void onConferenceDetailsEnd();
        void onConferenceDetailsSuccess(ConferenceDetailsEntity entity);
        void onConferenceDetailsFailure(int code,String msg);
    }

    void requestMeetingNotice(ConferenceCallBack callBack);

    void requestMeetingDetails(int id,ConferenceDetailsCallBack callBack);
}
