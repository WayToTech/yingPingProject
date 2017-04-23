package com.wayto.android.vendor.retrofit;

import com.wayto.android.BuildConfig;
import com.wayto.android.common.Constant;
import com.wayto.android.utils.ISpfUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 管理类
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:43
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class RetrofitManager {

    private static RetrofitManager instance;

    private static Retrofit mRetrofit;

    public static RetrofitManager getInstance() {
        if (instance == null) {
            instance = new RetrofitManager();
        }
        return instance;
    }

    /**
     * 返回Service
     *
     * @return
     */
    public APIService getService() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
//                    .client(getClient())
                    .baseUrl(BuildConfig.DOMAI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        APIService service = mRetrofit.create(APIService.class);
        return service;
    }

    /**
     * 设置Client请求头
     *
     * @return
     */
    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "bearer " + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString())
                        .build();
                return chain.proceed(request);
            }
        }).build();
        return client;
    }
}
