package com.wayto.android.module.vote.data.source;

import com.wayto.android.common.Constant;
import com.wayto.android.entity.ResponseModel;
import com.wayto.android.module.vote.data.VoteEntity;
import com.wayto.android.utils.ISpfUtil;
import com.wayto.android.vendor.retrofit.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/15 14:24
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class VoteRemoteRepo implements VoteDataSource {

    @Override
    public void requestVoteList(final VoteCallBack callBack) {
        String url = "/vote/appVoteList?sessionid=" + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString();
        Call<ResponseModel<List<VoteEntity>>> call = RetrofitManager.getInstance().getService().getVoteList(url);
        call.enqueue(new Callback<ResponseModel<List<VoteEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<VoteEntity>>> call, Response<ResponseModel<List<VoteEntity>>> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        callBack.onVoteSuccess(response.body().getData());
                    } else {
                        callBack.onVoteFailure(response.body().getCode(), response.body().getMessage());
                    }
                } else {
                    callBack.onVoteFailure(response.code(), "获取失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<VoteEntity>>> call, Throwable t) {
                callBack.onVoteFailure(405, "获取失败");
            }
        });
    }

    @Override
    public void carridVote(int id, int voteId, final CarridVoteCallBack callBack) {
        callBack.onCarridVoteStart();
        String url = "vote/appMemberVote?id=" + id + "&voteId=" + voteId + "?sessionid=" + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString();
        Call<ResponseModel> call = RetrofitManager.getInstance().getService().arridVote(url);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        callBack.onCarridVoteSuccess();
                        callBack.onCarridVoteEnd();
                    } else {
                        callBack.onCarridVoteFailure();
                        callBack.onCarridVoteEnd();
                    }
                } else {
                    callBack.onCarridVoteFailure();
                    callBack.onCarridVoteEnd();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                callBack.onCarridVoteFailure();
                callBack.onCarridVoteEnd();
            }
        });
    }
}
