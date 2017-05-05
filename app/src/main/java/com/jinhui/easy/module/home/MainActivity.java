package com.jinhui.easy.module.home;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.jinhui.easy.common.Constant;
import com.jinhui.easy.utils.PermissionHelper;
import com.umeng.socialize.UMShareAPI;
import com.jinhui.easy.R;
import com.jinhui.easy.base.BaseActivity;
import com.jinhui.easy.base.BaseFragment;
import com.jinhui.easy.common.dialog.DialogFactory;
import com.jinhui.easy.common.eventbus.NoticeEvent;
import com.jinhui.easy.module.comment.CommentListFragment;
import com.jinhui.easy.module.conference.ConferenceListFragment;
import com.jinhui.easy.module.mainFuncations.MainContract;
import com.jinhui.easy.module.notice.CheckAppVersion.CheckAppVersionContract;
import com.jinhui.easy.module.notice.NoticeListFragment;
import com.jinhui.easy.module.ranking.RankingFragment;
import com.jinhui.easy.utils.IActivityManage;
import com.jinhui.easy.view.MainBottomNavigationBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主界面
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:33
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class MainActivity extends BaseActivity implements MainBottomNavigationBar.BottomTabSelectedListener, MainContract.MainView, CheckAppVersionContract.View {
    private final String TAG = getClass().getSimpleName();

    /*模块标记*/
    private final int TAB_HOME = 0;
    private final int TAB_MISSION = 1;
    private final int TAB_TRACK = 2;
    private final int TAB_RECORD = 3;
    private final int TAB_MINE = 4;
    /*Bottom Tab 资源*/
    private int[] tabIconRes;
    private int[] tabNameRes;
    private List<BaseFragment> tabModule = new ArrayList<>();

    @BindView(R.id.main_bottom_navigationBar)
    MainBottomNavigationBar mainBottomNavigationBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        setToolbarCenterTitle(R.string.main_home_tab);
        setSwipeEnabled(false);
        ButterKnife.bind(this);
        init();
        PermissionHelper.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, Constant.REQUEST_READ_EXTERNAL_STORAGE);
        PermissionHelper.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Constant.REQUEST_WRITE_EXTERNAL_STORAGE);
    }


    /**
     * 初始化
     */
    private void init() {
        initBottomTabRes();

        initBottomNavigationBar();
    }

    /**
     * 初始化Bottom Tab Module Res
     * 如果要增加或者删除相应的模块，在对应的index添加或者删除,及修改@onTabSelected方法监听回调
     */
    private void initBottomTabRes() {
        tabNameRes = new int[]{R.string.main_home_tab,
                R.string.main_mission_tab,
                R.string.main_track_tab,
                R.string.main_record_tab,
                R.string.main_mine_tab};
        tabIconRes = new int[]{R.drawable.ic_home_24,
                R.drawable.ic_comment_24,
                R.drawable.ic_ranking_24,
                R.drawable.ic_conference_24,
                R.drawable.ic_notice_24};

        tabModule.add(new HomeFragment());
        tabModule.add(new CommentListFragment());
        tabModule.add(new RankingFragment());
        tabModule.add(new ConferenceListFragment());
        tabModule.add(new NoticeListFragment());
    }

    /**
     * 初始化BottomNavigationBar
     */
    private void initBottomNavigationBar() {
        mainBottomNavigationBar.initConfig(this, R.id.main_container_FrameLayout);
        for (int i = 0; i < tabIconRes.length; i++) {
            mainBottomNavigationBar.addTabItem(tabIconRes[i], tabNameRes[i]);
            mainBottomNavigationBar.addFragment(tabModule.get(i));
        }
        mainBottomNavigationBar.setTabSelectedListener(this);
        mainBottomNavigationBar.setFirstSelectedTab(TAB_HOME);
    }


    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case TAB_HOME:
                setToolbarCenterTitle(R.string.main_home_tab);
                setToolbarVisibility(View.GONE);
                break;
            case TAB_MISSION:
                setToolbarCenterTitle(R.string.main_mission_tab);
                setToolbarVisibility(View.VISIBLE);
                break;
            case TAB_TRACK:
                setToolbarCenterTitle(R.string.main_track_tab);
                setToolbarVisibility(View.GONE);
                break;
            case TAB_RECORD:
                setToolbarCenterTitle(R.string.main_record_tab);
                setToolbarVisibility(View.VISIBLE);
                break;
            case TAB_MINE:
                setToolbarCenterTitle(R.string.main_mine_tab);
                setToolbarVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onMainThreedEvent(NoticeEvent event) {
        super.onMainThreedEvent(event);
        switch (event.getFlag()){
            case 1:
                mainBottomNavigationBar.setFirstSelectedTab(TAB_MINE);
                break;
            case 2:
                mainBottomNavigationBar.setFirstSelectedTab(TAB_RECORD);
                break;
        }
    }

    @Override
    public void showRequestFail() {

    }

    @Override
    public void showIsLatestVersion() {

    }

    @Override
    public void showCheckingDialog() {

    }

    @Override
    public void dismissCheckingDialog() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            showExitDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 退出Dialog
     */
    private void showExitDialog() {
        DialogFactory.showMsgDialog(this, getString(R.string.dialog_title_exit), getString(R.string.exit_msg) + getString(R.string.app_name) + "?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.gc();
                IActivityManage.getInstance().exit();
                System.exit(0);
            }
        });
    }
}
