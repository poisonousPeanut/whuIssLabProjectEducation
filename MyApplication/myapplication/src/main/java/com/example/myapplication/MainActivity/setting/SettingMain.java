package com.example.myapplication.MainActivity.setting;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.MyApplication;

/**
 * Created by 小妖王 on 2017/2/20.
 */

public class SettingMain extends Fragment implements OnClickListener {
    private View security;
    private View personalData;
    //private View inform;
    //private View auFunc;
    private View logOut;

    private Fragment fragmentS;//personal accounct and security
    private Fragment fragmentD;//personal data
    //private Fragment fragmentI;
    private Fragment fragmentA;//待选辅助功能（或直接写到设置主界面）： 切换账号 清除缓存
    //private Fragment fragmentL;


    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private MainActivity mainActivity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_main, container, false);


        mainActivity = (MainActivity) getActivity();

        security = view.findViewById(R.id.id_setting_accountSecurity);
        personalData = view.findViewById(R.id.id_setting_persondata);
        logOut = view.findViewById(R.id.id_setting_log_out);
        //inform = view.findViewById(R.id.id_setting_messageInform);
        //auFunc = view.findViewById(R.id.id_setting_auxiliaryFunc);

        security.setOnClickListener(this);
        personalData.setOnClickListener(this);
        logOut.setOnClickListener(this);
//        inform.setOnClickListener(this);
//        auFunc.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        switch (v.getId()) {
            //账户与安全逻辑
            case R.id.id_setting_accountSecurity:
                if (fragmentS == null) {
                    fragmentS = new SettingSecurity();
                }
                mTransaction.hide(mainActivity.getBottomTag());
                mTransaction.replace(R.id.fourth_fragment_content, fragmentS);
                mTransaction.addToBackStack(null);
                // mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                mTransaction.commit();
                break;

            //个人资料按钮逻辑
            case R.id.id_setting_persondata:
                if (fragmentD == null) {
                    fragmentD = new SettingData();
                }
                mTransaction.hide(mainActivity.getBottomTag());
                mTransaction.replace(R.id.fourth_fragment_content, fragmentD);
                mTransaction.addToBackStack(null);
//                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                mTransaction.commit();
                break;

//            case R.id.id_setting_auxiliaryFunc:
//                mFragmentManager = getFragmentManager();
//                mTransaction = mFragmentManager.beginTransaction();
//                if (fragmentS==null){
//                    fragmentS=new Setting???();
//                }
//                mTransaction.replace(R.id.fourth_fragment_content,fragmentA);
//                Log.i(TAG, "onClick:a ");
//                break;

            //退出登录逻辑
            case R.id.id_setting_log_out:
                //退出登录
                MyApplication.getInstance().exit();
                break;

            //            case R.id.id_setting_messageInform:
//                mFragmentManager = getFragmentManager();
//                mTransaction = mFragmentManager.beginTransaction();
//                if (fragmentI==null){
//                    fragmentI=new ;
//                }
//                mTransaction.replace(R.id.fourth_fragment_content,fragmentI);
//                mTransaction.hide(mainActivity.getBottomTag());
//                mTransaction.addToBackStack(null);
//                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                mTransaction.commit();
//                Log.i(TAG, "onClick:i ");
//                break;
        }
    }

}

