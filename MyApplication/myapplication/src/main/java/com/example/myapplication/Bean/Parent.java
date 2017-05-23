package com.example.myapplication.Bean;

import java.io.Serializable;

/**
 * Created by 小妖王 on 2017/3/16.
 */

public class Parent implements Serializable {
//    public static String uid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int id;
    public String identity;
    public String username;
    public String password;
    public String nickname;
    public String email;
    public String number;
    public String realname;
    public String region;
    public String phone;
    public String signature;
    public String imageUrl;
    public String gender;
    public String introduction;
    public String token;

    public boolean isHasAccountFlag() {
        return hasAccountFlag;
    }

    public void setHasAccountFlag(boolean hasAccountFlag) {
        this.hasAccountFlag = hasAccountFlag;
    }

    private boolean hasAccountFlag;

    public boolean isPasswordWriteFlag() {
        return passwordWriteFlag;
    }

    public void setPasswordWriteFlag(boolean passwordWriteFlag) {
        this.passwordWriteFlag = passwordWriteFlag;
    }

    private boolean passwordWriteFlag;
//    private String major;
//    private String classname;
//    private String school;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

//    public String getMajor() {
//        return major;
//    }
//
//    public void setMajor(String major) {
//        this.major = major;
//    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    //    public String getClassname() {
//        return classname;
//    }
//    public void setClassname(String classname) {
//        this.classname = classname;
//    }
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    //    public String getSchool() {
//        return school;
//    }
//    public void setSchool(String school) {
//        this.school = school;
//    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signture) {
        this.signature = signture;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Parent{" +
//                "classname='" + classname + '\'' +
                ", id='" + id + '\'' +
//                ", identity='" + identity + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", realname='" + realname + '\'' +
                ", region='" + region + '\'' +
//                ", school='" + school + '\'' +
                ", phone='" + phone + '\'' +
                ", signature='" + signature + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

