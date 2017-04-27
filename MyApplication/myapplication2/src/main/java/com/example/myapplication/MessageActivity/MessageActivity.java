package com.example.myapplication.MessageActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.MyApplication;

public class MessageActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    public Toolbar getToolbar() {
        return toolbar;
    }

    private Toolbar toolbar;


    public void setToolbarTitle(String titleContent) {
        this.toolbarTitle.setText(titleContent);
    }

    private TextView toolbarTitle;
    private BottomTagFragment2 bottomTag;//主页面底部标签栏

    public Fragment getBottomTag() {
        return bottomTag;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        MyApplication.getInstance().addActivity(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitle=(TextView)findViewById(R.id.toolbarTitle);
//        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbarTitle.setText("消息");
        setSupportActionBar(toolbar);

        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        bottomTag = new BottomTagFragment2();
        mTransaction.replace(R.id.bottomTag2, bottomTag);
        mTransaction.commit();
    }

    @Override
    protected void onResume() {
        bottomTag.getSecond_rad().setChecked(true);
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.add_friend:
                //加上加好友的逻辑
                break;
            case R.id.scace:
                //加上扫一扫
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
//        updateData();
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            //do nothing 不返回上一个activity
            new AlertDialog.Builder(this).setTitle("确认退出吗？")
                    .setIcon(R.drawable.ic_info_outline_red_500_24dp)
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
}
