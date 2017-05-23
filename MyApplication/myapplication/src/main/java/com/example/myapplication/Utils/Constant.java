package com.example.myapplication.Utils;

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
    String PREFERENCE_USERINFO = "ParentInfo";
    long CACHE_DURATION = 0;
    String URI_CONVERSATION_PRIVATE = "rong://com.example.myapplication/conversation/private?targetId=";
    String URI_CONVERSATIONLIST = "rong://com.example.myapplication/conversationlist";
    int IMAGE_RADIUS = 5;
    //判断是否需要手动登录
    String KEY_NEED_LOGIN = "needlogin";
    final String INTERNET_FAIL = "网络异常，请重试";
    String URI_CONVERSATION_GROUP = "rong://com.example.myapplication/conversation/group?targetId=";
    public static final String imagesUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486567132953&di=1d9c597fcfb457a754958434a9feb631&imgtype=0&src=http%3A%2F%2Fimage95.360doc.com%2FDownloadImg%2F2016%2F02%2F2719%2F66765107_17.jpg";
}
