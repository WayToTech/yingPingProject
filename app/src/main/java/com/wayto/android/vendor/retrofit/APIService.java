package com.wayto.android.vendor.retrofit;

import com.wayto.android.BuildConfig;
import com.wayto.android.entity.ResponseModel;
import com.wayto.android.module.accountFuncation.data.ModifyHeadEntity;
import com.wayto.android.module.accountFuncation.data.ModifyPasswordEntity;
import com.wayto.android.module.accountFuncation.data.UserInfoEntity;
import com.wayto.android.module.comment.data.TaskDetailsEntity;
import com.wayto.android.module.comment.data.TaskEndEntity;
import com.wayto.android.module.comment.data.TaskEntity;
import com.wayto.android.module.conference.data.ConferenceDetailsEntity;
import com.wayto.android.module.conference.data.ConferenceEntity;
import com.wayto.android.module.home.data.HomeEntity;
import com.wayto.android.module.mainFuncations.data.QiNiuTokenEntity;
import com.wayto.android.module.notice.data.AppVersionEntity;
import com.wayto.android.module.notice.data.NoticeDetailsEntity;
import com.wayto.android.module.notice.data.NoticeEntity;
import com.wayto.android.module.ranking.data.RankingEntity;
import com.wayto.android.module.vote.data.VoteEntity;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 请求API接口配制
 * <p>
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/3/14 10:43
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public interface APIService {
    /**
     * 登录
     *
     * @param url
     */
    @GET
    Call<ResponseModel<String>> loginRepo(@Url String url);

    /**
     * 主界面
     * <p>
     * author: hezhiWu
     * created at 2017/4/13 0:37
     */
    @GET
    Call<ResponseModel<HomeEntity>> getMember(@Url String url);

    /**
     * 消息报送
     * <p>
     * author: hezhiWu
     * created at 2017/4/24 20:41
     */
    @Multipart
    @POST
    Call<ResponseModel> recordMsg(@Url String url, @PartMap Map<String, RequestBody> params);

    /**
     * 会务列表
     * <p>
     * author: hezhiWu
     * created at 2017/4/13 0:38
     */
    @GET
    Call<ResponseModel<List<ConferenceEntity>>> getMeetingNotice(@Url String url);

    /**
     * 会务详情
     * <p>
     * author: hezhiWu
     * created at 2017/4/13 15:52
     */
    @GET
    Call<ResponseModel<ConferenceDetailsEntity>> getMeetingNoticeDetails(@Url String url);

    /**
     * 公告列表
     * <p>
     * author: hezhiWu
     * created at 2017/4/13 10:36
     */
    @GET
    Call<ResponseModel<List<NoticeEntity>>> getNoticeList(@Url String url);

    /**
     * 任务详情
     * <p>
     * author: hezhiWu
     * created at 2017/4/13 15:35
     */
    @GET
    Call<ResponseModel<NoticeDetailsEntity>> getNoticeDetails(@Url String url);

    /**
     * 任务列表
     * <p>
     * author: hezhiWu
     * created at 2017/4/13 14:34
     */
    @GET
    Call<ResponseModel<List<TaskEntity>>> getCommentList(@Url String url);

    /**
     * 任务详情
     * <p>
     * author: hezhiWu
     * created at 2017/4/13 20:59
     */
    @GET
    Call<ResponseModel<TaskDetailsEntity>> getTaskDetails(@Url String url);

    /**
     * 上传任务
     * <p>
     * author: hezhiWu
     * created at 2017/4/25 12:39
     */
    @Multipart
    @POST
    Call<ResponseModel> recordTask(@Url String url, @PartMap Map<String, RequestBody> params);

    @GET
    Call<ResponseModel<TaskEndEntity>> getdetailTaskEndById(@Url String url);
    /**
     * 获取排名列表
     * <p>
     * author: hezhiWu
     * created at 2017/4/27 9:32
     */
    @GET
    Call<ResponseModel<RankingEntity>> getRanking(@Url String url);

    /**
     * 投票列表
     * <p>
     * author: hezhiWu
     * created at 2017/4/15 14:25
     */
    @GET
    Call<ResponseModel<List<VoteEntity>>> getVoteList(@Url String url);

    @GET
    Call<ResponseModel> arridVote(@Url String url);

    /**
     * 修改用户信息
     *
     * @param entity
     * @return
     */
    @POST(BuildConfig.MODIFY_USER_HEAD_URL + "{id}")
    Call<ResponseModel> modifyUserInfo(@Body ModifyHeadEntity entity, @Path("id") String id);

    /**
     * 修改用户密码
     *
     * @param entity
     * @return
     */
    @PUT(BuildConfig.MODIFY_USER_PWD_URL + "{id}")
    Call<ResponseModel> modifyUserPassword(@Body ModifyPasswordEntity entity, @Path("id") String id);

    /**
     * 请求七牛Token
     *
     * @return
     */
    @GET(BuildConfig.QINIU_TOKEN_URL)
    Call<ResponseModel<QiNiuTokenEntity>> reqQiniuToken();

    /**
     * 版本检测
     *
     * @return
     */
    @GET(BuildConfig.CHECK_APP_VERSION_URL)
    Call<ResponseModel<AppVersionEntity>> checkAppVersion();

    /**
     * 文件下载
     *
     * @param url
     * @return
     */
    @GET
    Call<ResponseBody> downloadFile(@Url String url);

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @Multipart
    @POST("")
    Call<ResponseModel> uploadFile(@Part MultipartBody.Part file);

    /**
     * 轮询资源上传
     *
     * @param body
     * @return
     */
    @POST
    Call<ResponseModel> uploadPollingData(@Url String url, @Body RequestBody body);

}
