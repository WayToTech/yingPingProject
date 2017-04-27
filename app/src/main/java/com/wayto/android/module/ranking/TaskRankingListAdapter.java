package com.wayto.android.module.ranking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wayto.android.R;
import com.wayto.android.base.BaseRecyclerViewAdapter;
import com.wayto.android.module.ranking.data.RankingEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/27 9:37
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class TaskRankingListAdapter extends BaseRecyclerViewAdapter<RankingEntity.TaskDataBean> {


    public TaskRankingListAdapter(Context context) {
        super(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_ranking_layout, parent, false));
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.rankingName.setText(mLists.get(position).getRealName());
        viewHolder.rankingInt.setText("任务:" + mLists.get(position).getTaskSize());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView rankingName;
        TextView rankingInt;

        public ViewHolder(View view) {
            super(view);
            rankingName=ButterKnife.findById(view,R.id.ranking_name);
            rankingInt=ButterKnife.findById(view,R.id.ranking_int);
        }
    }
}
