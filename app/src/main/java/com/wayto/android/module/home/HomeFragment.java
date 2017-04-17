package com.wayto.android.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wayto.android.R;
import com.wayto.android.base.BaseFragment;
import com.wayto.android.module.comment.TaskClassifyActivity;
import com.wayto.android.module.comment.RecordTaskActivity;
import com.wayto.android.module.conference.ConferenceDetailsActivity;
import com.wayto.android.module.home.data.HomeEntity;
import com.wayto.android.module.notice.NoticeDetailsActivity;
import com.wayto.android.utils.ISkipActivityUtil;
import com.wayto.android.view.PullToRefreshRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:38
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class HomeFragment extends BaseFragment implements HomeContract.HomeView, PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.HomeRecycler)
    PullToRefreshRecyclerView mRecycler;
    @BindView(R.id.home_UserName)
    TextView mUserName;
    @BindView(R.id.Home_departmentName)
    TextView mDepartmentName;
    @BindView(R.id.home_integral)
    TextView mIntegral;
    @BindView(R.id.notice_number)
    TextView noticeNumber;
    @BindView(R.id.notice_content_Layout)
    LinearLayout noticeContentLayout;
    @BindView(R.id.notice_Layout)
    CardView noticeLayout;
    @BindView(R.id.meeting_number)
    TextView meetingNumber;
    @BindView(R.id.meeting_content_Layout)
    LinearLayout meetingContentLayout;
    @BindView(R.id.meeting_Layout)
    CardView meetingLayout;
    @BindView(R.id.task_number)
    TextView taskNumber;
    @BindView(R.id.task_content_Layout)
    LinearLayout taskContentLayout;
    @BindView(R.id.task_Layout)
    CardView taskLayout;
    private HomeAdapter adapter;

    private HomePresenter homePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_home, null);
        ButterKnife.bind(this, rootView);
        adapter = new HomeAdapter(getContext());
        mRecycler.setRecyclerViewAdapter(adapter);
        mRecycler.setPullToRefreshListener(this);
        mRecycler.startUpRefresh();
        return rootView;
    }

    @Override
    public void onDownRefresh() {
        homePresenter.requestMember();
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onHomeStart() {

    }

    @Override
    public void onHomeEnd() {
        mRecycler.closeDownRefresh();
    }

    @Override
    public void onHomeSuccess(HomeEntity entity) {
        if (entity != null) {
            mUserName.setText(entity.getRealName());
            mDepartmentName.setText("所属机构:" + entity.getDepartmentName());
            mIntegral.setText(entity.getIntegral());
            /*公告*/
            if (entity.getNoticeDate().size() > 0) {
                noticeNumber.setText(entity.getNoticeDate().size() + "");
                for (final HomeEntity.NoticeDateBean noticeDateBean : entity.getNoticeDate()) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_notice, null);
                    TextView contentTextView = (TextView) view.findViewById(R.id.notice_content);
                    view.findViewById(R.id.notice_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle=new Bundle();
                            bundle.putString("id",noticeDateBean.getId()+"");
                            ISkipActivityUtil.startIntent(getContext(), NoticeDetailsActivity.class,bundle);
                        }
                    });
                    contentTextView.setText(noticeDateBean.getTitle());
                    noticeContentLayout.addView(view);
                }
            } else {
                noticeLayout.setVisibility(View.GONE);
            }
            /*会务通知*/
            if (entity.getMeetingNoticeDate().size() > 0) {
                for (final HomeEntity.MeetingNoticeDateBean meetingNoticeDateBean : entity.getMeetingNoticeDate()) {
                    meetingNumber.setText(entity.getMeetingNoticeDate().size() + "");
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_meeting, null);
                    TextView timeTextView = ButterKnife.findById(view, R.id.meeting_time);
                    TextView contenTextView = ButterKnife.findById(view, R.id.meeting_content);
                    TextView address = ButterKnife.findById(view, R.id.meeting_address);
                    view.findViewById(R.id.meeting_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle=new Bundle();
                            bundle.putInt("id",meetingNoticeDateBean.getId());
                            ISkipActivityUtil.startIntent(getContext(), ConferenceDetailsActivity.class,bundle);
                        }
                    });
                    timeTextView.setText("会议时间:" + meetingNoticeDateBean.getReleasetime());
                    contenTextView.setText(meetingNoticeDateBean.getTitle());
                    address.setText("会议地址:");
                    meetingContentLayout.addView(view);
                }
            } else {
                meetingLayout.setVisibility(View.GONE);
            }

            /*网评任务*/
            if (entity.getTaskDate().size()>0){
                taskNumber.setText(entity.getTaskDate().size()+"");
                for (final HomeEntity.TaskDateBean taskDateBean:entity.getTaskDate()){
                    View view=LayoutInflater.from(getContext()).inflate(R.layout.item_task,null);
                    TextView content=ButterKnife.findById(view,R.id.task_content);
                    TextView typeTime=ButterKnife.findById(view,R.id.task_type_time);
                    TextView integral=ButterKnife.findById(view,R.id.task_integral);
                    Button button=ButterKnife.findById(view,R.id.task_button);
                    content.setText(taskDateBean.getTitle());
                    typeTime.setText(taskDateBean.getTasktype()+"  截止时间"+taskDateBean.getCompletiontime());
                    integral.setText(entity.getIntegral()+"积分");
                    String status = taskDateBean.getStatus();
                    if ("待完成".equals(status)) {
                        button.setText("立即执行");
                    } else {
                        button.setText(status);
                        button.setBackgroundResource(R.drawable.btn_round_grad);
                    }
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle=new Bundle();
                            bundle.putInt("id",taskDateBean.getId());
                            ISkipActivityUtil.startIntent(getContext(),RecordTaskActivity.class,bundle);
                        }
                    });
                    taskContentLayout.addView(view);
                }
            }else {
                taskLayout.setVisibility(View.GONE);
            }
            taskNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ISkipActivityUtil.startIntent(getContext(), TaskClassifyActivity.class);
                }
            });
        }else {
            noticeLayout.setVisibility(View.GONE);
            meetingLayout.setVisibility(View.GONE);
            taskLayout.setVisibility(View.GONE);
        }
        adapter.addItem(entity);
    }

    @Override
    public void onHomeFailure(int code, String msg) {
        showToast(msg);
        mRecycler.closeDownRefresh();
        noticeLayout.setVisibility(View.GONE);
        meetingLayout.setVisibility(View.GONE);
        taskLayout.setVisibility(View.GONE);
    }
}
