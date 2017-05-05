package com.jinhui.easy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 附件GridView布局
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:08
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class AccessoryImageGridView extends GridView {

    public AccessoryImageGridView(Context context) {
        super(context);
    }

    public AccessoryImageGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AccessoryImageGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
