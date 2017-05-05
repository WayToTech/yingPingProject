package com.jinhui.easy.module.accountFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseActivity;
import com.jinhui.easy.common.Constant;
import com.jinhui.easy.common.dialog.DialogFactory;
import com.jinhui.easy.module.accountFuncation.data.soure.AccountRemoteRepo;
import com.jinhui.easy.utils.ISpfUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 重置密码
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:41
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class ModifyPasswordActivity extends BaseActivity implements AccountContract.ModifyUserPasswordView {

    @BindView(R.id.pwd_edit_old_edit)
    EditText pwdEditOldEdit;
    @BindView(R.id.pwd_edit_new_edit)
    EditText pwdEditNewEdit;
    @BindView(R.id.pwd_edit_comfir_edit)
    EditText comfirPwdEditText;

    private AccountPresenter accountPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_pwd_edit);
        setToolbarTitle(R.string.title_edit_user_pwd);
        setToolbarRightText(R.string.title_submit);
        ButterKnife.bind(this);
        accountPresenter = new AccountPresenter(AccountRemoteRepo.newInstance(), this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountPresenter.cancelRequest();
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        if (TextUtils.isEmpty(pwdEditOldEdit.getText().toString())) {
            showToast(R.string.toast_pwd_old_null);
            return;
        }
        if (TextUtils.isEmpty(pwdEditNewEdit.getText().toString())) {
            showToast(R.string.toast_pwd_new_null);
            return;
        }
        if (TextUtils.isEmpty(comfirPwdEditText.getText().toString())) {
            showToast(R.string.toast_comfir_pwd_null);
            return;
        }
        if (!pwdEditOldEdit.getText().toString().trim().equals(ISpfUtil.getValue(Constant.PSSWORD_KEY, "").toString().trim())) {
            showToast(R.string.toast_pwd_not_dis);
            return;
        }
        if (!pwdEditNewEdit.getText().toString().trim().equals(comfirPwdEditText.getText().toString().trim())) {
            showToast(R.string.toast_new_comfir_not_dif);
            return;
        }
        accountPresenter.modifyUserPwd();
    }

    @Override
    public void showModifyPwdDialog() {
        loadDialog = DialogFactory.createLoadingDialog(this, R.string.dialog_msg_modify_pwd);
    }

    @Override
    public void dimissModifyPwdDialog() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void modifyPwdSuccess() {
        showToast(R.string.toast_modify_pwd_success);
    }

    @Override
    public void modifyPwdFailure(String error) {
        showToast(error);
    }

    @Override
    public String getNewPassword() {
        return pwdEditNewEdit.getText().toString();
    }

    @Override
    public String getOldPassword() {
        return pwdEditOldEdit.getText().toString();
    }
}
