package com.wayto.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wayto.android.R;

import butterknife.ButterKnife;

/**
 * Button切换
 * <p>
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/6 11:00
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class SwitchButtonTwo extends LinearLayout {

    public final static int ONCLICK_LEFT = 1;
    public final static int ONCLICK_RIGHT = 2;

    TextView tvLeft;
    TextView tvRight;

    private String leftName="", rightName="";

    private SwitchButtonOnClickListenter listenter;

    public SwitchButtonTwo(Context context) {
        super(context);
        initAttributeset(context, null, 0);
        initView(context);
    }

    public SwitchButtonTwo(Context context, AttributeSet attri) {
        super(context, attri);
        initAttributeset(context, attri, 0);
        initView(context);
    }

    private void initView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.button_switch_layout, null);
        ButterKnife.bind(this, rootView);
        tvLeft=ButterKnife.findById(rootView,R.id.tv_left);
        tvRight=ButterKnife.findById(rootView, R.id.tv_right);
        if (!TextUtils.isEmpty(leftName)) {
            tvLeft.setText(leftName);
        }

        if (!TextUtils.isEmpty(rightName)) {
            tvRight.setText(rightName);
        }

        tvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLeft.setBackgroundResource(R.drawable.switch_button_left_pre);
                tvRight.setBackgroundResource(R.drawable.switch_button_right_nor);

                tvLeft.setTextColor(Color.parseColor("#E51C23"));
                tvRight.setTextColor(getResources().getColor(R.color.white));

                if (listenter != null) {
                    listenter.onSwitchOnClick(ONCLICK_LEFT);
                }
            }
        });

        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLeft.setBackgroundResource(R.drawable.switch_button_left_nor);
                tvRight.setBackgroundResource(R.drawable.switch_button_right_pre);

                tvLeft.setTextColor(getResources().getColor(R.color.white));
                tvRight.setTextColor(Color.parseColor("#E51C23"));

                if (listenter != null) {
                    listenter.onSwitchOnClick(ONCLICK_RIGHT);
                }
            }
        });

        addView(rootView);
    }

    /**
     * 初始化自定义属性值
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    private void initAttributeset(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButtonTwo, defStyle, 0);
        leftName = typedArray.getString(R.styleable.SwitchButtonTwo_textLeft);
        rightName = typedArray.getString(R.styleable.SwitchButtonTwo_textRight);

        typedArray.recycle();
    }

    public void setListenter(SwitchButtonOnClickListenter listenter) {
        this.listenter = listenter;
    }

    /**
     * 监听器
     */
    public interface SwitchButtonOnClickListenter {
        void onSwitchOnClick(int flag);
    }
}
