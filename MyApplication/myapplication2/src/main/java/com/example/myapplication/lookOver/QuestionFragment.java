package com.example.myapplication.lookOver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Bean.QuestionInfo;
import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Constant;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2016/11/20.
 */

public class QuestionFragment extends Fragment implements MyItemClickListener {
    private QuesListAdapter quesListAdapter;
    private final String imageUrl= Constant.imagesUrl;

    @Bind(R.id.id_RecyclerView)
    RecyclerView recyclerView;

    private ArrayList<QuestionInfo> questions;
    private final String TAG_1 = "OnClick";
    private TwinklingRefreshLayout refreshLayout;
    private int start=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab01,container,false);
        refreshLayout=(TwinklingRefreshLayout) v.findViewById(R.id.refreshLayout);
        refreshLayout.setHeaderView(new SinaRefreshView(getContext()));
        ButterKnife.bind(this,v);
        initRecyclerView(v);

        return v;
    }

    private void initRecyclerView(View v){
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {

            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                initData(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                },2000);            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                initData(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                },2000);            }
        });


        if(recyclerView==null){
            Log.i(TAG_1,"bad mess");
            return;
        }
        questions=new ArrayList<>();
        initData(true);
        if(questions!=null){
            Log.i("question number",questions.toString());
        }else {
            Log.i("question number","== zero");
        }
    }
    private void initData(final boolean isLoadMore){

//        initData("大学生有怎么样的生活啊","10月10日","1111人",imageUrl,"周铠");
//        initData("有父母陪是怎么样的","11月13日","643人",imageUrl,"宋惠");
//        initData("大哥哥，这道题该怎么做？","12月01日","24人",imageUrl,"王光曜");
//        initData("有时候很想在外地的父母，大姐姐可以告诉我，应该怎么做吗？","12月09日","63人",imageUrl,"谢柱坤");
//        initData("这些题目好难啊，都不想做","11:02","382人",imageUrl,"江茹");
//        initData("大哥哥，大学生活美好吗？","11:06","39人",imageUrl,"王学舟");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QuestionRequestServes questionRequestServes = retrofit.create(QuestionRequestServes.class);
        Call<ArrayList<QuestionInfo>> call = questionRequestServes.loadDiscover(start);
        call.enqueue(new Callback<ArrayList<QuestionInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<QuestionInfo>> call, Response<ArrayList<QuestionInfo>> response) {
                ArrayList<QuestionInfo> questionsSet=response.body();
                if(questionsSet!=null){
                    Log.i("get question","succeed");
                    for(QuestionInfo questionInfo:questionsSet){
                        Log.i("get question",questionInfo.toString());
                        questions.add(questionInfo);
                    }
                    if(isLoadMore==true)
                        start+=questionsSet.size();
                    quesListAdapter = new QuesListAdapter(questions,recyclerView.getContext());
                    quesListAdapter.getActivity(getActivity());
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                    quesListAdapter.setOnItemClickListener(QuestionFragment.this);
                    recyclerView.setAdapter(quesListAdapter);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<QuestionInfo>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    @Override
    public void onItemClick(View view, int postion) {
        Log.i(TAG_1," succeed "+postion);

        if(this.getActivity() instanceof MainActivity){

            Intent intent = new Intent(getActivity(), QuestionContentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Question", questions.get(postion));
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

}
