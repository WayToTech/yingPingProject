package com.jinhui.easy.module.notice.data.source;

import com.jinhui.easy.BuildConfig;
import com.jinhui.easy.entity.ResponseModel;
import com.jinhui.easy.module.notice.data.AppVersionEntity;
import com.jinhui.easy.utils.IFileUtils;
import com.jinhui.easy.utils.ILog;
import com.jinhui.easy.vendor.retrofit.APIService;
import com.jinhui.easy.vendor.retrofit.RetrofitManager;
import com.jinhui.easy.vendor.retrofit.download.DownloadProgressHandler;
import com.jinhui.easy.vendor.retrofit.download.ProgressHelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * App版本检测
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:37
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class CheckAppVersionRemoteRepo implements CheckAppVersionDataSource {
    private final String TAG = getClass().getSimpleName();

    private static CheckAppVersionRemoteRepo checkAppVersionRepo;

    private Call<ResponseModel<AppVersionEntity>> appVersionCall;
    private Call<ResponseBody> downloadCall;

    public static CheckAppVersionRemoteRepo newInstance() {
        if (checkAppVersionRepo == null) {
            checkAppVersionRepo = new CheckAppVersionRemoteRepo();
        }
        return checkAppVersionRepo;
    }

    @Override
    public void checkAppVersion(final CheckAppVersionCallBack callBack) {
        appVersionCall = RetrofitManager.getInstance().getService().checkAppVersion();
        appVersionCall.enqueue(new Callback<ResponseModel<AppVersionEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<AppVersionEntity>> call, Response<ResponseModel<AppVersionEntity>> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    callBack.onCheckAppSuccess(response.body().getData());
                } else {
                    callBack.onCheckAppDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<AppVersionEntity>> call, Throwable t) {
                callBack.onCheckAppDataNotAvailable();
            }
        });
    }

    @Override
    public void downloadApk(final DownloadCallBack downloadCallBack) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.DOMAI);
        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
        APIService retrofit = retrofitBuilder
                .client(builder.build())
                .build().create(APIService.class);

        downloadCall = retrofit.downloadFile(downloadCallBack.getDownloadURL());
        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long bytesRead, long contentLength, boolean done) {
                downloadCallBack.onDownloadProgress(bytesRead, contentLength, done);
                ILog.e(TAG, String.format("%d%% done\n", (100 * bytesRead) / contentLength));
                downloadCallBack.onDownloadProgress((int) ((100 * bytesRead) / contentLength));
            }
        });
        downloadCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                InputStream is = response.body().byteStream();
                                File file = new File(IFileUtils.getDownloadDir());
                                if (!file.exists()) {
                                    file.mkdirs();
                                }
                                FileOutputStream fos = new FileOutputStream(file + "/YunweiBase.apk");
                                BufferedInputStream bis = new BufferedInputStream(is);
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = bis.read(buffer)) != -1) {
                                    fos.write(buffer, 0, len);
                                    fos.flush();
                                }
                                fos.close();
                                bis.close();
                                is.close();
                                downloadCallBack.onDownloadComplete(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                                downloadCallBack.onDownloadDataNotAvailable();
                            }
                        }
                    }).start();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                downloadCallBack.onDownloadDataNotAvailable();
            }
        });
    }

    @Override
    public void cancelRequest() {
        if (appVersionCall != null && !appVersionCall.isCanceled()) {
            appVersionCall.cancel();
        }
        if (downloadCall != null && !downloadCall.isCanceled()) {
            downloadCall.cancel();
        }
    }
}
