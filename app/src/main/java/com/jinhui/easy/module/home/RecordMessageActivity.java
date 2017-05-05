package com.jinhui.easy.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseActivity;
import com.jinhui.easy.common.dialog.DialogFactory;
import com.jinhui.easy.module.home.data.datasource.HomeDataSource;
import com.jinhui.easy.module.home.data.datasource.HomeRemoteRepo;
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
    @BindView(R.id.editText_title)
    EditText editTextTitle;

    private AccessoryView accessoryView;

    private HomeRemoteRepo remoteRepo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_message);
        ButterKnife.bind(this);
        setToolbarTitle("消息报送");
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
                                accessoryView.setImageList(path);
                                DialogFactory.dimissDialog(loadDialog);
                            }
                        });
                    }
                }).start();
            }
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
        if (TextUtils.isEmpty(editTextTitle.getText().toString())) {
            showToast("标题不能为空");
            return;
        }
        if (TextUtils.isEmpty(editText.getText().toString())) {
            showToast("内容不能为空");
            return;
        }
        List<String> im = accessoryView.getImageLists();
        if (im == null || im.size() <= 0) {
            showToast("图片不能为空");
            return;
        }
        final String path = im.get(0);
        DialogFactory.showMsgDialog(this, "提交提示", "确定报送?", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remoteRepo.recordMsg(editTextTitle.getText().toString(), editText.getText().toString(), new File(path), RecordMessageActivity.this);
                loadDialog = DialogFactory.createLoadingDialog(RecordMessageActivity.this, "上报...");
            }
        });
    }
}
