package com.example.myapplication.lookOver;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 60440 on 2016/11/29.
 */

public interface AddQuestionRequestServes {
    @POST("servletc/AddQuestionServlet")
    @FormUrlEncoded
    Call<String> getString(@Field("userId") int id,
                           @Field("question") String question
    );

    @POST("servletc/AddQuestionServlet")
    @FormUrlEncoded
    Call<String> submitQuestion(@Field("title") String title, @Field("content") String content, @Field("anonymous") boolean anonymous, @Field("id") int id);
}
