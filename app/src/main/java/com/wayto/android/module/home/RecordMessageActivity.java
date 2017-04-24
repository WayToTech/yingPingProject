package com.wayto.android.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.wayto.android.R;
import com.wayto.android.base.BaseActivity;
import com.wayto.android.common.dialog.DialogFactory;
import com.wayto.android.module.home.data.datasource.HomeDataSource;
import com.wayto.android.module.home.data.datasource.HomeRemoteRepo;
import com.wayto.android.module.pictureFuncation.SelectPictureActivity;
import com.wayto.android.module.pictureFuncation.data.PictureEntity;
import com.wayto.android.widget.AccessoryView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/24 20:15
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class RecordMessageActivity extends BaseActivity implements HomeDataSource.RecordMsgCallBack {

    @BindView(R.id.record_imageView)
    FrameLayout recordImageView;
    @BindView(R.id.editText)
    EditText editText;

    private AccessoryView accessoryView;

    private HomeRemoteRepo remoteRepo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_message);
        ButterKnife.bind(this);
        setToolbarTitle("消息报送");
        setToolbarRightText("提交");
        accessoryView = new AccessoryView(this);
        recordImageView.addView(accessoryView);

        remoteRepo = new HomeRemoteRepo();

    }

    @Override
    public void onClickToolbarRightLayout() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SelectPictureActivity.SELECT_PICTURE_RESULT_CODE) {
            List<PictureEntity> imgs = (List<PictureEntity>) data.getSerializableExtra("result");
            accessoryView.setImageListToEntity(imgs);
        }
    }

    @Override
    public void onRecordSuccess() {
        showToast("上报成功");
        finish();
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void onRecordFailure(String msg) {
        showToast(msg);
        DialogFactory.dimissDialog(loadDialog);
    }

    @OnClick(R.id.record_buff)
    public void onClick() {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            showToast("报送内容不能为空");
            return;
        }
        try {
            String path = accessoryView.getImageLists().get(0);
            remoteRepo.recordMsg(editText.getText().toString(), new File(path), this);
            loadDialog = DialogFactory.createLoadingDialog(this, "上报...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
