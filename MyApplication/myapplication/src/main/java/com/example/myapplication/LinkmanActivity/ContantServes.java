package com.example.myapplication.LinkmanActivity;


import com.example.myapplication.Bean.Student;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 60440 on 2017/2/17.
 */

public interface ContantServes {
    @GET("QueryContactsServlet")
    Call<ArrayList<QueryItem>> queryContacts(@Query("uid") String uid, @Query("query") String query);

    @GET("GetFriendRequestServlet")
    Call<ArrayList<FriendRequest>> getFriendRequest(@Query("uid") String uid);

    @GET("GetRequestCountServlet")
    Call<Result> getRequestCount(@Query("uid") String uid);

    @POST("AddFriendServlet")
    @FormUrlEncoded
    Call<Result> addFriend(@Field("rid") int rid, @Field("group") String group, @Field("remark") String remark);

    @POST("RequestFriendServlet")
    @FormUrlEncoded
    Call<Result> requestFriend(@Field("quid") String quid, @Field("muid") String muid, @Field("group") String group, @Field("remark") String remark, @Field("message") String message);

    @GET("GetGroupNamesServlet")
    Call<ArrayList<String>> getGroupNames(@Query("uid") String uid);

    @GET("GetContactsServlet")
    Call<ArrayList<RosterGroup>> getContacts(@Query("uid") String uid);

    @GET("GetDetailedInfoServlet")
    Call<InfoDetail> getDetailedInfo(@Query("uid") String uid);

    @POST("servletc/LoadUnpairStudentServlet")
    Call<ArrayList<Student>> getUnpairStudent(@Query("start") int start);

    @POST("servletc/AddPairStudentServlet")
    @FormUrlEncoded
    Call<String> addPairStudent(@Field("studentId") String sid, @Field("collegeStudentId") String cid);

    @POST("lu/GetPairedStudent")
    Call<ArrayList<Student>> getStudentInfo(@Query("parentId") String aid);

    @GET("GetGroupsServlet")
    Call<ArrayList<ChatGroup>> getGroups(@Query("uid") String uid);


}
