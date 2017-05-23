package com.example.myapplication.lookOver;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 60440 on 2017/2/13.
 */

public interface AddAnswer {
    @POST("servletc/AddAnswerServlet")
    @FormUrlEncoded
    Call<String> getString(@Field("id") String id,
                           @Field("userId") String userId,
                           @Field("content") String content
    );

    @POST("WriteAnswerServlet")
    @FormUrlEncoded
    Call<String> submitAnswer(@Field("content") String content, @Field("anonymous") boolean anonymous, @Field("iid") int iid, @Field("uid") String uid);
}
