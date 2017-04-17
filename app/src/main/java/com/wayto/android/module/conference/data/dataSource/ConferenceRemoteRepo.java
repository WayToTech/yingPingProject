package com.wayto.android.module.conference.data.dataSource;

import com.wayto.android.entity.ResponseModel;
import com.wayto.android.module.conference.data.ConferenceDetailsEntity;
import com.wayto.android.module.conference.data.ConferenceEntity;
import com.wayto.android.vendor.retrofit.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 0:36
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class ConferenceRemoteRepo implements ConferenceDatasource {

    private Call<ResponseModel<List<ConferenceEntity>>> call;

    @Override
    public void requestMeetingNotice(final ConferenceCallBack callBack) {
        call = RetrofitManager.getInstance().getService().getMeetingNotice();
        call.enqueue(new Callback<ResponseModel<List<ConferenceEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<ConferenceEntity>>> call, Response<ResponseModel<List<ConferenceEntity>>> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        callBack.onConferenceSuccess(response.body().getData());
                    } else {
                        callBack.onConferenceFailure(response.code(), response.body().getMessage());
                    }
                } else {
                    callBack.onConferenceFailure(response.code(), "请求失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<ConferenceEntity>>> call, Throwable t) {
                callBack.onConferenceFailure(405, "请求失败");
            }
        });
    }

    @Override
    public void requestMeetingDetails(int id, final ConferenceDetailsCallBack callBack) {
        callBack.onConferenceDetailsStart();
        String url = "meetingNotice/appDetailById?id=" + id;
        Call<ResponseModel<ConferenceDetailsEntity>> call = RetrofitManager.getInstance().getService().getMeetingNoticeDetails(url);
        call.enqueue(new Callback<ResponseModel<ConferenceDetailsEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<ConferenceDetailsEntity>> call, Response<ResponseModel<ConferenceDetailsEntity>> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        callBack.onConferenceDetailsSuccess(response.body().getData());
                        callBack.onConferenceDetailsEnd();
                    } else {
                        callBack.onConferenceDetailsFailure(response.code(), response.body().getMessage());
                        callBack.onConferenceDetailsEnd();
                    }
                } else {
                    callBack.onConferenceDetailsFailure(response.code(), "请求失败");
                    callBack.onConferenceDetailsEnd();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ConferenceDetailsEntity>> call, Throwable t) {
                callBack.onConferenceDetailsFailure(405, "请求失败");
                callBack.onConferenceDetailsEnd();
            }
        });
    }
}
