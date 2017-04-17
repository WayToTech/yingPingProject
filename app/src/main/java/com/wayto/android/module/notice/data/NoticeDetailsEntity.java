package com.wayto.android.module.notice.data;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 15:32
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class NoticeDetailsEntity {


    /**
     * id : 1
     * title : test
     * content : assdda
     * releasetime : 2017-04-11 18:58:37
     * status : 发布
     * createid : 1
     * createtime : 2017-04-11 18:58:45
     * modifyid : null
     * modifytime : null
     */

    private int id;
    private String title;
    private String content;
    private String releasetime;
    private String status;
    private int createid;
    private String createtime;
    private Object modifyid;
    private Object modifytime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
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

    public Object getModifyid() {
        return modifyid;
    }

    public void setModifyid(Object modifyid) {
        this.modifyid = modifyid;
    }

    public Object getModifytime() {
        return modifytime;
    }

    public void setModifytime(Object modifytime) {
        this.modifytime = modifytime;
    }
}
