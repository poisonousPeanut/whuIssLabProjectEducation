package com.example.myapplication.MainActivity.setting;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.Utils.ParentInfo;

/**
 * Created by 小妖王 on 2017/2/23.
 */

public class SettingDataSignature extends Fragment {
    private EditText editSignature;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_data_signature, container, false);
        editSignature= (EditText) view.findViewById(R.id.editText_signature);
        editSignature.setText(ParentInfo.signature);
        return view;
    }

    @Override
    public void onStop() {
        ParentInfo.signature=editSignature.getText()+"";
        //Log.i(TAG, "onDestroy: good!");
        super.onStop();
    }
}
