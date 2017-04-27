package com.wayto.android.module.conference;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.wayto.android.R;
import com.wayto.android.base.BaseActivity;
import com.wayto.android.common.dialog.DialogFactory;
import com.wayto.android.module.conference.data.ConferenceDetailsEntity;
import com.wayto.android.module.conference.data.dataSource.ConferenceDatasource;
import com.wayto.android.module.conference.data.dataSource.ConferenceRemoteRepo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 15:46
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class ConferenceDetailsActivity extends BaseActivity implements ConferenceDatasource.ConferenceDetailsCallBack {

    @BindView(R.id.ConferenceDetails_titls)
    TextView ConferenceDetailsTitls;
    @BindView(R.id.ConferenceDetails_content)
    TextView ConferenceDetailsContent;
    @BindView(R.id.ConferenceDetails_releaseTime)
    TextView ConferenceDetailsReleaseTime;
    @BindView(R.id.ConferenceDetails_address)
    TextView ConferenceDetailsAddress;

    private ConferenceRemoteRepo remoteRepo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_comference_details);
        ButterKnife.bind(this);
        setToolbarTitle("会务详情");
        remoteRepo = new ConferenceRemoteRepo();
        remoteRepo.requestMeetingDetails(getIntent().getIntExtra("id", 0), this);
    }

    @Override
    public void onConferenceDetailsStart() {
        loadDialog = DialogFactory.createLoadingDialog(this);
    }

    @Override
    public void onConferenceDetailsEnd() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void onConferenceDetailsSuccess(ConferenceDetailsEntity entity) {
        if (entity != null) {
            ConferenceDetailsTitls.setText(entity.getTitle());
            ConferenceDetailsContent.setText(entity.getContent());
            ConferenceDetailsAddress.setText("会务地址:"+entity.getAddress());
            ConferenceDetailsReleaseTime.setText("会务时间:" + entity.getReleasetime());
        }
    }

    @Override
    public void onConferenceDetailsFailure(int code, String msg) {
        showToast(msg);
    }
}
