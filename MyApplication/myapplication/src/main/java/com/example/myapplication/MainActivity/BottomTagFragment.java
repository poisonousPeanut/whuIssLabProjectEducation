package com.example.myapplication.MainActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.LinkmanActivity.LinkmanFragment;
import com.example.myapplication.MainActivity.setting.FourthFragment;
import com.example.myapplication.MessageActivity.MessageFragment;
import com.example.myapplication.R;
import com.example.myapplication.lookOver.FirstFragment;

/**
 * Created by 小妖王 on 2017/2/21.
 */

public class BottomTagFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;

    public RadioButton getFirst_rad() {
        return first_rad;
    }

    private RadioButton first_rad;//底部标签第一个按钮

    public RadioButton getFourth_rad() {
        return fourth_rad;
    }

    private RadioButton fourth_rad;//底部标签第的第四个按钮
    private String[] mainFragments = {"FirstFragment", "SecondFragment", "ThirdFragment", "FourthFragment"};
    //private String[] mainFragments = {"FirstFragment","FourthFragment"};
    private MainActivity mainActivity;
    private int pageNow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomtag_fragment, container, false);
        mainActivity = (MainActivity) getActivity();
//        pageNow=mainActivity.getPageNow();
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(this);
//
        first_rad = (RadioButton) view.findViewById(R.id.first);
//        fourth_rad = (RadioButton)view.findViewById(R.id.fourth);
//        if(pageNow==1){
//            first_rad.setChecked(true);
//            //changeFragment("FirstFragment");
//        }
//        if (pageNow==4){
//            fourth_rad.setChecked(true);
//        }
        first_rad.setChecked(true);
        return view;
    }

    public void changeFragment(String tag) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                //fragment = (Fragment) Class.forName(tag).newInstance();
                switch (tag) {
                    case "FirstFragment":
                        fragment = new FirstFragment();
                        break;
                    case "SecondFragment":
                        fragment = new MessageFragment();
                        break;
                    case "ThirdFragment":
                        fragment = new LinkmanFragment();
                        break;
                    case "FourthFragment":
                        fragment = new FourthFragment();
                        break;
                }
            } catch (Exception e) {
            }
            transaction.add(R.id.fragments, fragment, tag);
        } else {
            transaction.show(fragment);
        }
        for (int i = 0; i < 4; i++) {
            Fragment tempF = manager.findFragmentByTag(mainFragments[i]);
            if (tempF != null && !tempF.getTag().equals(tag)) {
                transaction.hide(tempF);
//                Log.e("'''''", "changeFragment:aafsadfa ");
            }
        }
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub

        switch (checkedId) {
            case R.id.first:
                changeFragment(mainFragments[0]);
                break;

            case R.id.second:
                //这里开启消息界面
                changeFragment(mainFragments[1]);
                break;

            case R.id.third:
                //这里开启联系人界面
                changeFragment(mainFragments[2]);
                break;

            case R.id.fourth:
                changeFragment(mainFragments[3]);
                break;

        }
    }

}
