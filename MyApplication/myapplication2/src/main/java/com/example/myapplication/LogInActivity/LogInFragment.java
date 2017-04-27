package com.example.myapplication.LogInActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Bean.Parent;
import com.example.myapplication.LinkmanActivity.Info;
import com.example.myapplication.LinkmanActivity.PersonalInfoActivity;
import com.example.myapplication.MainActivity.MainActivity;
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


/**
 * Created by 小妖王 on 2017/3/1.
 */

public class LogInFragment extends Fragment {
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private Fragment signUpFragment;
    private String userName = "";
    private String userpwd = "";
    EditText loginName;
    EditText loginpwd;
    @OnClick(R.id.buttonSignIn)
    void onClick(){
        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        Fragment fragment = mFragmentManager.findFragmentByTag("LogInFragment");
        mTransaction.hide(fragment);
        signUpFragment=new SignUpFragment();
        mTransaction.add(R.id.logIn_content,signUpFragment,"SignUpFragment");
        mTransaction.addToBackStack(null);
        mTransaction.commit();
    }
    @OnClick(R.id.button)
    void onClick1(){
        Log.e(getClass().getSimpleName(), "onClick1: "+getActivity().getApplicationContext().getPackageName() );
        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("rong://"+getActivity().getApplicationContext().getPackageName()+"/conversationlist")));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this,view);
        loginName= (EditText) view.findViewById(R.id.userName);
        loginpwd= (EditText) view.findViewById(R.id.passWord);
        loginName.setText("l5");
        loginpwd.setText("l5");
        Button button=(Button)view.findViewById(R.id.buttonLogIn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userName = loginName.getText().toString();
                userpwd = loginpwd.getText().toString();
                Log.i("front", "onClicklogin:"+userName+userpwd);
                InitLogin(userName,userpwd);
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
            }
        });

        return view;
    }

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
                Toast.makeText(getActivity(), "网络异常，请重试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                Log.e("response", "!");
//                Gson gson = new Gson();
                Parent parent = response.body();
                if(parent ==null){
                    Toast.makeText(getActivity(), "该账户不存在", Toast.LENGTH_SHORT).show();
                }else if(!parent.isPasswordWriteFlag()){
                    Toast.makeText(getActivity(), "密码输入错误", Toast.LENGTH_SHORT).show();
                }else if (parent.isHasAccountFlag()&& parent.isPasswordWriteFlag()) {
                    ParentInfo.id = parent.getId();
                    ParentInfo.nickname = parent.getNickname();
                    ParentInfo.password = parent.getPassword();
                    ParentInfo.region = parent.getRegion();
                    ParentInfo.signature = parent.getSignature();
                    ParentInfo.imageURL = parent.getImageURL();
                    ParentInfo.gender = parent.getGender();
                    ParentInfo.introduction = parent.getIntroduction();
                    ParentInfo.email = parent.getEmail();
                    ParentInfo.number = parent.getNumber();
                    ParentInfo.realname = parent.getRealname();
                    ParentInfo.phone = parent.getPhone();
                    ParentInfo.token = parent.getToken();
                    ParentInfo.username = parent.getUsername();
                    ParentInfo.uid = "a"+parent.getId();
                    Log.e("introduction", ParentInfo.introduction+"");
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
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
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
                    .baseUrl(getResources().getString(R.string.testBaseURL))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final RequestServes requestServes = retrofit.create(RequestServes.class);
            final Call<Info> call = requestServes.getUserInfo(uid, ParentInfo.uid);
            Response<Info> response = call.execute();
            Info info = response.body();
            io.rong.imlib.model.UserInfo userInfo = null;
            Log.i("userinfo", "findUserByUid: " + info);
            if (info != null)
                userInfo = new io.rong.imlib.model.UserInfo(info.getUid(), info.getNickname(), Uri.parse(getResources().getString(R.string.testBaseURL) + info.getImageURL()));
            return userInfo;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    private void storeInfo(String username, String password) {
//        SharedPreferences.Editor info = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();
//        info.putString("username",username);
//        info.putString("password",password);
//        info.putBoolean("needlogin",false);
//        info.commit();
//    }

}
