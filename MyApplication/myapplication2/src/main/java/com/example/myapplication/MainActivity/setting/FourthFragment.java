package com.example.myapplication.MainActivity.setting;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;

/**
 * Created by 小妖王 on 2017/2/13.
 */

public class FourthFragment extends Fragment{
//    private View signature;
//    private View file;
//    private View inform;
//    private View auFunc;
//    private View logOut;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private Fragment fragmentM;
    private MainActivity mainActivity;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fourth_fragment, container, false);
        mainActivity=(MainActivity)getActivity();

        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();

        toolbar = (Toolbar)view.findViewById(R.id.fourth_toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbarTitle = (TextView) view.findViewById(R.id.toolbarTitle4);
        toolbarTitle.setText("设置");
        fragmentM=new SettingMain();
        mTransaction.replace(R.id.fourth_fragment_content, fragmentM);
        mTransaction.commit();

//        signature=view.findViewById(R.id.id_setting_personalizesignature);
//        file=view.findViewById(R.id.id_setting_personfile);
//        inform=view.findViewById(R.id.id_setting_messageInform);
//        auFunc=view.findViewById(R.id.id_setting_auxiliaryFunc);
//        logOut=view.findViewById(R.id.id_setting_log_out);
//
//        signature.setOnClickListener(this);
//        file.setOnClickListener(this);
//        inform.setOnClickListener(this);
//        auFunc.setOnClickListener(this);
//        logOut.setOnClickListener(this);

        //getActivity().setTitle("555");

//        mainActivity.setToolbarTitle("设置");
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){
            //do nothing
        }
        if(!hidden){
//            mainActivity.setToolbarTitle("设置");
        }
    }
//    @Override
//    public void onResume() {
//        mainActivity.setToolbarTitle("设置");
//        super.onResume();
//    }

    //    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.id_setting_personalizesignature:
//                Log.i("t", "onClick:s ");
//                break;
//            case R.id.id_setting_personfile:
//                Log.i(TAG, "onClick:f ");
//                break;
//            case R.id.id_setting_messageInform:
//                Log.i(TAG, "onClick:i ");
//                break;
//            case R.id.id_setting_auxiliaryFunc:
//                Log.i(TAG, "onClick:a ");
//                break;
//            case R.id.id_setting_log_out:
//                Log.i(TAG, "onClick:l ");
//                break;
////            case id_setting_personalizesignature:
////                break;
//        }
//    }
}
