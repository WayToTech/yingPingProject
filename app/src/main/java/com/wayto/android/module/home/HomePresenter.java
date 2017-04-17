package com.wayto.android.module.home;

import com.wayto.android.module.home.data.HomeEntity;
import com.wayto.android.module.home.data.datasource.HomeDataSource;
import com.wayto.android.module.home.data.datasource.HomeRemoteRepo;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/12 22:36
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class HomePresenter implements HomeDataSource.HomeCallBack, HomeContract.Prenseter {

    private HomeRemoteRepo remoteRepo;
    private HomeContract.HomeView homeView;

    public HomePresenter(HomeContract.HomeView homeView) {
        this.remoteRepo = new HomeRemoteRepo();
        this.homeView = homeView;
    }

    @Override
    public void onHomeSuccess(HomeEntity entity) {
        homeView.onHomeSuccess(entity);
        homeView.onHomeEnd();
    }

    @Override
    public void onHomeFailure(int code, String msg) {
        homeView.onHomeFailure(code, msg);
        homeView.onHomeEnd();
    }

    @Override
    public void requestMember() {
        homeView.onHomeStart();
        remoteRepo.requestMember(this);
    }
}
