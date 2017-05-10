package com.example.myapplication.MainActivity.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ParentInfo;

/**
 * Created by 小妖王 on 2017/2/20.
 */

public class SettingDataIntroduction extends Fragment {
    private EditText editIntroduction;
    private MainActivity mainActivity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_data_introduction, container, false);
        
        mainActivity=(MainActivity)getActivity();

        editIntroduction= (EditText) view.findViewById(R.id.editText_introduction);
        editIntroduction.setText(ParentInfo.introduction);
        return view;
    }

    @Override
    public void onStop() {
        ParentInfo.introduction=editIntroduction.getText()+"";
        //Log.i(TAG, "onDestroy: good!");
        super.onStop();
    }
}
