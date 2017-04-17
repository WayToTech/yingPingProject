package com.wayto.android.module.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.wayto.android.R;
import com.wayto.android.base.BaseRecyclerViewAdapter;
import com.wayto.android.common.dialog.ToastUtil;
import com.wayto.android.module.home.data.HomeEntity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-04-08
 * Time: 12:07
 * Version:1.0
 */

public class HomeAdapter extends BaseRecyclerViewAdapter<HomeEntity> {

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.item_home_list, parent, false));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @OnClick(R.id.look)
    public void onClick() {
        ToastUtil.showToast(mContent,"look");
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
