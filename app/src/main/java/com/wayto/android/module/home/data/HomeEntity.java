package com.wayto.android.module.home.data;

import java.util.List;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-04-08
 * Time: 12:07
 * Version:1.0
 */

public class HomeEntity {


    /**
     * realName : 张三
     * departmentName : test
     * integral : 10
     * noticeDate : [{"id":1,"title":"test","releasetime":"2017-04-11 18:58:37"},{"id":2,"title":"测试","releasetime":"2017-04-11 20:21:52"}]
     * meetingNoticeDate : [{"id":1,"title":"会务通知标题","releasetime":"2017-04-11 20:20:32"},{"id":2,"title":"会务通知标题2","releasetime":"2017-04-11 20:21:04"}]
     * taskDate : [{"id":1,"title":"测试1","taskurl":null,"tasktype":"转发","integral":"2","completiontime":"2017-1-1","status":"待完成"},{"id":2,"title":"测试2","taskurl":null,"tasktype":"转发","integral":"2","completiontime":"2017-1-1","status":"待完成"},{"id":3,"title":"测试3","taskurl":null,"tasktype":"转发","integral":"12","completiontime":"2017-1-1","status":"已完成"},{"id":4,"title":"测试4","taskurl":null,"tasktype":"转发","integral":"3","completiontime":"2017-1-1","status":"已完成"}]
     */

    private String realName;
    private String departmentName;
    private String integral;
    private List<NoticeDateBean> noticeDate;
    private List<MeetingNoticeDateBean> meetingNoticeDate;
    private List<TaskDateBean> taskDate;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public List<NoticeDateBean> getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(List<NoticeDateBean> noticeDate) {
        this.noticeDate = noticeDate;
    }

    public List<MeetingNoticeDateBean> getMeetingNoticeDate() {
        return meetingNoticeDate;
    }

    public void setMeetingNoticeDate(List<MeetingNoticeDateBean> meetingNoticeDate) {
        this.meetingNoticeDate = meetingNoticeDate;
    }

    public List<TaskDateBean> getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(List<TaskDateBean> taskDate) {
        this.taskDate = taskDate;
    }

    public static class NoticeDateBean {
        /**
         * id : 1
         * title : test
         * releasetime : 2017-04-11 18:58:37
         */

        private int id;
        private String title;
        private String releasetime;

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

        public String getReleasetime() {
            return releasetime;
        }

        public void setReleasetime(String releasetime) {
            this.releasetime = releasetime;
        }
    }

    public static class MeetingNoticeDateBean {
        /**
         * id : 1
         * title : 会务通知标题
         * releasetime : 2017-04-11 20:20:32
         */

        private int id;
        private String title;
        private String releasetime;

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

        public String getReleasetime() {
            return releasetime;
        }

        public void setReleasetime(String releasetime) {
            this.releasetime = releasetime;
        }
    }

    public static class TaskDateBean {
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
        private Object taskurl;
        private String tasktype;
        private String integral;
        private String completiontime;
        private String status;

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

        public Object getTaskurl() {
            return taskurl;
        }

        public void setTaskurl(Object taskurl) {
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
    }
}
