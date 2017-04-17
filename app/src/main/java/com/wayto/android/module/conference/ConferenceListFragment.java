package com.wayto.android.module.conference;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wayto.android.R;
import com.wayto.android.base.BaseFragment;
import com.wayto.android.base.BaseRecyclerViewAdapter;
import com.wayto.android.module.conference.data.ConferenceEntity;
import com.wayto.android.module.conference.data.dataSource.ConferenceDatasource;
import com.wayto.android.module.conference.data.dataSource.ConferenceRemoteRepo;
import com.wayto.android.module.home.HomeAdapter;
import com.wayto.android.utils.ISkipActivityUtil;
import com.wayto.android.view.PullToRefreshRecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 会务
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:34
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class ConferenceListFragment extends BaseFragment implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener, ConferenceDatasource.ConferenceCallBack {

    @BindView(R.id.conference_refreshRecycler)
    PullToRefreshRecyclerView conferenceRefreshRecycler;

    private ConferenceAdapter adapter;

    private ConferenceRemoteRepo remoteRepo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        remoteRepo = new ConferenceRemoteRepo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.conference_fragment_record, null);
        ButterKnife.bind(this, rootView);
        adapter = new ConferenceAdapter(getContext());
        conferenceRefreshRecycler.setRecyclerViewAdapter(adapter);
        conferenceRefreshRecycler.setPullToRefreshListener(this);
        conferenceRefreshRecycler.startUpRefresh();
        return rootView;
    }

    @Override
    public void onDownRefresh() {
        remoteRepo.requestMeetingNotice(this);
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onConferenceSuccess(List<ConferenceEntity> entities) {
        adapter.clearList();
        if (entities != null && entities.size() > 0) {
            adapter.addItems(entities);
        } else {
            conferenceRefreshRecycler.setEmptyTextView();
        }
        conferenceRefreshRecycler.closeDownRefresh();
    }

    @Override
    public void onConferenceFailure(int code, String msg) {
        if (code==404){
            conferenceRefreshRecycler.setEmptyTextView();
        }else {
            showToast(msg);
        }
        conferenceRefreshRecycler.closeDownRefresh();
    }

    private class ConferenceAdapter extends BaseRecyclerViewAdapter<ConferenceEntity> {
        public ConferenceAdapter(Context context) {
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
            itemViewHolder.timeText.setText("会务时间:" + mLists.get(position).getReleasetime());
            itemViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putInt("id",mLists.get(position).getId());
                    ISkipActivityUtil.startIntent(mContent,ConferenceDetailsActivity.class,bundle);
                }
            });
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView contentText;
            TextView timeText;
            CardView cardView;

            public ItemViewHolder(View view) {
                super(view);
                contentText = ButterKnife.findById(view, R.id.conference_content);
                timeText = ButterKnife.findById(view, R.id.conference_time);
                cardView=ButterKnife.findById(view,R.id.item_notice_cardView);
                ButterKnife.bind(this, view);
            }
        }
    }
}
