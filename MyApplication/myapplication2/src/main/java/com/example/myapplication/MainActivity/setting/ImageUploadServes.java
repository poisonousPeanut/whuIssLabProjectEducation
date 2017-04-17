package com.example.myapplication.MainActivity.setting;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by 小妖王 on 2017/4/16.
 */
public interface ImageUploadServes {
    @POST("lu/ImageServlet")
    @Multipart
    Call<String> uploadImage(@Part("fileName") String uid , @Part("file\";filename=\"1.jpg") RequestBody file);
}
