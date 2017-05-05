package com.jinhui.easy.module.mainFuncations.data.soure;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:38
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface MainDataSource {
    /**
     * 七牛Token
     */
    interface RequestQiNiuTokenCallBack {
        void getQiNiuTokenSuccess(String token);
    }

    void reqQiNiuToken(RequestQiNiuTokenCallBack callBack);
}
