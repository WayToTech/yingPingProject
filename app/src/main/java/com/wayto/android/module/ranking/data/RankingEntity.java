package com.wayto.android.module.ranking.data;

import java.util.List;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/27 9:29
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class RankingEntity {


    private List<IntegralDataBean> integralData;
    private List<TaskDataBean> taskData;

    public List<IntegralDataBean> getIntegralData() {
        return integralData;
    }

    public void setIntegralData(List<IntegralDataBean> integralData) {
        this.integralData = integralData;
    }

    public List<TaskDataBean> getTaskData() {
        return taskData;
    }

    public void setTaskData(List<TaskDataBean> taskData) {
        this.taskData = taskData;
    }

    public static class IntegralDataBean {
        /**
         * memberid : null
         * departmentid : null
         * departmentname : 我的中队1
         * realname : null
         * memberintegral : null
         * departmenintegral : 0
         */

        private Object memberid;
        private Object departmentid;
        private String departmentname;
        private Object realname;
        private Object memberintegral;
        private String departmenintegral;

        public Object getMemberid() {
            return memberid;
        }

        public void setMemberid(Object memberid) {
            this.memberid = memberid;
        }

        public Object getDepartmentid() {
            return departmentid;
        }

        public void setDepartmentid(Object departmentid) {
            this.departmentid = departmentid;
        }

        public String getDepartmentname() {
            return departmentname;
        }

        public void setDepartmentname(String departmentname) {
            this.departmentname = departmentname;
        }

        public Object getRealname() {
            return realname;
        }

        public void setRealname(Object realname) {
            this.realname = realname;
        }

        public Object getMemberintegral() {
            return memberintegral;
        }

        public void setMemberintegral(Object memberintegral) {
            this.memberintegral = memberintegral;
        }

        public String getDepartmenintegral() {
            return departmenintegral;
        }

        public void setDepartmenintegral(String departmenintegral) {
            this.departmenintegral = departmenintegral;
        }
    }

    public static class TaskDataBean {
        /**
         * realName : 测试员1
         * taskSize : 3
         * mobile : 13567897396
         */

        private String realName;
        private String taskSize;
        private String mobile;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getTaskSize() {
            return taskSize;
        }

        public void setTaskSize(String taskSize) {
            this.taskSize = taskSize;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
