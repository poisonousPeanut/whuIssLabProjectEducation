package com.example.myapplication.MainActivity.setting;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ParentInfo;

/**
 * Created by 小妖王 on 2017/2/23.
 */

public class SettingDataRegion extends Fragment {
    private EditText editRegion;
    private MainActivity mainActivity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_data_region, container, false);

        mainActivity=(MainActivity)getActivity();

        editRegion= (EditText) view.findViewById(R.id.editText_region);
        editRegion.setText(ParentInfo.region);
        return view;
    }

    @Override
    public void onStop() {
        ParentInfo.region=editRegion.getText()+"";
        //Log.i(TAG, "onDestroy: good!");
        super.onStop();
    }
}
