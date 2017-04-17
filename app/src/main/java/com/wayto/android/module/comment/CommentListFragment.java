package com.wayto.android.module.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wayto.android.R;
import com.wayto.android.base.BaseFragment;
import com.wayto.android.module.comment.data.TaskEntity;
import com.wayto.android.module.comment.data.source.CommentDataSource;
import com.wayto.android.module.comment.data.source.CommentRemoteRepo;
import com.wayto.android.view.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 网评
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:35
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class CommentListFragment extends BaseFragment implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener, CommentDataSource.CommentCallBack {

    @BindView(R.id.comment_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private CommetAdapter adapter;

    private CommentRemoteRepo remoteRepo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        remoteRepo = new CommentRemoteRepo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comment_fragment_mission, null);
        ButterKnife.bind(this, rootView);
        adapter = new CommetAdapter(getContext());
        mRecyclerView.setRecyclerViewAdapter(adapter);
        mRecyclerView.setPullToRefreshListener(this);
        mRecyclerView.setMode(PullToRefreshRecyclerView.Mode.PULL_FROM_START);
        mRecyclerView.startUpRefresh();

        return rootView;
    }

    @Override
    public void onDownRefresh() {
        remoteRepo.requestTaskData("task/appTaskList", this);
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onCommentSuccess(List<TaskEntity> entities) {
        adapter.clearList();
        if (entities != null && entities.size() > 0) {
            adapter.addItems(entities);
        } else {
            mRecyclerView.setEmptyTextView();
        }
        mRecyclerView.closeDownRefresh();
    }

    @Override
    public void onCommentFailure(int code, String msg) {
        if (code == 404) {
            mRecyclerView.setEmptyTextView();
        } else {
            showToast(msg);
            mRecyclerView.closeDownRefresh();
        }
        mRecyclerView.closeDownRefresh();
    }
}
