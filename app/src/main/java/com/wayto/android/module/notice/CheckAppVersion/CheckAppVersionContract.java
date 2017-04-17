package com.wayto.android.module.notice.CheckAppVersion;

import android.app.Activity;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:37
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface CheckAppVersionContract {

    interface View {
        void showRequestFail();

        void showIsLatestVersion();

        void showCheckingDialog();

        void dismissCheckingDialog();

        Activity getActivity();
    }

    interface Presenter {
        void checkAppVersion();
    }
}
