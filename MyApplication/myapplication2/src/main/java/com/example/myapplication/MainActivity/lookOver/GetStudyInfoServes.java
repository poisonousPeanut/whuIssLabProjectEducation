package com.example.myapplication.MainActivity.lookOver;

import com.example.myapplication.Bean.DayStudyInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 小妖王 on 2017/3/18.
 */

public interface GetStudyInfoServes {
    @GET("servlet/ParentGetStudyInfo")
    Call<ArrayList<DayStudyInfo>> getStudyInfo(@Query("id") int id);
}
