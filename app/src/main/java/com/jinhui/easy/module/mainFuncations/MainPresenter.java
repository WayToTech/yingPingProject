package com.jinhui.easy.module.mainFuncations;

import com.jinhui.easy.common.Constant;
import com.jinhui.easy.module.mainFuncations.data.soure.MainDataSource;
import com.jinhui.easy.module.mainFuncations.data.soure.MainRemoteRepo;
import com.jinhui.easy.utils.ISpfUtil;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:33
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class MainPresenter implements MainContract.Presenter, MainDataSource.RequestQiNiuTokenCallBack {

    private MainDataSource mRemoteRepo;
    private MainContract.MainView mainView;

    public MainPresenter(MainRemoteRepo remoteRepo, MainContract.MainView mainView) {
        this.mRemoteRepo = remoteRepo;
        this.mainView = mainView;
    }

    @Override
    public void reqQiNiuToken() {
        mRemoteRepo.reqQiNiuToken(this);
    }

    @Override
    public void getQiNiuTokenSuccess(String token) {
        ISpfUtil.setValue(Constant.QINIU_TOKEN_KEY, token);
    }
}
