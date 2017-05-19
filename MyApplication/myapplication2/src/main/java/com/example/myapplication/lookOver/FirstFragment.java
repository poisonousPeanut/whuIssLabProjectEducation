package com.example.myapplication.lookOver;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by 小妖王 on 2017/2/13.
 */

public class FirstFragment extends Fragment implements View.OnClickListener {
    private Spinner changeStudent;
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
        View view=inflater.inflate(R.layout.first_fragment, container, false);
        changeStudent = (Spinner) view.findViewById(R.id.changeStudent);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.piconly,
                new String[]{"pic", "text"}, new int[]{R.id.imageview, R.id.textview});
        simpleAdapter.setDropDownViewResource(R.layout.pic_text);
        changeStudent.setAdapter(simpleAdapter);
        toolbar = (Toolbar)view.findViewById(R.id.first_toolbar);
        initView(view);
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
                    mRFragment = new QuestionFragment();
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

//        mFragmentManager = getFragmentManager();
//        mTransaction = mFragmentManager.beginTransaction();
//
//        mLFragment = mFragmentManager.findFragmentByTag("LeftFragment");
//        if (mLFragment == null) {
//            try {
//                mLFragment = new LeftFragment();
//            } catch (Exception e) {
//            }
//            mTransaction.add(R.id.first_fragment_content, mLFragment, "LeftFragment");
//        } else {
//            mTransaction.show(mLFragment);
//        }
//        mTransaction.commit();

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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pic", R.drawable.ic_face_red_400_24dp);
        map.put("text", "sde");
        mapList.add(map);
        return mapList;
    }

//    private void prepareData() {
//        user = UserUtils.getParam(getApplicationContext());
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(getResources().getString(R.string.baseURL))
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ContantServes contantServes = retrofit.create(ContantServes.class);
//        Call<ArrayList<Student>> call = contantServes.getStudentInfo(UserInfo.id+"");
//        call.enqueue(new Callback<ArrayList<Student>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
//                ArrayList<Student> unpairStudents = response.body();
//                if(unpairStudents!=null){
//                    students=unpairStudents;
//                    StudentInfoAdapter studentAdapter = new StudentInfoAdapter(students,recyclerView.getContext());
//                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//                    studentAdapter.setOnItemClickListener(PairStudentActivity.this);
//                    recyclerView.setAdapter(studentAdapter);
//                }else {
//                    Toast.makeText(PairStudentActivity.this, "学生获取失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
//                Toast.makeText(PairStudentActivity.this, "网络异常，请重试"+t, Toast.LENGTH_SHORT).show();
//                Log.i("unpair student",t.toString());
//            }
//        });
//
//
//        //// TODO: 2017/2/21
//
//    }
}
