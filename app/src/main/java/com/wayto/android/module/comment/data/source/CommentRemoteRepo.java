package com.wayto.android.module.comment.data.source;

import com.wayto.android.entity.ResponseModel;
import com.wayto.android.module.comment.data.TaskDetailsEntity;
import com.wayto.android.module.comment.data.TaskEntity;
import com.wayto.android.vendor.retrofit.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 14:32
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class CommentRemoteRepo implements CommentDataSource {

    Call<ResponseModel<List<TaskEntity>>> call;

    @Override
    public void requestTaskData(String url,final CommentCallBack commentCallBack) {
        call = RetrofitManager.getInstance().getService().getCommentList(url);
        call.enqueue(new Callback<ResponseModel<List<TaskEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<TaskEntity>>> call, Response<ResponseModel<List<TaskEntity>>> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        commentCallBack.onCommentSuccess(response.body().getData());
                    } else {
                        commentCallBack.onCommentFailure(response.body().getCode(), response.body().getMessage());
                    }
                } else {
                    commentCallBack.onCommentFailure(response.code(), "请求失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<TaskEntity>>> call, Throwable t) {
                commentCallBack.onCommentFailure(405, "请求失败");
            }
        });
    }

    @Override
    public void requestTaskDetails(int id, final TaskDetailsCallBack callBack) {
        callBack.onTaskStart();
        String url = "task/detailTaskById?id=" + id;
        Call<ResponseModel<TaskDetailsEntity>> call = RetrofitManager.getInstance().getService().getTaskDetails(url);
        call.enqueue(new Callback<ResponseModel<TaskDetailsEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<TaskDetailsEntity>> call, Response<ResponseModel<TaskDetailsEntity>> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        callBack.onTaskSuccess(response.body().getData());
                        callBack.onTaskEnd();
                    } else {
                        callBack.onTaskFailure(response.code(), response.body().getMessage());
                        callBack.onTaskEnd();
                    }
                } else {
                    callBack.onTaskFailure(response.code(), "请求失败");
                    callBack.onTaskEnd();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<TaskDetailsEntity>> call, Throwable t) {
                callBack.onTaskFailure(405, "请求失败");
                callBack.onTaskEnd();
            }
        });
    }
}
