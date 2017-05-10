package com.example.myapplication.MainActivity.setting;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 小妖王 on 2017/2/20.
 */

public class SettingSecurity extends Fragment implements View.OnClickListener {
    @Bind(R.id.setting_password)
    View password;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private Fragment pwdFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_security, container, false);
        ButterKnife.bind(this,view);
        password.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        switch (v.getId()) {

            //修改密码
            case R.id.setting_password:
                if (pwdFragment == null) {
                    pwdFragment = new SettingSecurityPwd();
                }
                mTransaction.replace(R.id.fourth_fragment_content, pwdFragment);
                mTransaction.addToBackStack(null);
                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                break;

//        //性别
//        case R.id.setting_gender:
//        if (genderFragment==null){
//            genderFragment=new SettingDataGender();
//        }
//        mTransaction.replace(R.id.fourth_fragment_content,genderFragment);
//        mTransaction.addToBackStack(null);
//        mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//        Log.i(TAG, "onClick: clickgE!");
//        break;
//
//        //个性签名
//        case R.id.id_setting_personalfile_signature:
//        if (signaFragment==null){
//            signaFragment=new SettingDataSignature();
//        }
//        mTransaction.replace(R.id.fourth_fragment_content,signaFragment);
//        mTransaction.addToBackStack(null);
//        mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//        break;
//
//        //个人简介
//        case R.id.id_setting_personalfile_introduction:
//        if (introFragment==null){
//            introFragment=new SettingDataIntroduction();
//        }
//        mTransaction.replace(R.id.fourth_fragment_content,introFragment);
//        mTransaction.addToBackStack(null);
//        mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//        break;
//
//        //所在地区
//        case R.id.setting_region:
//        if (regionFragment==null){
//            regionFragment=new SettingDataRegion();
//        }
//        mTransaction.replace(R.id.fourth_fragment_content,regionFragment);
//        mTransaction.addToBackStack(null);
//        mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//        break;
        }
        mTransaction.commit();
    }
}
