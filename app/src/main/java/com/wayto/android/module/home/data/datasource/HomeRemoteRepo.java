package com.wayto.android.module.home.data.datasource;

import com.wayto.android.R;
import com.wayto.android.common.Constant;
import com.wayto.android.entity.ResponseModel;
import com.wayto.android.module.home.data.HomeEntity;
import com.wayto.android.utils.ISpfUtil;
import com.wayto.android.vendor.retrofit.RetrofitManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
        String url = "memberIndex?sessionid=" + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString();
        call = RetrofitManager.getInstance().getService().getMember(url);
        call.enqueue(new Callback<ResponseModel<HomeEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<HomeEntity>> call, Response<ResponseModel<HomeEntity>> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        callBack.onHomeSuccess(response.body().getData());
                    } else {
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

    @Override
    public void recordMsg(String title, String context, File file, final RecordMsgCallBack callBack) {
        String url = "message/appUploadMessage?sessionid=" + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString();
        Map<String, RequestBody> map = new HashMap<>();
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        map.put("file \"; fileName=\"" + file.getName(), body);
        map.put("fileName", RequestBody.create(MediaType.parse("text/plain"), file.getName()));
        map.put("title", RequestBody.create(MediaType.parse("text/plain"), title));
        map.put("content", RequestBody.create(MediaType.parse("text/plain"), context));
        Call<ResponseModel> call = RetrofitManager.getInstance().getService().recordMsg(url, map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        callBack.onRecordSuccess();
                    } else {
                        callBack.onRecordFailure("上报失败");
                    }
                } else {
                    callBack.onRecordFailure("上报失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                callBack.onRecordFailure("上报失败");
            }
        });
    }
}
