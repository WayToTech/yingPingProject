package com.jinhui.easy.module.notice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseActivity;
import com.jinhui.easy.common.dialog.DialogFactory;
import com.jinhui.easy.module.notice.data.NoticeDetailsEntity;
import com.jinhui.easy.module.notice.data.source.NoticeDataSource;
import com.jinhui.easy.module.notice.data.source.NoticeRemoteRepo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公告详情
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 15:18
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class NoticeDetailsActivity extends BaseActivity implements NoticeDataSource.NoticeDetailsCallBack {

    @BindView(R.id.NoticeDetails_titls)
    TextView NoticeDetailsTitls;
    @BindView(R.id.NoticeDetails_content)
    TextView NoticeDetailsContent;
    @BindView(R.id.NoticeDetails_releaseTime)
    TextView NoticeDetailsReleaseTime;

    private NoticeRemoteRepo remoteRepo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);
        ButterKnife.bind(this);
        setToolbarTitle("公告详情");
        remoteRepo=new NoticeRemoteRepo();
        remoteRepo.requestNoticeDetailsData(getIntent().getStringExtra("id"),this);
    }

    @Override
    public void onNoticeDetailsStart() {
        loadDialog= DialogFactory.createLoadingDialog(this,"加载...");
    }

    @Override
    public void onNoticeDetailsEnd() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void onNoticeDetailsSuccess(NoticeDetailsEntity entity) {
        if (entity!=null){
            NoticeDetailsTitls.setText(entity.getTitle());
            NoticeDetailsContent.setText(entity.getContent());
            NoticeDetailsReleaseTime.setText("发布时间:"+entity.getReleasetime());
        }
    }

    @Override
    public void onNoticeDetailsFailure(int code, String msg) {
        showToast(msg);
    }
}
