package com.example.myapplication.MainActivity.lookOver;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 小妖王 on 2017/2/13.
 */

public class FirstFragment extends Fragment implements View.OnClickListener {
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private Button title_left_btn , title_right_btn;
    private Toolbar toolbar;


    /**
     * Fragment管理器
     */
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    /**
     * 两个Fragment
     */
    private Fragment mLFragment ;
    private Fragment mRFragment;

    private MainActivity mainActivity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        //spinner = (Spinner)getActivity().findViewById(R.id.spinner);
        View view=inflater.inflate(R.layout.first_fragment, container, false);

//        spinner = (Spinner)view.findViewById(R.id.spinner);
//        //数据
//        data_list = new ArrayList<String>();
//        data_list.add("学生1");
//        data_list.add("学生2");
//        data_list.add("学生3");
//        data_list.add("学生4");
//
//        //适配器
//        arr_adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,data_list);
//        //设置样式
//        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //加载适配器
//        spinner.setAdapter(arr_adapter);
////        spinner.setOnItemClickListener(this);


        toolbar = (Toolbar)view.findViewById(R.id.first_toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

//        toolbar.setLogo(R.mipmap.ic_launcher);

//        SpinnerAdapter spinnerAdapter = new SimpleAdapter(getActivity(),
//                                                            getData(),
//                                                            R.layout.spinner_item,new String[]{"pic","text"},
//                                                            new int[] {R.id.imageView2,R.id.textView5});
//
//
//
//        Spinner navigationSpinner = new Spinner(toolbar.getContext());//(((AppCompatActivity) getActivity()).getSupportActionBar().getThemedContext());
//
//        navigationSpinner.setAdapter(spinnerAdapter);
//
//        toolbar.addView(navigationSpinner, 0);
//
//        navigationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                Toast.makeText(getActivity(),
//
//                        "you selected: " + position,
//
//                        Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//
//        });
        mainActivity=(MainActivity)getActivity();

        initView(view);
//        mainActivity.getToolbar().setTitle("查看学生学习情况");
//        mainActivity.setToolbarTitle("按时间查看");
        return view;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.checkAsTime:
                if(title_left_btn.isEnabled()){
                    title_left_btn.setEnabled(false);
                    title_right_btn.setEnabled(true);
                    title_right_btn.getBackground().setAlpha(100);
                    title_left_btn.getBackground().setAlpha(100);
                }
                mFragmentManager = getFragmentManager();
                mTransaction = mFragmentManager.beginTransaction();
                mLFragment = mFragmentManager.findFragmentByTag("LeftFragment");
                Fragment TimeDay = mFragmentManager.findFragmentByTag("TimeDay");
                if(TimeDay!=null){
                    mTransaction.show(TimeDay);
                }
                if(mLFragment == null){
                    mLFragment = new LeftFragment();
                    mTransaction.add(R.id.first_fragment_content, mLFragment, "LeftFragment");
                }else{
                    mTransaction.show(mLFragment);
                }
                Fragment tempF = mFragmentManager.findFragmentByTag("RightFragment");
                if (tempF != null) {
                    mTransaction.hide(tempF);
                }

                mTransaction.commit();
                break;

            case R.id.checkAsCourse:
                if(title_right_btn.isEnabled()){
                    title_left_btn.setEnabled(true);
                    title_right_btn.setEnabled(false);
                }
                mFragmentManager = getFragmentManager();
                mTransaction = mFragmentManager.beginTransaction();
                mRFragment = mFragmentManager.findFragmentByTag("RightFragment");
                Fragment TimeDay1 = mFragmentManager.findFragmentByTag("TimeDay");
                if(TimeDay1!=null){
                    mTransaction.hide(TimeDay1);
                }
                if(mRFragment == null){
                    mRFragment = new RightFragment();
                    mTransaction.add(R.id.first_fragment_content, mRFragment, "RightFragment");
                }else{
                    mTransaction.show(mRFragment);
                }

                Fragment tempF1 = mFragmentManager.findFragmentByTag("LeftFragment");
                if (tempF1 != null) {
                    mTransaction.hide(tempF1);
                }

                mTransaction.commit();
                break;
        }
    }
    private void initView(View view) {
        // TODO Auto-generated method stub
        title_left_btn = (Button)view.findViewById(R.id.checkAsTime);
        title_right_btn = (Button)view.findViewById(R.id.checkAsCourse);

        title_left_btn.setOnClickListener(this);
        title_left_btn.performClick();//模拟点击事件，使左边按钮被点击

        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();

        mLFragment = mFragmentManager.findFragmentByTag("LeftFragment");
        if (mLFragment == null) {
            try {
                mLFragment = new LeftFragment();
            } catch (Exception e) {
            }
            mTransaction.add(R.id.first_fragment_content, mLFragment, "LeftFragment");
        } else {
            mTransaction.show(mLFragment);
        }
        mTransaction.commit();

        title_right_btn.setOnClickListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){
            //do nothing
        }
        if(!hidden){
//            mainActivity.setToolbarTitle(ParentInfo.getTitleNow());
        }
    }

    protected List<Map<String,Object>> getData(){
        List<Map<String,Object>> mapList= new ArrayList<>();
        for (int i=0;i<3;i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("pic",R.mipmap.ic_launcher);
            map.put("text","oh,shit"+i);
            mapList.add(map);
        }
        return mapList;
    }
//    @Override
//    public void onResume() {
//        mainActivity.setToolbarTitle("查看");
//        super.onResume();
//    }
}
