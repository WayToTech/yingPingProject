package com.jinhui.easy.module.comment.data;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 14:20
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class TaskEntity {


    /**
     * id : 1
     * title : 测试1
     * taskurl : null
     * tasktype : 转发
     * integral : 2
     * completiontime : 2017-1-1
     * status : 待完成
     */

    private int id;
    private String title;
    private String taskurl;
    private String tasktype;
    private String integral;
    private String completiontime;
    private String status;
    private String forwardingicon;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskurl() {
        return taskurl;
    }

    public void setTaskurl(String taskurl) {
        this.taskurl = taskurl;
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

    public String getCompletiontime() {
        return completiontime;
    }

    public void setCompletiontime(String completiontime) {
        this.completiontime = completiontime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getForwardingicon() {
        return forwardingicon;
    }

    public void setForwardingicon(String forwardingicon) {
        this.forwardingicon = forwardingicon;
    }
}
