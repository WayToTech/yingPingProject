package com.jinhui.easy.view;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jinhui.easy.base.DataApplication;

/**
 * 将bitmap切割成圆形的Target,适用于Glide框架中的into()
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:17
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class RoundedBitmapImageViewTarget extends BitmapImageViewTarget {

    public RoundedBitmapImageViewTarget(ImageView view) {
        super(view);
    }

    @Override
    protected void setResource(Bitmap resource) {
        RoundedBitmapDrawable circularBitmapDrawable =
                RoundedBitmapDrawableFactory.create(DataApplication.getInstance().getResources(), resource);
        circularBitmapDrawable.setCircular(true);
        view.setImageDrawable(circularBitmapDrawable);
    }
}
