package com.example.myapplication.LogInActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.Utils.MyApplication;
import com.example.myapplication.R;

import static com.example.myapplication.Utils.MyUtils.hideSoftKeyboard;

/**
 * Created by 小妖王 on 2017/3/1.
 */

public class LogInActivity extends Activity {
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private Fragment logInFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_login);
        initView();
        setupUI(findViewById(R.id.activity_login));
    }

    private void initView() {
        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        logInFragment = new LogInFragment();
        mTransaction.add(R.id.logIn_content, logInFragment,"LogInFragment");
        mTransaction.commit();
    }

    public void setupUI(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LogInActivity.this);  //Main.this是我的activity名
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            new AlertDialog.Builder(this).setTitle("确认退出吗？")
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“确认”后的操作
                            //MainActivity.this.finish();
                            //MainActivity.super.onBackPressed();//结束当前activity
                            //System.exit(0);
                            MyApplication.getInstance().exit();
                        }
                    })
                    .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“返回”后的操作,这里不设置没有任何操作
                        }
                    }).show();
        }
//        super.onBackPressed();
    }
//    //接收退出广播
//    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//
//
//        @Override
//        public void onReceive(Context arg0, Intent arg1) {
//            // TODO Auto-generated method stub
//            finish();
//        }
//    };
//
//    @Override
//    protected void onResume() {
//// TODO Auto-generated method stub
//        super.onResume();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("ExitApp");
//        this.registerReceiver(broadcastReceiver, filter);
//        unregisterReceiver(broadcastReceiver);
//    }

}
