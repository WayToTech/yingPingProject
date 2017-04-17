package com.wayto.android.module.comment.data.source;

import com.wayto.android.module.comment.data.TaskDetailsEntity;
import com.wayto.android.module.comment.data.TaskEntity;

import java.util.List;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 14:31
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface CommentDataSource {

    interface CommentCallBack{
        void onCommentSuccess(List<TaskEntity> entities);
        void onCommentFailure(int code,String msg);
    }

    interface TaskDetailsCallBack{
        void onTaskStart();
        void onTaskEnd();
        void onTaskSuccess(TaskDetailsEntity entity);
        void onTaskFailure(int code,String msg);
    }

    void requestTaskData(String url,CommentCallBack commentCallBack);

    void requestTaskDetails(int id,TaskDetailsCallBack callBack);
}
