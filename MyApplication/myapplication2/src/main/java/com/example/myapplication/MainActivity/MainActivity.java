package com.example.myapplication.MainActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.MainActivity.lookOver.FirstFragment;
import com.example.myapplication.R;
import com.example.myapplication.Utils.MyApplication;
import com.example.myapplication.LinkmanActivity.ParentInfo;
import com.example.myapplication.Utils.UpdateInfoServes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.myapplication.Utils.MyUtils.hideSoftKeyboard;


public class MainActivity extends AppCompatActivity {
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
    private BottomTagFragment bottomTag;//主页面底部标签栏

    public Fragment getBottomTag() {
        return bottomTag;
    }

    private Fragment firstFragment;

    private Fragment currentFragment;
    public void setCurrentFragment(Fragment fragment){
        currentFragment = fragment;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }
    //    public Parent getAParent() {
//        return parent;
//    }
//
//    private Parent parent;//个人资料界面性别显示


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        MyApplication.getInstance().addActivity(this);
        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();

        bottomTag = new BottomTagFragment();
        mTransaction.add(R.id.bottomTag, bottomTag);
        mTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitle=(TextView)findViewById(R.id.toolbarTitle);
        //toolbar.setSubtitle("子标题");
        toolbar.setLogo(R.mipmap.ic_launcher); //设置App的logo
        setSupportActionBar(toolbar);

        initView();
//        setupUI(findViewById(R.id.activity_main));
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

//    @Override
//    protected void onResume() {
//        Bundle bundle = this.getIntent().getExtras();
//        //接收name值
//        try{
//        int bottomNo = bundle.getInt("bottomNo");
//            Log.e("MainActivity","onResume:获取到的name值为"+bottomNo);
//            if (bottomNo==1){
//                bottomTag.getFirst_rad().setChecked(true);
//            }
//            if(bottomNo==4){
//                bottomTag.getFourth_rad().setChecked(true);
//            }
//        }catch (Exception E){
//            Log.e("MainActivity", "onResume: FIRST CREAT");
//            E.printStackTrace();
//        }
//
//        super.onResume();
//    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        try{
            int bottomNo = bundle.getInt("bottomNo");
            Log.e("MainActivity","onResume:获取到的name值为"+bottomNo);
            if (bottomNo==1){
                bottomTag.getFirst_rad().setChecked(true);
            }
            if(bottomNo==4){
                bottomTag.getFourth_rad().setChecked(true);
            }
        }catch (Exception E){
            Log.e("MainActivity", "onResume: FIRST CREAT");
            E.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        updateData();
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
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

    public void updateData() {
        Log.e("test", "updateData: enter");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UpdateInfoServes update = retrofit.create(UpdateInfoServes.class);
        Call<String> call = update.getString(
                ParentInfo.id,
                ParentInfo.nickname,
                ParentInfo.password,
                ParentInfo.region,
                ParentInfo.signature,
                ParentInfo.imageURL,
                ParentInfo.gender,
                ParentInfo.introduction,
                ParentInfo.email,
                ParentInfo.number,
                ParentInfo.realname,
                ParentInfo.phone,
                ParentInfo.token,
                ParentInfo.identity,
                ParentInfo.username
        );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(ChangePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                if (response.body() != null) {
                    if (response.body().equals("success")) {
                        Log.e("UPDATE", "onResponse: SUCCESS");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(ChangePwdActivity.this, "修改失败"+t, Toast.LENGTH_SHORT).show();
                Log.e("UPDATE", "onResponse: FAIL");
                t.printStackTrace();
            }
        });

    }

    public void initView(){
        if (firstFragment==null){
            firstFragment = new FirstFragment();
        }
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragments, firstFragment, "FirstFragment");
        transaction.commit();
    }

    public void setupUI(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainActivity.this);  //Main.this是我的activity名
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

//    //接收退出广播
//    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//
//
//        @Override
//        public void onReceive(Context arg0, Intent arg1) {
//        // TODO Auto-generated method stub
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
//    }
//
//    //结束程序
//    public void close() {
//        Intent intent = new Intent();
//        intent.setAction("ExitApp");
//        this.sendBroadcast(intent);
//        super.finish();
//    }
}







