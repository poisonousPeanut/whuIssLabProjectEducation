package com.example.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import io.rong.imkit.RongIM;

/**
 * Created by 小妖王 on 2017/2/14.
 */

public class MyApplication extends Application {
    private static MyApplication instance ;
    private static Context context;
    private static int mainThreadId;
    private static Handler handler;

    public List<Activity> getActivityList() {
        return activityList;
    }

    private List<Activity> activityList = new LinkedList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RongIM.init(this);
        handler = new Handler(getMainLooper());
        mainThreadId = Process.myTid();
        context =getApplicationContext();
        Log.e(getClass().getSimpleName(), "onCreate: "+getApplicationContext().getPackageName() );
    }

    public static MyApplication getInstance(){
        // 因为我们程序运行后，Application是首先初始化的，如果在这里不用判断instance是否为空
        return instance;
    }

    public static Context getContext(){
        return context;
    }
    public static Handler getHandler(){
        return handler;
    }
    public static int getMainThreadId(){
        return mainThreadId;
    }

    //添加Activity到容器中
    public void addActivity(Activity activity)
    {
        activityList.add(activity);
    }
    //遍历所有Activity并finish
    public void exit()
    {
        for(Activity activity:activityList)
        {
            activity.finish();
        }
        System.exit(0);
    }
}
