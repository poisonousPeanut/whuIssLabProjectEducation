package com.example.myapplication.LogInActivity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2016/11/29.
 */

public interface RegisterServes {
    @POST("lu/RegisterServlet")
    Call<String> getString(@Query("username") String username, @Query("password") String password);
}
