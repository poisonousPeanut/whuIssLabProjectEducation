package com.example.myapplication.LogInActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Bean.Parent;
import com.example.myapplication.LinkmanActivity.Info;
import com.example.myapplication.LinkmanActivity.PersonalInfoActivity;
import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ParentInfo;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.myapplication.Utils.MyUtils.hideSoftKeyboard;

/**
 * Created by 小妖王 on 2017/3/1.
 */

public class LogInActivity extends Activity {
//    private FragmentManager mFragmentManager;
//    private FragmentTransaction mTransaction;
//    private Fragment logInFragment;
private String userName = "";
    private String userpwd = "";
    EditText loginName;
    EditText loginpwd;
    @OnClick(R.id.buttonSignIn)
    void onClick(){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //initView();
        loginName= (EditText)findViewById(R.id.userName);
        loginpwd= (EditText)findViewById(R.id.passWord);
        loginName.setText("l5");
        loginpwd.setText("l5");
        Button button=(Button)findViewById(R.id.buttonLogIn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userName = loginName.getText().toString();
                userpwd = loginpwd.getText().toString();
                Log.i("front", "onClicklogin:"+userName+userpwd);
                InitLogin("l5", "l5");
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
            }
        });
        setupUI(findViewById(R.id.activity_login));
    }

//    private void initView() {
//        mFragmentManager = getFragmentManager();
//        mTransaction = mFragmentManager.beginTransaction();
//        logInFragment = new LogInFragment();
//        mTransaction.add(R.id.logIn_content, logInFragment,"LogInFragment");
//        mTransaction.commit();
//    }
private void InitLogin(final String username, final String userpwd) {
    Retrofit retorfit = new Retrofit.Builder()
            .baseUrl(getResources().getString(R.string.testBaseURL))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RequestServes requestServes = retorfit.create(RequestServes.class);
    Call<Parent> call = requestServes.getString(username, userpwd);
    call.enqueue(new Callback<Parent>() {
        @Override
        public void onFailure(Call<Parent> call, Throwable throwable) {
            Log.e("get data", "fail");
            throwable.printStackTrace();
            Toast.makeText(LogInActivity.this, "网络异常，请重试", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(Call<Parent> call, Response<Parent> response) {
            Log.e("response", "!");
//                Gson gson = new Gson();
            Parent parent = response.body();
            if(parent ==null){
                Toast.makeText(LogInActivity.this, "该账户不存在", Toast.LENGTH_SHORT).show();
            }else if(!parent.isPasswordWriteFlag()){
                Toast.makeText(LogInActivity.this, "密码输入错误", Toast.LENGTH_SHORT).show();
            }else if (parent.isHasAccountFlag()&& parent.isPasswordWriteFlag()) {
                ParentInfo.id = parent.getId();
                ParentInfo.nickname = parent.getNickname();
                ParentInfo.password = parent.getPassword();
                ParentInfo.region = parent.getRegion();
                ParentInfo.signature = parent.getSignature();
                ParentInfo.imageURL = parent.getImageUrl();
                ParentInfo.gender = parent.getGender();
                ParentInfo.introduction = parent.getIntroduction();
                ParentInfo.email = parent.getEmail();
                ParentInfo.number = parent.getNumber();
                ParentInfo.realname = parent.getRealname();
                ParentInfo.phone = parent.getPhone();
                ParentInfo.token = parent.getToken();
                ParentInfo.username = parent.getUsername();
                ParentInfo.uid = "a"+parent.getId();
                Log.e("imageurl", ParentInfo.imageURL + "");
                Log.e("username", ParentInfo.username+"");
                Log.e("success", "onResponse:1 ");
//                    storeInfo(parent.getUsername(),parent.getPassword());
//                    UserUtils.setParam(getApplicationContext(),parent);
                connect(parent.getToken());
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    startActivity(intent);
            }
        }
    });
}

    private void connect(String token) {
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public io.rong.imlib.model.UserInfo getUserInfo(String uid) {
                return findUserByUid(uid);
            }
        }, true);
        RongIM.setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType type, io.rong.imlib.model.UserInfo info) {
                Intent intent = new Intent(context, PersonalInfoActivity.class);
                intent.putExtra("uid", info.getUserId());
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType type, io.rong.imlib.model.UserInfo info) {
                return false;
            }

            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLinkClick(Context context, String s) {
                return false;
            }

            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                return false;
            }
        });

        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {

            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                Log.d("LoginActivity", "--onSuccess" + userid);
                startActivity(new Intent(LogInActivity.this, MainActivity.class));
                finish();
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });

    }

    private io.rong.imlib.model.UserInfo findUserByUid(String uid) {
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://60.205.190.45:8080/education/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final RequestServes requestServes = retrofit.create(RequestServes.class);
            final Call<Info> call = requestServes.getUserInfo(uid, ParentInfo.uid);
            Response<Info> response = call.execute();
            Info info = response.body();
            io.rong.imlib.model.UserInfo userInfo = null;
            Log.i("userinfo", "findUserByUid: " + info);
            if (info != null)
                userInfo = new io.rong.imlib.model.UserInfo(info.getUid(), info.getNickname(), Uri.parse("http://60.205.190.45:8080/education/" + info.getImageURL()));
            return userInfo;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
