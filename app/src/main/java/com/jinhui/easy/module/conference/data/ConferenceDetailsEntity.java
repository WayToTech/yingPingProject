package com.jinhui.easy.module.conference.data;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/13 15:48
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class ConferenceDetailsEntity {


    /**
     * id : 7
     * title : 我的通知
     * content : 一）审议通过了关于取消原《2016年度利润分配方案》的议案； 公司高度重视监管部门的监管理念和监管导向，为切实维护广大股东利益特 别是保护中小股东权益，经公司建议并经公司控股股东浙江巨龙控股集团有限公 司（以下简称“巨龙控股”）
     * releasetime : 2017-04-12 00:00:00
     * status : 发布
     * address : 宁波市会议中心
     * createid : 31
     * createtime : 2017-04-27 13:14:47
     * modifyid : 31
     * modifytime : 2017-04-27 13:16:22
     */

    private int id;
    private String title;
    private String content;
    private String releasetime;
    private String status;
    private String address;
    private int createid;
    private String createtime;
    private int modifyid;
    private String modifytime;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
