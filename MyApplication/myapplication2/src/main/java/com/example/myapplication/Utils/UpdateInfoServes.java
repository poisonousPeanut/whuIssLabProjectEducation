package com.example.myapplication.Utils;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 60440 on 2017/2/12.
 */

public interface UpdateInfoServes {
    @POST("lu/UpdateParentServlet")
    @FormUrlEncoded
    Call<String> getString(@Field("id") int id,
                           @Field("nickname") String nickname,
                           @Field("password") String password,
                           @Field("region") String region,
                           @Field("signature") String signature,
                           @Field("imageURL") String imageURL,
                           @Field("gender") String gender,
                           @Field("introduction") String introduction,
                           @Field("email") String email,
                           @Field("number")String number,
                           @Field("realname")String realname,
                           @Field("phone")String phone,
                           @Field("token")String token,
                           @Field("identity")String identity,
                           @Field("username")String username
    );

}
