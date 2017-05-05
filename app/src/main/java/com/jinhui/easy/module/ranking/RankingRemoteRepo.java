package com.jinhui.easy.module.ranking;

import com.jinhui.easy.common.Constant;
import com.jinhui.easy.entity.ResponseModel;
import com.jinhui.easy.module.ranking.data.RankingEntity;
import com.jinhui.easy.module.ranking.data.RepoCallBack;
import com.jinhui.easy.utils.ISpfUtil;
import com.jinhui.easy.vendor.retrofit.RetrofitManager;

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
