package com.jinhui.easy.module.vote;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseFragment;
import com.jinhui.easy.base.BaseRecyclerViewAdapter;
import com.jinhui.easy.module.vote.data.VoteEntity;
import com.jinhui.easy.module.vote.data.source.VoteDataSource;
import com.jinhui.easy.module.vote.data.source.VoteRemoteRepo;
import com.jinhui.easy.utils.ISkipActivityUtil;
import com.jinhui.easy.view.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 投票
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:33
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class VoteListFragment extends BaseFragment implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener, VoteDataSource.VoteCallBack {


    @BindView(R.id.vote_recyclerView)
    PullToRefreshRecyclerView voteRecyclerView;

    private VoteListAdater adater;

    private VoteRemoteRepo remoteRepo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        remoteRepo = new VoteRemoteRepo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.vote_list, null);
        ButterKnife.bind(this, rootView);
        adater = new VoteListAdater(getContext());
        voteRecyclerView.setRecyclerViewAdapter(adater);
        voteRecyclerView.setPullToRefreshListener(this);
        voteRecyclerView.startUpRefresh();
        return rootView;
    }

    @Override
    public void onDownRefresh() {
        remoteRepo.requestVoteList(this);
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onVoteSuccess(List<VoteEntity> entities) {
        if (entities==null||entities.size()<=0){
            voteRecyclerView.setEmptyTextView();
        }else {
            adater.addItems(entities);
        }
        voteRecyclerView.closeDownRefresh();
    }

    @Override
    public void onVoteFailure(int code, String msg) {
        if (code == 404) {
            voteRecyclerView.setEmptyTextView();
        } else {
            showToast(msg);
        }
        voteRecyclerView.closeDownRefresh();
    }

    private class VoteListAdater extends BaseRecyclerViewAdapter<VoteEntity> {

        public VoteListAdater(Context context) {
            super(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(inflater.inflate(R.layout.item_vote_list, parent, false));
        }

        @Override
        public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ItemViewHolder itemViewHolder=(ItemViewHolder)holder;
            itemViewHolder.content.setText(mLists.get(position).getTitle());
            itemViewHolder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("entity",mLists.get(position));
                    ISkipActivityUtil.startIntent(mContent,VoteActivity.class,bundle);
                }
            });
        }



        class ItemViewHolder extends RecyclerView.ViewHolder {
            private TextView content;

            public ItemViewHolder(View view) {
                super(view);
                content = ButterKnife.findById(view, R.id.vote_item_content);
            }
        }
    }
}
