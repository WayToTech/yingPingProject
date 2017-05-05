package com.jinhui.easy.module.home;

import com.jinhui.easy.module.home.data.HomeEntity;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/12 22:36
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface HomeContract {

    interface HomeView {
        void onHomeStart();

        void onHomeEnd();

        void onHomeSuccess(HomeEntity entity);

        void onHomeFailure(int code, String msg);
    }

    interface Prenseter{
        void requestMember();
    }
}
