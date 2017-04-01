package com.example.myapplication.Utils;

import android.app.Activity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by 小妖王 on 2017/3/18.
 */

public class MyUtils {
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MyUtils", "exception again!");
        }
    }

    public static String transTime(long ms){
        java.lang.String string;
        long l = ms/1000;
        string= l/3600+"小时"+(l%3600)/60+"分钟"+1%60;
        return string;
    }
}
