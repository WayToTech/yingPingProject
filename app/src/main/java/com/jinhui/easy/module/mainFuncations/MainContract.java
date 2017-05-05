package com.jinhui.easy.module.mainFuncations;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:33
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface MainContract {

    interface MainView {

    }

    interface Presenter {
        /*获取七牛Token*/
        void reqQiNiuToken();
    }
}
