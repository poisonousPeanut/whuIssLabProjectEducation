package com.example.myapplication.LinkmanActivity;

/**
 * Created by fate on 2016/11/16.
 */

public interface Constant {
    int DATAS_ONCE = 10;
    int REQURST_SETTING = 100;
    int REQURST_SEARCH = 101;
    int REQURST_ANSWER = 102;
    int REQURST_ASK = 103;
    String SERVER_URL = "http://60.205.190.45:8080/education/";
    String PREFERENCE_USERINFO = "UserInfo";
    long CACHE_DURATION = 0;
    String URI_CONVERSATION_PRIVATE = "rong://com.example.a60440.collegestudent/conversation/private?targetId=";
    int IMAGE_RADIUS = 5;
    //判断是否需要手动登录
    String KEY_NEED_LOGIN = "needlogin";
    final String INTERNET_FAIL="网络异常，请重试";
    String URI_CONVERSATION_GROUP = "rong://hl.iss.whu.edu.laboratoryproject/conversation/group?targetId=";
}
