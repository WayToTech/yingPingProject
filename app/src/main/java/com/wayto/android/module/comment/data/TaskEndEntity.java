package com.wayto.android.module.comment.data;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/27 10:09
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class TaskEndEntity {


    /**
     * title : 车俊任浙江省委书记 夏宝龙不再担任(图/简历)
     * websiteid : 2
     * tasktype : 评论
     * integral : 12
     * requirement : 12321
     * taskphoto : /uploadWlzgFiles/easycomment/20170426/2017042620195724.png
     * executiontime : 2017-04-26 20:19:57
     */

    private String title;
    private int websiteid;
    private String tasktype;
    private String integral;
    private String requirement;
    private String taskphoto;
    private String executiontime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWebsiteid() {
        return websiteid;
    }

    public void setWebsiteid(int websiteid) {
        this.websiteid = websiteid;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getTaskphoto() {
        return taskphoto;
    }

    public void setTaskphoto(String taskphoto) {
        this.taskphoto = taskphoto;
    }

    public String getExecutiontime() {
        return executiontime;
    }

    public void setExecutiontime(String executiontime) {
        this.executiontime = executiontime;
    }
}
