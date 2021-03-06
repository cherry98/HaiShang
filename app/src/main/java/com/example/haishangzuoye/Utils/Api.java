package com.example.haishangzuoye.Utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> register(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("editPwd")
    Call<ResponseBody> editPwd(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("fabuTask")
    Call<ResponseBody> fabuTask(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("fileImg")
    Call<ResponseBody> fileImg(@Field("vars") String vars);

    @POST("taskTypeList")
    Call<ResponseBody> taskTypeList();

    @FormUrlEncoded
    @POST("editUserData")
    Call<ResponseBody> editUserData(@Field("vars") String vars);//个人资料信息

    @FormUrlEncoded
    @POST("taskList")
    Call<ResponseBody> taskList(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("overTask")
    Call<ResponseBody> overTask(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("acceptTask")
    Call<ResponseBody> acceptTask(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("addMessage")
    Call<ResponseBody> addMessage(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("userTaskList")
    Call<ResponseBody> userTaskList(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("setSign")
    Call<ResponseBody> setSign(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("signList")
    Call<ResponseBody> signList(@Field("vars") String vars);

    @POST("taskAllMessageList")
    Call<ResponseBody> taskAllMessageList();

    @FormUrlEncoded
    @POST("userAllTaskList")
    Call<ResponseBody> userAllTaskList(@Field("vars") String vars);


    @FormUrlEncoded
    @POST("taskMessageList")
    Call<ResponseBody> taskMessageList(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("getUserAllTask")
    Call<ResponseBody> getUserAllTask(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("addLog")
    Call<ResponseBody> addLog(@Field("vars") String vars);

    @FormUrlEncoded
    @POST("logList")
    Call<ResponseBody> logList(@Field("vars") String vars);
}
