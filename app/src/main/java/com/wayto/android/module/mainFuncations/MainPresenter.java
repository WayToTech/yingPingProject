package com.wayto.android.module.mainFuncations;

import com.wayto.android.common.Constant;
import com.wayto.android.module.mainFuncations.data.soure.MainDataSource;
import com.wayto.android.module.mainFuncations.data.soure.MainRemoteRepo;
import com.wayto.android.utils.ISpfUtil;

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
