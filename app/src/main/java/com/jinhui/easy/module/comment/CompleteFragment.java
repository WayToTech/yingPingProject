package com.jinhui.easy.module.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseFragment;
import com.jinhui.easy.module.comment.data.TaskEntity;
import com.jinhui.easy.module.comment.data.source.CommentDataSource;
import com.jinhui.easy.module.comment.data.source.CommentRemoteRepo;
import com.jinhui.easy.view.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 已完成
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/15 13:10
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class CompleteFragment extends BaseFragment implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener, CommentDataSource.CommentCallBack {

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
        remoteRepo.requestTaskData("task/appTaskEndList", this);
        return rootView;
    }

    @Override
    public void onDownRefresh() {

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
        }
        mRecyclerView.closeDownRefresh();
    }
}
