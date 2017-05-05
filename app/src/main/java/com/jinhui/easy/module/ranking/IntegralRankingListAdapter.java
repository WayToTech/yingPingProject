package com.jinhui.easy.module.ranking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseRecyclerViewAdapter;
import com.jinhui.easy.module.ranking.data.RankingEntity;

import butterknife.ButterKnife;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/27 9:37
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class IntegralRankingListAdapter extends BaseRecyclerViewAdapter<RankingEntity.IntegralDataBean> {


    public IntegralRankingListAdapter(Context context) {
        super(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.item_ranking_layout, parent, false));
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.rankingName.setText(mLists.get(position).getDepartmentname());
        viewHolder.rankingInt.setText("积分:" + mLists.get(position).getDepartmenintegral());
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView rankingName;
        TextView rankingInt;

        public ItemViewHolder(View view) {
            super(view);
            rankingName=ButterKnife.findById(view,R.id.ranking_name);
            rankingInt=ButterKnife.findById(view,R.id.ranking_int);
        }
    }
}
