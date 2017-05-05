package com.jinhui.easy.base;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.jinhui.easy.common.dialog.ToastUtil;
import com.jinhui.easy.common.eventbus.NoticeEvent;
import com.jinhui.easy.common.handler.BaseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 基类Fragment
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:12
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class BaseFragment extends Fragment {

    /**
     * 消息处理Handler
     */
    protected BaseHandler mHandler;
    /*定义加载Dialog*/
    protected Dialog loadDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initHandler();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化Handler
     */
    private void initHandler() {
        mHandler = new BaseHandler(getActivity()) {
            @Override
            public void handleMessage(Message msg) {
                BaseFragment.this.dispatchMessage(msg);
            }
        };
    }

    /**
     * Handler事件分发处理
     *
     * @param msg
     */
    protected void dispatchMessage(Message msg) {
    }

    /**
     * Toast
     *
     * @param resid
     */
    public void showToast(int resid) {
        ToastUtil.showToast(getActivity(), resid);
    }

    /**
     * Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        ToastUtil.showToast(getActivity(), msg);
    }

    /**
     * 子线程回调
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSubThreedEvent(NoticeEvent event) {

    }

    /**
     * 主线程回调
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainThreedEvent(NoticeEvent event) {

    }
}
