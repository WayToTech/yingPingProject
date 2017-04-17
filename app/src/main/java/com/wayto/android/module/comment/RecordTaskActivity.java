package com.wayto.android.module.comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wayto.android.R;
import com.wayto.android.base.BaseActivity;
import com.wayto.android.common.dialog.DialogFactory;
import com.wayto.android.module.comment.data.TaskDetailsEntity;
import com.wayto.android.module.comment.data.source.CommentDataSource;
import com.wayto.android.module.comment.data.source.CommentRemoteRepo;
import com.wayto.android.module.pictureFuncation.SelectPictureActivity;
import com.wayto.android.module.pictureFuncation.data.PictureEntity;
import com.wayto.android.widget.AccessoryView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 任务执行
 * <p>
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 20:22
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class RecordTaskActivity extends BaseActivity implements CommentDataSource.TaskDetailsCallBack {

    @BindView(R.id.taskRecord_content)
    TextView taskRecordContent;
    @BindView(R.id.taskRecord_Requirement)
    TextView taskRecordRequirement;
    @BindView(R.id.taskRecord_images)
    FrameLayout taskRecordImages;
    @BindView(R.id.taskRecord_title)
    TextView taskRecordTitle;

    private AccessoryView accessoryViewl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_task);
        ButterKnife.bind(this);
        setToolbarTitle("任务详情");
        new CommentRemoteRepo().requestTaskDetails(getIntent().getIntExtra("id",-1),this);
        accessoryViewl=new AccessoryView(this);
        taskRecordImages.addView(accessoryViewl);
    }

    @Override
    public void onTaskStart() {
        loadDialog = DialogFactory.createLoadingDialog(this);
    }

    @Override
    public void onTaskEnd() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void onTaskSuccess(TaskDetailsEntity entity) {
        if (entity != null) {
            taskRecordContent.setText(entity.getContent());
            taskRecordTitle.setText(entity.getTitle());
            taskRecordRequirement.setText(entity.getRequirement());
        }
    }

    @Override
    public void onTaskFailure(int code, String msg) {
        showToast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== SelectPictureActivity.SELECT_PICTURE_RESULT_CODE){
            ArrayList<PictureEntity> list=(ArrayList<PictureEntity>) data.getSerializableExtra("result");
            accessoryViewl.setImageListToEntity(list);
        }
    }
}
