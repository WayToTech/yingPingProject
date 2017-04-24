package com.wayto.android.module.accountFuncation.data.soure;

import com.wayto.android.R;
import com.wayto.android.base.DataApplication;
import com.wayto.android.common.Constant;
import com.wayto.android.entity.ResponseModel;
import com.wayto.android.module.accountFuncation.data.ModifyHeadEntity;
import com.wayto.android.module.accountFuncation.data.UserInfoEntity;
import com.wayto.android.utils.INetWorkUtil;
import com.wayto.android.utils.ISystemUtil;
import com.wayto.android.utils.IUtil;
import com.wayto.android.vendor.retrofit.RetrofitManager;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录业务请求
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:11
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class AccountRemoteRepo implements AccountDataSoure {
    private final String TAG = getClass().getSimpleName();

    private static AccountRemoteRepo remoteRepo;
    private Call<ResponseModel<String>> LoginCall;
    private Call<ResponseModel> modifyHeadCall;
    private Call<ResponseModel> modifyPwdCall;

    public static AccountRemoteRepo newInstance() {
        if (remoteRepo == null) {
            remoteRepo = new AccountRemoteRepo();
        }
        return remoteRepo;
    }

    @Override
    public void login(String account, String password, final LoginCallBack callBack) {
        if (!INetWorkUtil.isNetworkAvailable(DataApplication.getInstance())) {
            callBack.onLoginFailure(IUtil.getStrToRes(R.string.invalid_network));
            return;
        }
        String body = "login/appCheckLogin?userName=" + account + "&password=" + password;
        LoginCall = RetrofitManager.getInstance().getService().loginRepo(body);
        LoginCall.enqueue(new Callback<ResponseModel<String>>() {
            @Override
            public void onResponse(Call<ResponseModel<String>> call, Response<ResponseModel<String>> response) {
                if (response.code() == Constant.HTTP_SUCESS_CODE) {
                    if (response.body().getCode() == 200) {
                        callBack.onLoginSuccess(response.body().getData());
                    } else {
                        callBack.onLoginFailure(response.body().getMessage());
                    }
                } else {
                    callBack.onLoginFailure(IUtil.getStrToRes(R.string.login_failure));
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<String>> call, Throwable t) {
                callBack.onLoginFailure(IUtil.getStrToRes(R.string.login_failure));
            }
        });
    }

    @Override
    public void modifyHead(final String filePath, final ModifyHeadCallBack callBack) {
        if (!INetWorkUtil.isNetworkAvailable(DataApplication.getInstance())) {
            callBack.onModifyHeadFailure(IUtil.getStrToRes(R.string.invalid_network));
            return;
        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String cachePath = BitmapUtil.compressBitmap(filePath, IFileUtils.getImageCatchDir());
//                if (!TextUtils.isEmpty(cachePath)) {
//                    QiNiuImageUploadManager.uploadImage(ISpfUtil.getValue(Constant.QINIU_TOKEN_KEY, "").toString(), cachePath, new UploadCallBackListener() {
//                        @Override
//                        public void onUploadStart() {
//
//                        }
//
//                        @Override
//                        public void onUploadEnd() {
//
//                        }
//
//                        @Override
//                        public void onProgess(double percent) {
//
//                        }
//
//                        @Override
//                        public void onUploadComplete(List<String> path) {
//                            if (path != null && path.size() > 0) {
//                                IFileUtils.delete(filePath);
//                                updateUserInfo(callBack.getHeadBody(path.get(0)), callBack);
//                            }
//                        }
//
//                        @Override
//                        public void onUploadFailure() {
//                            callBack.onModifyHeadFailure(IUtil.getStrToRes(R.string.toast_modify_head_failure));
//                        }
//                    });
//                } else {
//                    callBack.onModifyHeadFailure(IUtil.getStrToRes(R.string.toast_modify_head_failure));
//                }
//            }
//        }).start();
    }

    /**
     * 更新用户信息
     *
     * @param entity
     * @param callBack
     */
    private void updateUserInfo(final ModifyHeadEntity entity, final ModifyHeadCallBack callBack) {
//        modifyHeadCall = RetrofitManager.getInstance().getService().modifyUserInfo(entity, DataApplication.getInstance().getUserInfoEntity().getId());
//        modifyHeadCall.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                if (response.isSuccessful()) {
//                    callBack.onModifyHeadSuccess(entity.getIcon());
//                } else {
//                    callBack.onModifyHeadFailure(IUtil.getStrToRes(R.string.toast_modify_head_failure));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                callBack.onModifyHeadFailure(IUtil.getStrToRes(R.string.toast_modify_head_failure));
//            }
//        });
    }

    @Override
    public void modifyPassword(final ModifyPasswordCallBack callBack) {
//        modifyPwdCall = RetrofitManager.getInstance().getService().modifyUserPassword(callBack.getPasswordBody(), DataApplication.getInstance().getUserInfoEntity().getId());
//        modifyPwdCall.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                if (response.code() == 200 && response.isSuccessful()) {
//                    callBack.onModifyPasswordSuccess();
//                } else {
//                    callBack.onModifyPasswordFailure(IUtil.getStrToRes(R.string.toast_modify_pwd_faliure));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                callBack.onModifyPasswordFailure(IUtil.getStrToRes(R.string.toast_modify_pwd_faliure));
//            }
//        });
    }

    /**
     * 取消请求
     */
    @Override
    public void cancelRequest() {
        if (LoginCall != null && !LoginCall.isCanceled()) {
            LoginCall.cancel();
        }
        if (modifyHeadCall != null && modifyHeadCall.isCanceled()) {
            modifyHeadCall.cancel();
        }
        if (modifyPwdCall != null && modifyPwdCall.isCanceled()) {
            modifyPwdCall.cancel();
        }
    }
}
