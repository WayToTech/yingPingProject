package com.wayto.android.module.notice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.wayto.android.R;
import com.wayto.android.base.BaseActivity;
import com.wayto.android.module.notice.fragment.AboutFragment;
import com.wayto.android.module.notice.fragment.MessageSetingFragment;
import com.wayto.android.module.notice.fragment.TrackSetingFragment;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:36
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class SetingInfoActivity extends BaseActivity {

    public static final String HEAD_TITLE_FLAG = "title_flag";
    public static final String SHOW_FRAGMENT_FLAG = "show_flag";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_seting_info);
        String fromFlag = getIntent().getStringExtra(SHOW_FRAGMENT_FLAG);
        initUI(fromFlag);
    }

    /**
     * 初始化UI
     *
     * @param flag
     */
    private void initUI(String flag) {
        if (TextUtils.isEmpty(flag)) {
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (flag.equals(MessageSetingFragment.FRAGMENT_FLAG)) {
            setToolbarTitle(R.string.set_msg_info);
            transaction.replace(R.id.seting_info_container, MessageSetingFragment.newInstance());
        } else if (flag.equals(TrackSetingFragment.FRAGMENT_FLAG)) {
            setToolbarTitle(R.string.set_track_info);
            transaction.replace(R.id.seting_info_container, TrackSetingFragment.newInstance());
        } else if (flag.equals(AboutFragment.FRAGMENT_FLAG)) {
            setToolbarTitle(R.string.set_about);
            transaction.replace(R.id.seting_info_container, AboutFragment.newInstance());
        }
        transaction.commit();
    }
}
