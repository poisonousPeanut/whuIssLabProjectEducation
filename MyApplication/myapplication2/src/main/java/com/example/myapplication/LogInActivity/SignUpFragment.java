package com.example.myapplication.LogInActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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

/**
 * Created by 小妖王 on 2017/3/16.
 */

public class SignUpFragment extends Fragment {
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
//        if(nameLength<7){
//            Toast.makeText(getActivity(), "用户名过短，应有8-11位用户名", Toast.LENGTH_SHORT).show();
//            return;
//        }else if(nameLength>12){
//            Toast.makeText(getActivity(), "用户名过长，应有8-11位用户名", Toast.LENGTH_SHORT).show();
//            return;
//        }else if(pwdLength<5||pwdLength>19){
//            Toast.makeText(getActivity(), "密码格式不正确，应为6-18位字母或数字", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if(pwd.equals(confirm)){
            InitSignup(registerName.getText().toString(),registerpwd.getText().toString());
            Log.i("signup == ",registerName.getText().toString()+" "+registerpwd.getText().toString());

        }else {
            Toast.makeText(getActivity(), "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            registerpwd.setText("");
            registerCheckpwd.setText("");
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_fragment, container, false);
        ButterKnife.bind(this,view);
        return view;
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
//                    Parent user = new Parent();
//                    user.setUsername(username);
//                    user.setPassword(userpwd);
                        Toast.makeText(getActivity(), "创建成功", Toast.LENGTH_SHORT).show();
//                        ParentInfo.username=username;
//                        ParentInfo.password=userpwd;
//                        Intent intent = new Intent(getActivity(), MainActivity.class);
//                        startActivity(intent);
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction  transaction = manager.beginTransaction();
                        Fragment fragment = manager.findFragmentByTag("LogInFragment");
                        Fragment fragment1= manager.findFragmentByTag("SignUpFragment");
                        if(fragment==null){
                            fragment = new LogInFragment();
                            transaction.add(R.id.logIn_content,fragment,"LogInFragment");
                        }else{
                            transaction.show(fragment);
                        }
                        transaction.remove(fragment1);
                        transaction.commit();
                    }
                }

            }
        });
    }
}
