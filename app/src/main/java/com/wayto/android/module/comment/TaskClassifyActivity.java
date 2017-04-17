package com.wayto.android.module.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.wayto.android.R;
import com.wayto.android.base.BaseActivity;
import com.wayto.android.widget.SwitchButtonTwo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 21:52
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class TaskClassifyActivity extends BaseActivity {

    @BindView(R.id.TaskClassify_switchButton)
    SwitchButtonTwo TaskClassifySwitchButton;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_classify);
        ButterKnife.bind(this);
        setToolbarVisibility(View.GONE);

        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.TaskClassify_FrameLayout,new UnCompleteFragment());
        transaction.commitNowAllowingStateLoss();

        TaskClassifySwitchButton.setListenter(new SwitchButtonTwo.SwitchButtonOnClickListenter() {
            @Override
            public void onSwitchOnClick(int flag) {
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                if (flag==SwitchButtonTwo.ONCLICK_LEFT){
                    ft.replace(R.id.TaskClassify_FrameLayout,new UnCompleteFragment());
                }else {
                    ft.replace(R.id.TaskClassify_FrameLayout,new CompleteFragment());
                }
                ft.commitNowAllowingStateLoss();
            }
        });
    }

    @OnClick(R.id.TaskClassify_left)
    public void onClick() {
        finish();
    }
}
