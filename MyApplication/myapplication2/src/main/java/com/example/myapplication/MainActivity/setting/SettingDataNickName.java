package com.example.myapplication.MainActivity.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.Utils.ParentInfo;

/**
 * Created by 小妖王 on 2017/2/23.
 */

public class SettingDataNickName extends Fragment {
    private EditText editNickName;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_data_nickname, container, false);


        editNickName= (EditText) view.findViewById(R.id.editText_nickName);
        editNickName.setText(ParentInfo.nickname);
        return view;
    }

    @Override
    public void onStop() {
        ParentInfo.nickname=editNickName.getText()+"";
        //Log.i(TAG, "onDestroy: good!");
        super.onStop();
    }
}
