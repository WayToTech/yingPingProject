package com.jinhui.easy.module.vote.data;

import java.io.Serializable;
import java.util.List;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/15 14:21
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class VoteEntity implements Serializable{


    /**
     * voteId : 5
     * title : teaaaa
     * voteData : [{"id":1,"voteid":5,"name":"张三","ballot":"2"},{"id":2,"voteid":5,"name":"李四","ballot":"0"},{"id":3,"voteid":5,"name":"王五","ballot":"0"}]
     */

    private int voteId;
    private String title;
    private List<VoteDataBean> voteData;

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VoteDataBean> getVoteData() {
        return voteData;
    }

    public void setVoteData(List<VoteDataBean> voteData) {
        this.voteData = voteData;
    }

    public static class VoteDataBean implements Serializable{
        /**
         * id : 1
         * voteid : 5
         * name : 张三
         * ballot : 2
         */

        private int id;
        private int voteid;
        private String name;
        private String ballot;
        private boolean isSelecte;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVoteid() {
            return voteid;
        }

        public void setVoteid(int voteid) {
            this.voteid = voteid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBallot() {
            return ballot;
        }

        public void setBallot(String ballot) {
            this.ballot = ballot;
        }

        public void setSelecte(boolean b){
            this.isSelecte=b;
        }

        public boolean isSelecte(){
            return isSelecte;
        }
    }
}
