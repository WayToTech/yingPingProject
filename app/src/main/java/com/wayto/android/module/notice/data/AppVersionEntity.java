package com.wayto.android.module.notice.data;

import java.io.Serializable;

/**
 * 我的主界面
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:37
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class AppVersionEntity implements Serializable {

    private int Ver;
    private String Note;
    private String Url;
    private int Level;

    public int getVer() {
        return Ver;
    }

    public void setVer(int ver) {
        Ver = ver;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }
}
