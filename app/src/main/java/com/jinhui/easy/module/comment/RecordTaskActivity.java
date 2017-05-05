package com.jinhui.easy.module.comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseActivity;
import com.jinhui.easy.common.dialog.DialogFactory;
import com.jinhui.easy.module.comment.data.TaskDetailsEntity;
import com.jinhui.easy.module.comment.data.source.CommentDataSource;
import com.jinhui.easy.module.comment.data.source.CommentRemoteRepo;
import com.jinhui.easy.module.pictureFuncation.SelectPictureActivity;
import com.jinhui.easy.module.pictureFuncation.data.PictureEntity;
import com.jinhui.easy.utils.BitmapUtil;
import com.jinhui.easy.utils.IFileUtils;
import com.jinhui.easy.widget.AccessoryView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private int taskId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_task);
        ButterKnife.bind(this);
        setToolbarTitle("任务详情");
        taskId = getIntent().getIntExtra("id", -1);
        new CommentRemoteRepo().requestTaskDetails(taskId, this);
        accessoryViewl = new AccessoryView(this);
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
        if (resultCode == SelectPictureActivity.SELECT_PICTURE_RESULT_CODE) {
            loadDialog = DialogFactory.createLoadingDialog(this, "图片处理...");
            final List<PictureEntity> imgs = (List<PictureEntity>) data.getSerializableExtra("result");
            if (imgs != null && imgs.size() > 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String path = BitmapUtil.compressBitmap(imgs.get(0).getUrl(), IFileUtils.getImageCatchDir(), "");
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                accessoryViewl.setImageList(path);
                                DialogFactory.dimissDialog(loadDialog);
                            }
                        });
                    }
                }).start();
            }
        }
    }

    @OnClick(R.id.button_submit)
    public void onClick() {
        List<String> im = accessoryViewl.getImageLists();
        if (im == null || im.size() <= 0) {
            showToast("图片不能为空");
            return;
        }
        loadDialog = DialogFactory.createLoadingDialog(this, "提交...");
        new CommentRemoteRepo().recordTask(taskId, new File(im.get(0)), new CommentDataSource.RecordTaskCallBack() {
            @Override
            public void onRecordSuccess() {
                showToast("提交成功");
                finish();
                DialogFactory.dimissDialog(loadDialog);
            }

            @Override
            public void onRecordFailure(String msg) {
                showToast("提交失败");
                DialogFactory.dimissDialog(loadDialog);
            }
        });
    }
}
