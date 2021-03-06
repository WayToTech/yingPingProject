package com.jinhui.easy.module.accountFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseActivity;
import com.jinhui.easy.utils.ISkipActivityUtil;
import com.jinhui.easy.widget.FormWriteView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:40
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.UserInfo_HeadView_imageView)
    ImageView UserInfoHeadViewImageView;
    @BindView(R.id.UserInfo_account_textView)
    FormWriteView UserInfoAccountTextView;
    @BindView(R.id.UserInfo_depart_textView)
    FormWriteView UserInfoDepartTextView;
    @BindView(R.id.UserInfo_company_textView)
    FormWriteView UserInfoCompanyTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        setToolbarRightText(R.string.title_user_info_edit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUI();
    }

    private void initUI() {
//        UserInfoEntity userInfoEntity = DataApplication.getInstance().getUserInfoEntity();
//        if (userInfoEntity == null) {
//            return;
//        }
//        Glide.with(this).load(IUtil.fitterUrl(DataApplication.getInstance().getUserInfoEntity().getIcon())).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(UserInfoHeadViewImageView));
//        UserInfoAccountTextView.setContentText(ISpfUtil.getValue(Constant.ACCOUNT_KEY, "").toString()).setNonEditable();
//        UserInfoDepartTextView.setContentText(userInfoEntity.getDept()).setNonEditable();
//        UserInfoCompanyTextView.setContentText(userInfoEntity.getUnit()).setNonEditable();
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        ISkipActivityUtil.startIntent(this,EditUserInfoActivity.class);
    }
}
