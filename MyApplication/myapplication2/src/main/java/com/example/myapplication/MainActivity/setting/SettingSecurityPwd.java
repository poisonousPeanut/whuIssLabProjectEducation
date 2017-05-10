package com.example.myapplication.MainActivity.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Utils.ParentInfo;
import com.example.myapplication.Utils.UpdateInfoServes;

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

public class SettingSecurityPwd extends Fragment {
    @Bind(R.id.et_old_password)
    EditText oldPassword;
    @Bind(R.id.et_new_password)
    EditText newPassword;

    @OnClick(R.id.bt_confirm_password)
    void confirm() {
        oldpwd = oldPassword.getText().toString();
        newpwd = newPassword.getText().toString();

        if (newpwd.length() < 5 || newpwd.length() > 19) {
            Toast.makeText(getActivity(), "密码格式不正确，应为6-18位字母或数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if (newpwd.equals(oldpwd)) {
            Toast.makeText(getActivity(), "新密码不能与原密码相同", Toast.LENGTH_SHORT).show();
            return;
        } else if (oldpwd.equals(ParentInfo.password)) {
            ParentInfo.password=newpwd;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.testBaseURL))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UpdateInfoServes update = retrofit.create(UpdateInfoServes.class);
            Call<String> call = update.getString(
                    ParentInfo.id,
                    ParentInfo.nickname,
                    newpwd,
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
                    Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getActivity(), "修改失败" + t, Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });


        } else {
            Toast.makeText(getActivity(), "原密码输入错误", Toast.LENGTH_SHORT).show();
        }
    }

    private String oldpwd;
    private String newpwd;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_security_password, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        oldPassword.setText("");
        newPassword.setText("");
        super.onResume();
    }
}
