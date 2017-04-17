package com.wayto.android.module.comment.data;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 20:37
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class TaskDetailsEntity {


    /**
     * id : 1
     * title : 测试1
     * taskurl : http://www.baidu.com
     * websiteid : 12
     * tasktype : 转发
     * forwardingicon : null
     * integral : 2
     * requirement : 1
     * completiontime : 2017-1-1
     * departmentid : 1
     * notification : null
     * status : 下发
     * createid : null
     * createtime : null
     * modifyid : null
     * modifytime : null
     */

    private int id;
    private String title;
    private String taskurl;
    private int websiteid;
    private String tasktype;
    private String forwardingicon;
    private String integral;
    private String requirement;
    private String completiontime;
    private int departmentid;
    private String notification;
    private String status;
    private int createid;
    private String createtime;
    private int modifyid;
    private String modifytime;
    private String content;

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

    public String getForwardingicon() {
        return forwardingicon;
    }

    public void setForwardingicon(String forwardingicon) {
        this.forwardingicon = forwardingicon;
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

    public String getCompletiontime() {
        return completiontime;
    }

    public void setCompletiontime(String completiontime) {
        this.completiontime = completiontime;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreateid() {
        return createid;
    }

    public void setCreateid(int createid) {
        this.createid = createid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getModifyid() {
        return modifyid;
    }

    public void setModifyid(int modifyid) {
        this.modifyid = modifyid;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
