package com.jinhui.easy.module.accountFuncation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseActivity;
import com.jinhui.easy.common.dialog.DialogFactory;
import com.jinhui.easy.module.accountFuncation.data.soure.AccountRemoteRepo;
import com.jinhui.easy.module.pictureFuncation.SelectPictureActivity;
import com.jinhui.easy.module.pictureFuncation.data.PictureEntity;
import com.jinhui.easy.utils.ISkipActivityUtil;
import com.jinhui.easy.utils.IUtil;
import com.jinhui.easy.view.RoundedBitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录界面
 * 
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:42
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
*/
public class EditUserInfoActivity extends BaseActivity implements AccountContract.ModifyUearHeadView {

    @BindView(R.id.edit_user_head_iv)
    ImageView editUserHeadIv;

    private AccountPresenter accountPresenter;

    private String headPath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_edit_user_info);
        setToolbarTitle(R.string.title_edit_user_info);
        ButterKnife.bind(this);
        accountPresenter = new AccountPresenter(AccountRemoteRepo.newInstance(), this);
        initUI();
    }

    /**
     * 初始化UI
     */
    private void initUI() {
//        UserInfoEntity userInfoEntity = DataApplication.getInstance().getUserInfoEntity();
//        if (userInfoEntity == null) {
//            return;
//        }
//        Glide.with(this).load(IUtil.fitterUrl(DataApplication.getInstance().getUserInfoEntity().getIcon())).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(editUserHeadIv));
    }

    @OnClick({R.id.user_info_photo_edit, R.id.user_info_pwd_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_info_photo_edit:
                SelectPictureActivity.startIntent(this, 1);
                break;
            case R.id.user_info_pwd_edit:
                ISkipActivityUtil.startIntent(this,ModifyPasswordActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case SelectPictureActivity.SELECT_PICTURE_RESULT_CODE:
                List<PictureEntity> entities = (List<PictureEntity>) data.getExtras().getSerializable("result");
                if (entities == null || entities.size() == 0) {
                    return;
                }
                headPath = entities.get(0).getUrl();
                accountPresenter.modifyUserHead();
                break;
        }
    }

    @Override
    public void showModifyHearDialog() {
        loadDialog = DialogFactory.createLoadingDialog(this, R.string.dialog_msg_modify_head);
    }

    @Override
    public void modifyHeadFailure(String error) {
        showToast(error);
    }

    @Override
    public void modifyHeadSuccess(String headNewPath) {
        Glide.with(this).load(IUtil.fitterUrl(headNewPath)).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(editUserHeadIv));
        showToast(R.string.toast_modify_head_success);
    }

    @Override
    public String getHeadPath() {
        return headPath;
    }

    @Override
    public void dimissModifyHearDialog() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
