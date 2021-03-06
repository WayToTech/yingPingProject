package com.jinhui.easy.module.comment;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jinhui.easy.BuildConfig;
import com.jinhui.easy.R;
import com.jinhui.easy.ShareActivity;
import com.jinhui.easy.base.BaseRecyclerViewAdapter;
import com.jinhui.easy.module.comment.data.TaskEntity;
import com.jinhui.easy.module.home.data.HomeEntity;
import com.jinhui.easy.utils.ILog;
import com.jinhui.easy.utils.ISkipActivityUtil;

import java.util.HashMap;

import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/15 13:39
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class CommetAdapter extends BaseRecyclerViewAdapter<TaskEntity> {

    public CommetAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.content.setText(mLists.get(position).getTitle());
        itemViewHolder.typeTime.setText(mLists.get(position).getTasktype() + "  截止时间" + mLists.get(position).getCompletiontime());
        itemViewHolder.integral.setText(mLists.get(position).getIntegral() + "积分");
        final String status = mLists.get(position).getStatus();
        if ("待完成".equals(status)) {
            itemViewHolder.button.setText("立即执行");
        } else {
            itemViewHolder.button.setText(status);
            itemViewHolder.button.setBackgroundResource(R.drawable.btn_round_grad);
        }
        itemViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("已完成".equals(status)) {
                    return;
                }
                if ("转发".equals(mLists.get(position).getTasktype())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", mLists.get(position).getTaskurl());
                    bundle.putString("icon", mLists.get(position).getForwardingicon());
                    bundle.putString("content", mLists.get(position).getTitle());
                    ISkipActivityUtil.startIntent(mContent, ShareActivity.class, bundle);
                } else {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(TextUtils.isEmpty(mLists.get(position).getTaskurl()) ? "http://www.baidu.com" : mLists.get(position).getTaskurl());
                    intent.setData(content_url);
                    mContent.startActivity(intent);
                }
            }
        });
        itemViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("已完成".equals(status)) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", mLists.get(position).getId());
                    ISkipActivityUtil.startIntent(mContent, TaskDetailsActivity.class, bundle);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", mLists.get(position).getId());
                    ISkipActivityUtil.startIntent(mContent, RecordTaskActivity.class, bundle);
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.item_fragment_task, parent, false));
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView content, typeTime, integral;
        private Button button;
        private CardView cardView;

        public ItemViewHolder(View view) {
            super(view);
            content = ButterKnife.findById(view, R.id.task_content);
            typeTime = ButterKnife.findById(view, R.id.task_type_time);
            integral = ButterKnife.findById(view, R.id.task_integral);
            button = ButterKnife.findById(view, R.id.task_button);
            cardView = ButterKnife.findById(view, R.id.task_CardView);
        }
    }
}
