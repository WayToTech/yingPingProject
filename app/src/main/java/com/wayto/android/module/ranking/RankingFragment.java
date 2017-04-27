package com.wayto.android.module.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wayto.android.R;
import com.wayto.android.base.BaseFragment;
import com.wayto.android.module.ranking.data.RankingEntity;
import com.wayto.android.module.ranking.data.RepoCallBack;
import com.wayto.android.view.PullToRefreshRecyclerView;
import com.wayto.android.view.SwitchButton;
import com.wayto.android.widget.SwitchButtonTwo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/26 20:28
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class RankingFragment extends BaseFragment implements RepoCallBack<RankingEntity>, SwitchButtonTwo.SwitchButtonOnClickListenter, PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener {

    @BindView(R.id.RankingRecyclerView)
    PullToRefreshRecyclerView RankingRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.Fanking_switchButton)
    SwitchButtonTwo FankingSwitchButton;
    @BindView(R.id.RankingTaskRecyclerView)
    PullToRefreshRecyclerView RankingTaskRecyclerView;


    private IntegralRankingListAdapter integralRankingListAdapter;
    private TaskRankingListAdapter taskRankingListAdapter;

    private int index = SwitchButtonTwo.ONCLICK_LEFT;

    private RankingEntity entity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ranking, null);
        unbinder = ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        integralRankingListAdapter = new IntegralRankingListAdapter(getActivity());
        taskRankingListAdapter = new TaskRankingListAdapter(getActivity());
        RankingRecyclerView.setRecyclerViewAdapter(integralRankingListAdapter);
        RankingTaskRecyclerView.setRecyclerViewAdapter(taskRankingListAdapter);
        RankingRecyclerView.setMode(PullToRefreshRecyclerView.Mode.DISABLED);
        RankingTaskRecyclerView.setMode(PullToRefreshRecyclerView.Mode.DISABLED);
        RankingRecyclerView.setPullToRefreshListener(this);
        RankingRecyclerView.startUpRefresh();
        FankingSwitchButton.setListenter(this);
    }

    @Override
    public void onDownRefresh() {
        new RankingRemoteRepo().requestRanking(this);
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onDataNotAvailable(int code, String msg) {
        showToast(msg);
        RankingRecyclerView.closeDownRefresh();
    }

    @Override
    public void onDataAvailable(RankingEntity data) {
        integralRankingListAdapter.clearList();
        entity = data;
        if (data == null) {
            RankingRecyclerView.setEmptyTextView();
        } else
            RankingRecyclerView.closeDownRefresh();
        if (index == SwitchButtonTwo.ONCLICK_LEFT) {
            integralRankingListAdapter.addItems(data.getIntegralData());
        } else if (index == SwitchButton.FOCUS_RIGHT) {
            taskRankingListAdapter.addItems(entity.getTaskData());
        }
    }

    @Override
    public void onSwitchOnClick(int flag) {
        if (flag == SwitchButtonTwo.ONCLICK_LEFT) {
            integralRankingListAdapter.clearList();
            if (entity!=null){
                integralRankingListAdapter.addItems(entity.getIntegralData());
            }
            RankingRecyclerView.setVisibility(View.VISIBLE);
            RankingTaskRecyclerView.setVisibility(View.GONE);
        } else if (flag == SwitchButtonTwo.ONCLICK_RIGHT) {
            taskRankingListAdapter.clearList();
            if (entity!=null){
                taskRankingListAdapter.addItems(entity.getTaskData());
            }
            RankingRecyclerView.setVisibility(View.GONE);
            RankingTaskRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
