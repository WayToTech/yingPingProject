package com.jinhui.easy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jinhui.easy.base.BaseActivity;
import com.jinhui.easy.utils.IFileUtils;
import com.jinhui.easy.utils.ILog;
import com.jinhui.easy.utils.IUtil;
import com.jinhui.easy.vendor.retrofit.APIService;
import com.jinhui.easy.vendor.retrofit.RetrofitManager;
import com.jinhui.easy.vendor.retrofit.download.ProgressHelper;

import java.io.InputStream;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.moments.WechatMoments;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/5/4 19:57
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class ShareActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.iamgeView)
    ImageView iamgeView;
    @BindView(R.id.content)
    TextView contentTextView;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    @BindView(R.id.shown)
    TextView shown;

    String url, mContent;
    private String icon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        setToolbarVisibility(View.GONE);
        icon = getIntent().getStringExtra("icon");
        url = getIntent().getStringExtra("url");
        mContent = getIntent().getStringExtra("content");
        if (!TextUtils.isEmpty(url)) {
            Glide.with(this).load(BuildConfig.DOMAI + icon).into(iamgeView);
        }
        contentTextView.setText(mContent);
//        IUtil.shot(this);
        // 获取屏幕
//        View dView = getWindow().getDecorView();
//        dView.setDrawingCacheEnabled(true);
//        dView.buildDrawingCache();

//        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BuildConfig.DOMAI);
//        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
//        APIService retrofit = retrofitBuilder
//                .client(builder.build())
//                .build().create(APIService.class);
//
//        Call<ResponseBody> downloadCall = retrofit.downloadFile(icon);
//        downloadCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.code() == 200) {
//                    ILog.d(TAG, "body==" + response.body());
//                    InputStream is = response.body().byteStream();
//                    iamgeView.setImageBitmap(BitmapFactory.decodeStream(is));
//                }
//                shown.setVisibility(View.GONE);
//                shotView();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                shown.setVisibility(View.GONE);
//                shotView();
//            }
//        });
//
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            shown.setVisibility(View.GONE);
            shotView();
        }
    }

    private void share() {
        WechatMoments.ShareParams shareParams = new WechatMoments.ShareParams();
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setUrl(TextUtils.isEmpty(url) ? "http://sharesdk.cn" : url);
        shareParams.setText(mContent);
        shareParams.setTitle(mContent);
//        shareParams.setImageData(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_logo));
        shareParams.setImageUrl(BuildConfig.DOMAI + icon);
        Platform qzone = ShareSDK.getPlatform(WechatMoments.NAME);
        qzone.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ILog.d(TAG, "onComplete==" + platform.getName());

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ILog.d(TAG, "onError==" + platform.getName());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ILog.d(TAG, "onCancel==" + platform.getName());
            }
        });
        qzone.share(shareParams);
    }

    private void shotView() {
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bmp = dView.getDrawingCache();
        IUtil.saveImage(IFileUtils.getImageCatchDir(), bmp, 0);
        share();
        finish();
    }

    @OnClick(R.id.send)
    public void onClick() {
    }
}
