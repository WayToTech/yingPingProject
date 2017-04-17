package com.wayto.android.module.home.data.datasource;

import com.wayto.android.base.BaseDataSourse;
import com.wayto.android.module.home.data.HomeEntity;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/12 22:29
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface HomeDataSource extends BaseDataSourse {

    interface HomeCallBack{
        void onHomeSuccess(HomeEntity entity);
        void onHomeFailure(int code,String msg);
    }

    void requestMember(HomeCallBack callBack);
}
