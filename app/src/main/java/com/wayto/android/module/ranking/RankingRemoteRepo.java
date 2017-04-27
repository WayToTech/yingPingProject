package com.wayto.android.module.ranking;

import com.wayto.android.common.Constant;
import com.wayto.android.entity.ResponseModel;
import com.wayto.android.module.ranking.data.RankingEntity;
import com.wayto.android.module.ranking.data.RepoCallBack;
import com.wayto.android.utils.ISpfUtil;
import com.wayto.android.vendor.retrofit.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/27 9:30
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class RankingRemoteRepo {

    public void requestRanking(final RepoCallBack<RankingEntity> listRepoCallBack) {
        String url = "paimingIndex?sessionid=" + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString();
        Call<ResponseModel<RankingEntity>> call = RetrofitManager.getInstance().getService().getRanking(url);
        call.enqueue(new Callback<ResponseModel<RankingEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<RankingEntity>> call, Response<ResponseModel<RankingEntity>> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        listRepoCallBack.onDataAvailable(response.body().getData());
                    } else {
                        listRepoCallBack.onDataNotAvailable(405, response.body().getMessage());
                    }
                } else {
                    listRepoCallBack.onDataNotAvailable(405, "获取失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<RankingEntity>> call, Throwable t) {
                listRepoCallBack.onDataNotAvailable(405, "获取失败");
            }
        });

    }
}
