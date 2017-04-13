package com.example.myapplication.MainActivity.lookOver;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.Bean.DayStudyInfo;
import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ParentInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.myapplication.MainActivity.lookOver.TimeStudyData.getAllDaysData;
import static com.example.myapplication.MainActivity.lookOver.TimeStudyData.preDaysAnswer;
import static com.example.myapplication.MainActivity.lookOver.TimeStudyData.preDaysCourseLearning;
import static com.example.myapplication.MainActivity.lookOver.TimeStudyData.preDaysIssue;
import static com.example.myapplication.MainActivity.lookOver.TimeStudyData.preDaysMessage;

public class LeftFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ListView timeListView;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private Fragment timeDay;
    private MainActivity mainActivity;
    String[] res = {"今日","2017年2月22日","2017年2月21日","2017年2月20日","2017年2月19日","2017年2月18日","2017年2月17日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日","2017年2月16日"};
    private List<String> dateList=new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.left_fragment, container, false);
        mainActivity=(MainActivity)getActivity();
//        mainActivity.setToolbarTitle("按时间查看");
        dataPrepare(ParentInfo.getLookOverIdNow());//1代表着家长所关注学生的id
        timeListView=(ListView)view.findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_1, dateList);
        timeListView.setAdapter(arrayAdapter);
        timeListView.setOnItemClickListener(this);
        return view;


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(dateList.get(0).equals("网络繁忙或没有学习记录")){
            return;
        }
        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        Fragment leftFrament= mFragmentManager.findFragmentByTag("LeftFragment");
        Bundle bundle = new Bundle();
        bundle.putInt("dateSymbol", position);

        timeDay = new TimeDay();
        timeDay.setArguments(bundle);
        mTransaction.hide(leftFrament);
        mTransaction.add(R.id.first_fragment_content, timeDay,"TimeDay");
        mTransaction.addToBackStack(null);
        mTransaction.commit();

//        String text = timeListView.getItemAtPosition(position)+"";
    }

    public void dataPrepare(int id){
//        Log.e("test", "updateData: enter" );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetStudyInfoServes getStudyInfoServes = retrofit.create(GetStudyInfoServes.class);
        Call<ArrayList<DayStudyInfo>> call = getStudyInfoServes.getStudyInfo(id);
        call.enqueue(new Callback<ArrayList<DayStudyInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<DayStudyInfo>> call, Response<ArrayList<DayStudyInfo>> response) {
//                Toast.makeText(ChangePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                if(response.body()!=null){
//                    if(response.body().equals("success")){
                        Log.e("LeftFragment", "onResponse: SUCCESS");
//                    }
                    String sdate;
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    dateList.clear();
                    getAllDaysData().clear();
                    for(DayStudyInfo dayStudyInfo:response.body()){
                        sdate=sdf.format(dayStudyInfo.getTime());
//                        Log.e("LeftFragment", "date:"+dayStudyInfo.getTime());
//                        Log.e("LeftFragment", "sdate:"+sdate);
                        dateList.add(sdate);
                        getAllDaysData().add(dayStudyInfo);
                        preDaysCourseLearning();
                        preDaysIssue();
                        preDaysAnswer();
                        preDaysMessage();
                    }
                }
                else{
                    dateList.add("网络繁忙或没有学习记录");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DayStudyInfo>> call, Throwable t) {
//                Toast.makeText(ChangePwdActivity.this, "修改失败"+t, Toast.LENGTH_SHORT).show();
                Log.e("LeftFragment", "onResponse: FAIL");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){
            //do nothing
//            onPause();
        }
        if(!hidden){
//            mainActivity.setToolbarTitle("按时间查看");
            ParentInfo.setTitleNow("按时间查看");
        }
    }

}