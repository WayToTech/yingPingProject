package com.jinhui.easy.module.ranking.data;

/**
 * author: Haobo Chen <hopeseebok@gmail.com>
 * version: V1.0
 * created at 2017/3/28 18:17
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface RepoCallBack<T> {

    void onDataAvailable(T data);

    void onDataNotAvailable(int code, String msg);

}

