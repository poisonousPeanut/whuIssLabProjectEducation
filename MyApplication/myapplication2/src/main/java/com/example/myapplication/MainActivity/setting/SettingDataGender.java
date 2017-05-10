package com.example.myapplication.MainActivity.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ParentInfo;

/**
 * Created by 小妖王 on 2017/2/20.
 */

public class SettingDataGender extends Fragment implements View.OnClickListener{
    private View checkMale;//勾选男
    private View checkFemale;//勾选女
    private View checkedMale;//已勾选男
    private View checkedFemale;//已勾选女
    //private FragmentManager mFragmentManager;
//    private Parent parent;

    //private TextView personalDataGender;//个人信息界面的性别
//    public TextView getpersonlDataGender(){
//        SettingData=(com.example.myapplication.SettingData)mFragmentManager.findFragmentByTag("persondata");
//        personalDataGender=SettingData.getPersonalGender();
//        return personalDataGender;
//    }

    private MainActivity mainActivity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_data_gender, container, false);
        mainActivity=(MainActivity)getActivity();
//        parent = mainActivity.getAParent();
//        mFragmentManager = getFragmentManager();
        checkMale=view.findViewById(R.id.setting_gender_male);
        checkFemale=view.findViewById(R.id.id_setting_gender_female);
        checkedMale=view.findViewById(R.id.id_setting_gender_check_male);
        checkedFemale=view.findViewById(R.id.id_setting_gender_check_female);

        //personalDataGender=getpersonlDataGender();

        checkMale.setOnClickListener(this);
        checkFemale.setOnClickListener(this);

        initView();

        return view;
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        personDataGender=(TextView)mainActivity.findViewById(R.id.id_setting_textview_gender);
//        initView();
//
//    }

    private void initView() {
        //视数据库内数据而定
        if (ParentInfo.gender == "男") {
            checkedFemale.setVisibility(View.INVISIBLE);
            checkedMale.setVisibility(View.VISIBLE);
        }
        if (ParentInfo.gender == "女") {
            checkedFemale.setVisibility(View.VISIBLE);
            checkedMale.setVisibility(View.INVISIBLE);
        }
        if(ParentInfo.gender==null){
            checkedFemale.setVisibility(View.INVISIBLE);
            checkedMale.setVisibility(View.INVISIBLE);
        }
        //personalDataGender.setText("男");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_gender_male:
                checkedFemale.setVisibility(View.INVISIBLE);
                checkedMale.setVisibility(View.VISIBLE);
                ParentInfo.gender="男";
                //personalDataGender.setText("男");
                break;
            case R.id.id_setting_gender_female:
                checkedMale.setVisibility(View.INVISIBLE);
                checkedFemale.setVisibility(View.VISIBLE);
                ParentInfo.gender="女";
                //personalDataGender.setText("女");
                break;
        }
    }
}
