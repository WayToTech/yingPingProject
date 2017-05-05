package com.jinhui.easy.module.accountFuncation;

import com.jinhui.easy.common.Constant;
import com.jinhui.easy.module.accountFuncation.data.ModifyHeadEntity;
import com.jinhui.easy.module.accountFuncation.data.ModifyPasswordEntity;
import com.jinhui.easy.module.accountFuncation.data.soure.AccountDataSoure;
import com.jinhui.easy.module.accountFuncation.data.soure.AccountRemoteRepo;
import com.jinhui.easy.utils.ISpfUtil;

/**
 * 登录Presenter
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:41
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class AccountPresenter implements AccountDataSoure.LoginCallBack, AccountDataSoure.ModifyHeadCallBack, AccountDataSoure.ModifyPasswordCallBack, AccountContract.Presenter {

    private AccountContract.LoginView loginView;
    private AccountContract.ModifyUearHeadView modifyUearHeadView;
    private AccountContract.ModifyUserPasswordView modifyUserPasswordView;
    private AccountDataSoure remoteRepo;

    public AccountPresenter(AccountRemoteRepo remoteRepo, AccountContract.LoginView loginView) {
        this.loginView = loginView;
        this.remoteRepo = remoteRepo;
    }

    public AccountPresenter(AccountRemoteRepo remoteRepo, AccountContract.ModifyUearHeadView modifyUearHeadView) {
        this.modifyUearHeadView = modifyUearHeadView;
        this.remoteRepo = remoteRepo;
    }

    public AccountPresenter(AccountRemoteRepo remoteRepo, AccountContract.ModifyUserPasswordView modifyUserPassword) {
        this.modifyUserPasswordView = modifyUserPassword;
        this.remoteRepo = remoteRepo;
    }

    @Override
    public void login() {
        loginView.showDialog();
        remoteRepo.login(loginView.getAccount(), loginView.getPassword(), this);
    }

    @Override
    public void modifyUserHead() {
        modifyUearHeadView.showModifyHearDialog();
        remoteRepo.modifyHead(modifyUearHeadView.getHeadPath(), this);
    }

    @Override
    public void modifyUserPwd() {
        modifyUserPasswordView.showModifyPwdDialog();
        remoteRepo.modifyPassword(this);
    }

    @Override
    public void onLoginSuccess(String session) {
//        if (entity != null) {
//            /*数据本地化*/
//            ISpfUtil.setValue(Constant.ACCESS_TOKEN_KEY, entity.getAccess_token());
//            ISpfUtil.setValue(Constant.ACCOUNT_KEY, loginView.getAccount());
//            ISpfUtil.setValue(Constant.PSSWORD_KEY, loginView.getPassword());
//            ISpfUtil.setValue(Constant.USERINFO_KEY, new Gson().toJson(entity));
//        }
        ISpfUtil.setValue(Constant.ACCESS_TOKEN_KEY,session);
        loginView.loginSuccess();
        loginView.dismissDialog();
    }

    @Override
    public void onLoginFailure(String error) {
        loginView.loginFailure(error);
        loginView.dismissDialog();
    }

    @Override
    public void onModifyHeadSuccess(final String headNewPath) {
//        modifyUearHeadView.getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                UserInfoEntity entity = DataApplication.getInstance().getUserInfoEntity();
//                entity.setIcon(headNewPath);
//                ISpfUtil.setValue(Constant.USERINFO_KEY, new Gson().toJson(entity));
//                modifyUearHeadView.modifyHeadSuccess(headNewPath);
//                modifyUearHeadView.dimissModifyHearDialog();
//            }
//        });
    }

    @Override
    public void onModifyHeadFailure(final String error) {
        modifyUearHeadView.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                modifyUearHeadView.modifyHeadFailure(error);
                modifyUearHeadView.dimissModifyHearDialog();
            }
        });
    }

    @Override
    public void onModifyPasswordSuccess() {
        modifyUserPasswordView.modifyPwdSuccess();
        modifyUserPasswordView.dimissModifyPwdDialog();
    }

    @Override
    public void onModifyPasswordFailure(String error) {
        modifyUserPasswordView.modifyPwdFailure(error);
        modifyUserPasswordView.dimissModifyPwdDialog();
    }

    @Override
    public ModifyPasswordEntity getPasswordBody() {
        ModifyPasswordEntity entity = new ModifyPasswordEntity();
        entity.setNewPassword(modifyUserPasswordView.getNewPassword());
        entity.setOldPassword(modifyUserPasswordView.getOldPassword());
        return entity;
    }

    @Override
    public ModifyHeadEntity getHeadBody(String path) {
        ModifyHeadEntity entity = new ModifyHeadEntity();
        entity.setIcon(path);
        return entity;
    }

    /**
     * 取消请求
     */
    public void cancelRequest() {
        remoteRepo.cancelRequest();
    }
}
