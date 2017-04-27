package com.wayto.android.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.wayto.android.R;
import com.wayto.android.base.BaseFragment;
import com.wayto.android.common.Constant;
import com.wayto.android.common.dialog.DialogFactory;
import com.wayto.android.common.eventbus.NoticeEvent;
import com.wayto.android.module.comment.RecordTaskActivity;
import com.wayto.android.module.comment.TaskClassifyActivity;
import com.wayto.android.module.comment.TaskDetailsActivity;
import com.wayto.android.module.conference.ConferenceDetailsActivity;
import com.wayto.android.module.home.data.HomeEntity;
import com.wayto.android.module.notice.NoticeDetailsActivity;
import com.wayto.android.utils.IActivityManage;
import com.wayto.android.utils.ILog;
import com.wayto.android.utils.ISkipActivityUtil;
import com.wayto.android.utils.ISpfUtil;
import com.wayto.android.view.PullToRefreshRecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    public void onResume() {
        super.onResume();

        homePresenter.requestMember();
    }

    @Override
    public void onDownRefresh() {
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
                noticeContentLayout.removeAllViews();
                for (final HomeEntity.NoticeDateBean noticeDateBean : entity.getNoticeDate()) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_notice, null);
                    TextView contentTextView = (TextView) view.findViewById(R.id.notice_content);
                    view.findViewById(R.id.notice_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString("id", noticeDateBean.getId() + "");
                            ISkipActivityUtil.startIntent(getContext(), NoticeDetailsActivity.class, bundle);
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
                meetingContentLayout.removeAllViews();
                for (final HomeEntity.MeetingNoticeDateBean meetingNoticeDateBean : entity.getMeetingNoticeDate()) {
                    meetingNumber.setText(entity.getMeetingNoticeDate().size() + "");
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_meeting, null);
                    TextView timeTextView = ButterKnife.findById(view, R.id.meeting_time);
                    TextView contenTextView = ButterKnife.findById(view, R.id.meeting_content);
                    TextView address = ButterKnife.findById(view, R.id.meeting_address);
                    view.findViewById(R.id.meeting_button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", meetingNoticeDateBean.getId());
                            ISkipActivityUtil.startIntent(getContext(), ConferenceDetailsActivity.class, bundle);
                        }
                    });
                    timeTextView.setText("会议时间:" + meetingNoticeDateBean.getReleasetime());
                    contenTextView.setText(meetingNoticeDateBean.getTitle());
                    address.setText("会议地址:" + meetingNoticeDateBean.getAddress());
                    meetingContentLayout.addView(view);
                }
            } else {
                meetingLayout.setVisibility(View.GONE);
            }

            /*网评任务*/
            if (entity.getTaskDate().size() > 0) {
                taskNumber.setText(entity.getTaskDate().size() + "");
                taskContentLayout.removeAllViews();
                for (final HomeEntity.TaskDateBean taskDateBean : entity.getTaskDate()) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_task, null);
                    final TextView content = ButterKnife.findById(view, R.id.task_content);
                    TextView typeTime = ButterKnife.findById(view, R.id.task_type_time);
                    TextView integral = ButterKnife.findById(view, R.id.task_integral);
                    Button button = ButterKnife.findById(view, R.id.task_button);
                    content.setText(taskDateBean.getTitle());
                    typeTime.setText(taskDateBean.getTasktype() + "  截止时间" + taskDateBean.getCompletiontime());
                    integral.setText(entity.getIntegral() + "积分");
                    final String status = taskDateBean.getStatus();
                    if ("待完成".equals(status)) {
                        button.setText("立即执行");
                    } else {
                        button.setText(status);
                        button.setBackgroundResource(R.drawable.btn_round_grad);
                    }
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if ("已完成".equals(status)) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("id", taskDateBean.getId());
                                ISkipActivityUtil.startIntent(getContext(), TaskDetailsActivity.class, bundle);
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putInt("id", taskDateBean.getId());
                                ISkipActivityUtil.startIntent(getContext(), RecordTaskActivity.class, bundle);
                            }
                        }
                    });
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            if ("已完成".equals(status)) {
//                                return;
//                            }
//                            IUtil.shot(getActivity());
                            UMWeb web = new UMWeb(TextUtils.isEmpty(taskDateBean.getTaskurl()) ? "http://baidu.com" : taskDateBean.getTaskurl());
                            web.setTitle(taskDateBean.getTitle());//标题
                            web.setThumb(new UMImage(getContext(), R.mipmap.ic_logo));  //缩略图
                            web.setDescription("来至畅评分享");//描述
                            new ShareAction(getActivity())
                                    .withMedia(web)
                                    .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .setCallback(new UMShareListener() {
                                        @Override
                                        public void onStart(SHARE_MEDIA share_media) {
                                            ILog.d(TAG, "onStart");
                                        }

                                        @Override
                                        public void onResult(SHARE_MEDIA share_media) {
                                            ILog.d(TAG, "onResult");
                                        }

                                        @Override
                                        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                            showToast(throwable.getMessage());
                                            ILog.d(TAG, "onError");
                                        }

                                        @Override
                                        public void onCancel(SHARE_MEDIA share_media) {
                                            ILog.d(TAG, "onCancel");
                                        }
                                    }).share();
                        }
                    });

                    taskContentLayout.addView(view);
                }
            } else {
                taskLayout.setVisibility(View.GONE);
            }
            taskNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ISkipActivityUtil.startIntent(getContext(), TaskClassifyActivity.class);
                }
            });
        } else {
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

    @OnClick(R.id.record_button)
    public void onClick() {
        ISkipActivityUtil.startIntent(getContext(), RecordMessageActivity.class);
    }

    @OnClick({R.id.Home_exit, R.id.notice_title_layout, R.id.meeting_title_layout, R.id.task_title_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notice_title_layout:
                NoticeEvent noticeEvent = new NoticeEvent();
                noticeEvent.setFlag(1);
                EventBus.getDefault().post(noticeEvent);
                break;
            case R.id.meeting_title_layout:
                NoticeEvent noticeEvent1 = new NoticeEvent();
                noticeEvent1.setFlag(2);
                EventBus.getDefault().post(noticeEvent1);
                break;
            case R.id.task_title_layout:
                ISkipActivityUtil.startIntent(getContext(), TaskClassifyActivity.class);
                break;
            case R.id.Home_exit:
                DialogFactory.showMsgDialog(getContext(), getString(R.string.dialog_title_exit), getString(R.string.exit_msg) + getString(R.string.app_name) + "?", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ISpfUtil.setValue(Constant.ACCOUNT_KEY, "");
                        ISpfUtil.setValue(Constant.PSSWORD_KEY, "");
                        System.gc();
                        IActivityManage.getInstance().exit();
                        System.exit(0);
                    }
                });
                break;
        }
    }
}
