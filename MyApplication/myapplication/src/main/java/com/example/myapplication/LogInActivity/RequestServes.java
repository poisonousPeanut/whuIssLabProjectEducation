package com.example.myapplication.LogInActivity;

import com.example.myapplication.Bean.Parent;
import com.example.myapplication.LinkmanActivity.Info;
import com.example.myapplication.LinkmanActivity.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 小妖王 on 2017/3/12.
 */
public interface RequestServes {
    @POST("lu/LoginServlet")
    Call<Parent> getString(@Query("username") String loginname, @Query("password") String loginpwd);

    //获得联系人信息
    @GET("GetUserInfoServlet")
    Call<Info> getUserInfo(@Query("quid") String queryUid, @Query("muid") String myUid);

    @GET("RequestTokenServlet")
    Call<Result> requestToken(@Query("uid") String uid);
}