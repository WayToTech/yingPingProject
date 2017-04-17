package com.wayto.android.module.accountFuncation;

import android.app.Activity;

import com.wayto.android.module.accountFuncation.data.UserInfoEntity;

/**
 * 定义协议
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:41
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public interface AccountContract {
    /**
     * 登录
     */
    interface LoginView {
        void showDialog();

        void dismissDialog();

        void loginSuccess();

        void loginFailure(String error);

        String getAccount();

        String getPassword();
    }

    /**
     * 修改用户头像
     */
    interface ModifyUearHeadView {
        void showModifyHearDialog();

        void dimissModifyHearDialog();

        String getHeadPath();

        Activity getActivity();

        void modifyHeadSuccess(String headNewPath);

        void modifyHeadFailure(String error);
    }

    /**
     * 修改密码
     */
    interface ModifyUserPasswordView {
        void showModifyPwdDialog();

        void dimissModifyPwdDialog();

        void modifyPwdSuccess();

        void modifyPwdFailure(String error);

        String getNewPassword();

        String getOldPassword();
    }

    interface Presenter {
        void login();

        void modifyUserHead();

        void modifyUserPwd();
    }
}
