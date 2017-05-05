package com.jinhui.easy.module.comment.data.source;

import com.jinhui.easy.common.Constant;
import com.jinhui.easy.entity.ResponseModel;
import com.jinhui.easy.module.comment.data.TaskDetailsEntity;
import com.jinhui.easy.module.comment.data.TaskEndEntity;
import com.jinhui.easy.module.comment.data.TaskEntity;
import com.jinhui.easy.module.ranking.data.RepoCallBack;
import com.jinhui.easy.utils.ISpfUtil;
import com.jinhui.easy.vendor.retrofit.RetrofitManager;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
    public void requestTaskData(String url, final CommentCallBack commentCallBack) {
        url = url + "?sessionid=" + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString();
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
        String url = "task/detailTaskById?id=" + id + "&sessionid=" + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString();
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

    @Override
    public void recordTask(int taskId, File file, final RecordTaskCallBack callBack) {
        String url = "task/appUploadTaskImg?sessionid=" + ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString();
        Map<String, RequestBody> map = new HashMap<>();
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        map.put("file \"; Filename=\"" + file.getName(), body);
        map.put("Filename", RequestBody.create(MediaType.parse("text/plain"), file.getName()));
        map.put("taskid", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(taskId)));
        Call<ResponseModel> call = RetrofitManager.getInstance().getService().recordTask(url, map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.code() == 200) {
                    if (response.body().getCode() == 200) {
                        callBack.onRecordSuccess();
                    } else {
                        callBack.onRecordFailure("上传失败");
                    }
                } else {
                    callBack.onRecordFailure("上传失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                callBack.onRecordFailure("上传失败");
            }
        });
    }

    public void getCommentDetails(long taskId, final RepoCallBack<TaskEndEntity> callBack){
        String url="task/detailTaskEndById?id="+taskId+"&sessionid="+ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY,"").toString();
        Call<ResponseModel<TaskEndEntity>> call=RetrofitManager.getInstance().getService().getdetailTaskEndById(url);
        call.enqueue(new Callback<ResponseModel<TaskEndEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<TaskEndEntity>> call, Response<ResponseModel<TaskEndEntity>> response) {
                if (response.code()==200){
                    if (response.body().getCode()==200){
                        callBack.onDataAvailable(response.body().getData());
                    }else {
                        callBack.onDataNotAvailable(response.body().getCode(),response.body().getMessage());
                    }
                }else {
                    callBack.onDataNotAvailable(405,"获取失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<TaskEndEntity>> call, Throwable t) {
                callBack.onDataNotAvailable(405,"获取失败");
            }
        });
    }
}
