package com.jinhui.easy.module.notice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseFragment;
import com.jinhui.easy.base.BaseRecyclerViewAdapter;
import com.jinhui.easy.module.notice.data.NoticeEntity;
import com.jinhui.easy.module.notice.data.source.NoticeDataSource;
import com.jinhui.easy.module.notice.data.source.NoticeRemoteRepo;
import com.jinhui.easy.utils.ISkipActivityUtil;
import com.jinhui.easy.view.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公告
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:37
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class NoticeListFragment extends BaseFragment implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener, NoticeDataSource.NoticeCallBack {

    @BindView(R.id.noticeFragment_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private NoticeAdapter adapter;

    private NoticeRemoteRepo remoteRepo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        remoteRepo = new NoticeRemoteRepo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notice, null);
        ButterKnife.bind(this, rootView);
        adapter = new NoticeAdapter(getContext());
        mRecyclerView.setRecyclerViewAdapter(adapter);
        mRecyclerView.setPullToRefreshListener(this);
        mRecyclerView.startUpRefresh();
        return rootView;
    }

    @Override
    public void onDownRefresh() {
        remoteRepo.requestNoticeData(this);
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onNoticeSuccess(List<NoticeEntity> entities) {
        adapter.clearList();
        if (entities != null && entities.size() > 0) {
            adapter.addItems(entities);
        } else {
            mRecyclerView.setEmptyTextView();
        }
        mRecyclerView.closeDownRefresh();
    }

    @Override
    public void onNoticeFailure(int code, String msg) {
        if (code==404){
            mRecyclerView.setEmptyTextView();
        }else {
            showToast(msg);
        }
        mRecyclerView.closeDownRefresh();
    }

    private class NoticeAdapter extends BaseRecyclerViewAdapter<NoticeEntity> {
        public NoticeAdapter(Context context) {
            super(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(inflater.inflate(R.layout.item_conference, parent, false));
        }

        @Override
        public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.contentText.setText(mLists.get(position).getTitle());
            itemViewHolder.timeText.setText("公告时间:" + mLists.get(position).getReleasetime());
            itemViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putString("id",mLists.get(position).getId()+"");
                    ISkipActivityUtil.startIntent(mContent,NoticeDetailsActivity.class,bundle);
                }
            });
            itemViewHolder.address.setVisibility(View.GONE);
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView contentText;
            TextView timeText;
            CardView cardView;
            TextView address;

            public ItemViewHolder(View view) {
                super(view);
                contentText = ButterKnife.findById(view, R.id.conference_content);
                timeText = ButterKnife.findById(view, R.id.conference_time);
                cardView=ButterKnife.findById(view,R.id.item_notice_cardView);
                address=ButterKnife.findById(view,R.id.conference_address);
                ButterKnife.bind(this, view);
            }
        }
    }
}
