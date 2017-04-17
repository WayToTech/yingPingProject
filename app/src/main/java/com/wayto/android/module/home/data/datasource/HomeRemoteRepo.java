package com.wayto.android.module.home.data.datasource;

import com.wayto.android.entity.ResponseModel;
import com.wayto.android.module.home.data.HomeEntity;
import com.wayto.android.vendor.retrofit.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/12 22:31
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class HomeRemoteRepo implements HomeDataSource {

    Call<ResponseModel<HomeEntity>> call;

    @Override
    public void cancelRequest() {

    }

    @Override
    public void requestMember(final HomeCallBack callBack) {
        call = RetrofitManager.getInstance().getService().getMember();
        call.enqueue(new Callback<ResponseModel<HomeEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<HomeEntity>> call, Response<ResponseModel<HomeEntity>> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200){
                        callBack.onHomeSuccess(response.body().getData());
                    }else {
                        callBack.onHomeFailure(response.code(), response.body().getMessage());
                    }
                } else {
                    callBack.onHomeFailure(response.code(), "请求失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<HomeEntity>> call, Throwable t) {
                callBack.onHomeFailure(405, "请求失败");
            }
        });
    }
}
