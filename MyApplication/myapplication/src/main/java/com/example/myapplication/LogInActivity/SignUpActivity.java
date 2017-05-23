package com.example.myapplication.LogInActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MyApplication;
import com.example.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.myapplication.Utils.MyUtils.hideSoftKeyboard;

/**
 * Created by robot3 on 2017/5/9.
 */

public class SignUpActivity extends Activity {
    @Bind(R.id.editText3)
    EditText registerName;
    @Bind(R.id.editText5)
    EditText registerpwd;
    @Bind(R.id.editText4)
    EditText registerCheckpwd;
    @OnClick(R.id.button5)
    void registerResetOnClick(){
        registerName.setText("");
        registerpwd.setText("");
        registerCheckpwd.setText("");
        registerName.requestFocus();
    }
    @OnClick(R.id.button4)
    void registerSubmitOnclick(){
        String pwd = registerpwd.getText().toString();
        String confirm = registerCheckpwd.getText().toString();
        int nameLength = registerName.getText().toString().length();
        int pwdLength = pwd.length();
        //为测试方便暂时注释的代码
        if(nameLength<7){
            Toast.makeText(this, "用户名过短，应有8-11位用户名", Toast.LENGTH_SHORT).show();
            return;
        }else if(nameLength>12){
            Toast.makeText(this, "用户名过长，应有8-11位用户名", Toast.LENGTH_SHORT).show();
            return;
        }else if(pwdLength<5||pwdLength>19){
            Toast.makeText(this, "密码格式不正确，应为6-18位字母或数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pwd.equals(confirm)){
            InitSignup(registerName.getText().toString(),registerpwd.getText().toString());
            Log.i("signup == ",registerName.getText().toString()+" "+registerpwd.getText().toString());

        }else {
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            registerpwd.setText("");
            registerCheckpwd.setText("");
        }

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        setupUI(findViewById(R.id.activity_signup));
    }

    private void InitSignup(final String username, final String userpwd) {
        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterServes requestServes = retorfit.create(RegisterServes.class);
        Call<String> call = requestServes.getString(username,userpwd);
        call.enqueue(new Callback<String>(){
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e("===","fail");
                throwable.printStackTrace();
            }

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("==","return:"+response.body());
                if(response.body()!=null){
                    if(response.body().equals("true")){
                        Toast.makeText(SignUpActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else if(response.body().equals("account already exits")){
                        Toast.makeText(SignUpActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                        registerName.setText("");
                    }
                }

            }
        });
    }

    public void setupUI(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(SignUpActivity.this);  //Main.this是我的activity名
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
}
