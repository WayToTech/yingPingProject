package com.jinhui.easy.module.notice.data.source;

import com.jinhui.easy.common.Constant;
import com.jinhui.easy.entity.ResponseModel;
import com.jinhui.easy.module.notice.data.NoticeDetailsEntity;
import com.jinhui.easy.module.notice.data.NoticeEntity;
import com.jinhui.easy.utils.ISpfUtil;
import com.jinhui.easy.vendor.retrofit.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 10:34
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class NoticeRemoteRepo implements NoticeDataSource {

    Call<ResponseModel<List<NoticeEntity>>> call;
    Call<ResponseModel<NoticeDetailsEntity>> noticeDetailsCall;

    @Override
    public void requestNoticeData(final NoticeCallBack callBack) {
        String url="notice/appNoticeList?sessionid=" + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString();
        call = RetrofitManager.getInstance().getService().getNoticeList(url);
        call.enqueue(new Callback<ResponseModel<List<NoticeEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<NoticeEntity>>> call, Response<ResponseModel<List<NoticeEntity>>> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        callBack.onNoticeSuccess(response.body().getData());
                    } else {
                        callBack.onNoticeFailure(response.body().getCode(), response.body().getMessage());
                    }
                } else {
                    callBack.onNoticeFailure(response.code(), "请求失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<NoticeEntity>>> call, Throwable t) {
                callBack.onNoticeFailure(405, "请求失败");
            }
        });
    }

    @Override
    public void requestNoticeDetailsData(String id, final NoticeDetailsCallBack callBack) {
        String url = "notice/appDetailById?id=" + id+"&sessionid=" + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString();
        noticeDetailsCall = RetrofitManager.getInstance().getService().getNoticeDetails(url);
        noticeDetailsCall.enqueue(new Callback<ResponseModel<NoticeDetailsEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<NoticeDetailsEntity>> call, Response<ResponseModel<NoticeDetailsEntity>> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        callBack.onNoticeDetailsSuccess(response.body().getData());
                    } else {
                        callBack.onNoticeDetailsFailure(response.code(), response.body().getMessage());
                    }
                } else {
                    callBack.onNoticeDetailsFailure(response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<NoticeDetailsEntity>> call, Throwable t) {
                callBack.onNoticeDetailsFailure(405, "请求失败");
            }
        });
    }
}
