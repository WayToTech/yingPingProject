package com.jinhui.easy.module.accountFuncation.data.soure;

import com.jinhui.easy.module.accountFuncation.data.ModifyHeadEntity;
import com.jinhui.easy.module.accountFuncation.data.ModifyPasswordEntity;
import com.jinhui.easy.base.BaseDataSourse;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:11
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public interface AccountDataSoure extends BaseDataSourse {

    interface LoginCallBack {

        void onLoginSuccess(String sessionId);

        void onLoginFailure(String error);

    }

    interface ModifyHeadCallBack {
        void onModifyHeadSuccess(String headNewPath);

        void onModifyHeadFailure(String error);

        ModifyHeadEntity getHeadBody(String path);
    }

    interface ModifyPasswordCallBack {
        void onModifyPasswordSuccess();

        void onModifyPasswordFailure(String error);

        ModifyPasswordEntity getPasswordBody();
    }

    void login(String account, String password, LoginCallBack callBack);

    void modifyHead(String path, ModifyHeadCallBack callBack);

    void modifyPassword(ModifyPasswordCallBack callBack);
}
