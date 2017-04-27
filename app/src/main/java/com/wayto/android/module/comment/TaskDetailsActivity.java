package com.wayto.android.module.comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wayto.android.BuildConfig;
import com.wayto.android.R;
import com.wayto.android.base.BaseActivity;
import com.wayto.android.common.dialog.DialogFactory;
import com.wayto.android.module.comment.data.TaskEndEntity;
import com.wayto.android.module.comment.data.source.CommentRemoteRepo;
import com.wayto.android.module.pictureFuncation.SelectPictureActivity;
import com.wayto.android.module.pictureFuncation.data.PictureEntity;
import com.wayto.android.module.ranking.data.RepoCallBack;
import com.wayto.android.utils.BitmapUtil;
import com.wayto.android.utils.IFileUtils;
import com.wayto.android.widget.AccessoryView;

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
public class TaskDetailsActivity extends BaseActivity implements RepoCallBack<TaskEndEntity> {


    @BindView(R.id.taskEnd_title)
    TextView taskEndTitle;
    @BindView(R.id.taskIntegral)
    TextView taskIntegral;
    @BindView(R.id.taskEnd_ex_time)
    TextView taskEndExTime;
    @BindView(R.id.taskEnd_images)
    FrameLayout taskEndImages;
    private AccessoryView accessoryViewl;

    private int taskId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_end);
        ButterKnife.bind(this);
        setToolbarTitle("任务详情");
        taskId = getIntent().getIntExtra("id", -1);
        accessoryViewl = new AccessoryView(this);
        taskEndImages.addView(accessoryViewl);
        loadDialog = DialogFactory.createLoadingDialog(this, "获取...");
        new CommentRemoteRepo().getCommentDetails(taskId, this);
    }

    @Override
    public void onDataAvailable(TaskEndEntity data) {
        DialogFactory.dimissDialog(loadDialog);
        if (data == null) {
            finish();
            showToast("数据为空");
        } else {
            taskEndTitle.setText(data.getTitle());
            taskIntegral.setText("获得积分:"+data.getIntegral());
            taskEndExTime.setText("执行时间:"+data.getExecutiontime());
            accessoryViewl.setShowImage(BuildConfig.DOMAI + data.getTaskphoto());
        }
    }

    @Override
    public void onDataNotAvailable(int code, String msg) {
        DialogFactory.dimissDialog(loadDialog);
        showToast(msg);
        if (code == 404) {
            finish();
        }
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
}
