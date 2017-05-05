package com.jinhui.easy.module.vote.data.source;

import com.jinhui.easy.module.vote.data.VoteEntity;

import java.util.List;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/15 14:22
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface VoteDataSource {

    interface VoteCallBack {
        void onVoteSuccess(List<VoteEntity> entities);

        void onVoteFailure(int code, String msg);
    }

    void requestVoteList(VoteCallBack callBack);

    interface CarridVoteCallBack{
        void onCarridVoteStart();
        void onCarridVoteEnd();
        void onCarridVoteSuccess();
        void onCarridVoteFailure();
    }
    void carridVote(int id,int voteId,CarridVoteCallBack callBack);
}
