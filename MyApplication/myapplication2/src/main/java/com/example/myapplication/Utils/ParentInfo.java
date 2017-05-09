package com.example.myapplication.Utils;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 60440 on 2017/2/9.
 */

public class ParentInfo implements Serializable {
    public static String uid;
    public static int id;
    public static String username;
    public static String password;
    public static String nickname;
    public static String email;
    public static String number;
    public static String realname;
    public static String region;
    public static String phone;
    public static String signature;
    public static String imageURL;
    public static String gender;
    public static String introduction;
    public static String token;

    public static String getIdentity() {
        return identity;
    }

    public static void setIdentity(String identity) {
        ParentInfo.identity = identity;
    }

    public static String identity;

    public static int lookOverIdNow=8;

    public static String getTitleNow() {
        return titleNow;
    }

    public static void setTitleNow(String titleNow) {
        ParentInfo.titleNow = titleNow;
    }

    public static String titleNow;


    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ParentInfo.id = id;
    }

    public static ArrayList<String> groupNames = new ArrayList<>();
    public static Uri getPrivateChatUri(String uid, String title){
        return Uri.parse(Constant.URI_CONVERSATION_PRIVATE+uid+"&title="+title);
    }
    public static String getIdentity(String uid){
        String identity = "";
        switch (uid.charAt(0)) {
            case 's':
                identity = "学生";
                break;
            case 't':
                identity = "老师";
                break;
            case 'c':
                identity = "大学生";
                break;
            case 'a':
                identity = "家长";
                break;
            default:
                break;
        }
        return identity;
    }
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ParentInfo.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ParentInfo.password = password;
    }

    public static String getNickname() {
        return nickname;
    }

    public static void setNickname(String nickname) {
        ParentInfo.nickname = nickname;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ParentInfo.email = email;
    }

    public static String getNumber() {
        return number;
    }

    public static void setNumber(String number) {
        ParentInfo.number = number;
    }

    public static String getRealname() {
        return realname;
    }

    public static void setRealname(String realname) {
        ParentInfo.realname = realname;
    }

    public static String getRegion() {
        return region;
    }

    public static void setRegion(String region) {
        ParentInfo.region = region;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        ParentInfo.phone = phone;
    }

    public static String getSignature() {
        return signature;
    }

    public static void setSignature(String signature) {
        ParentInfo.signature = signature;
    }

    public static String getImageURL() {
        return imageURL;
    }

    public static void setImageURL(String imageURL) {
        ParentInfo.imageURL = imageURL;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        ParentInfo.gender = gender;
    }

    public static String getIntroduction() {
        return introduction;
    }

    public static void setIntroduction(String introduction) {
        ParentInfo.introduction = introduction;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        ParentInfo.token = token;
    }

    public static int getLookOverIdNow() {
        return lookOverIdNow;
    }

    public static void setLookOverIdNow(int lookOverIdNow) {
        ParentInfo.lookOverIdNow = lookOverIdNow;
    }
    public static Uri getGroupChatUri(String gid,String title){
        return Uri.parse(Constant.URI_CONVERSATION_GROUP+gid+"&title="+title);
    }


}


