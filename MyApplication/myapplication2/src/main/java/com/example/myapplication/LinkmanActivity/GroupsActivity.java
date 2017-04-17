package com.example.myapplication.LinkmanActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Utils.ParentInfo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class GroupsActivity extends AppCompatActivity {

    @Bind(R.id.recycler_general)
    RecyclerView mRecyclerGeneral;
    @Bind(R.id.toolbarTitle)
    TextView toolbarTitle;
    private RecyclerGroupsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitle.setText("我的群组");
        setSupportActionBar(toolbar);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerGeneral.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerGroupsAdapter(new ArrayList<ChatGroup>());
        mRecyclerGeneral.setAdapter(mAdapter);
        mAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener<ChatGroup>() {
            @Override
            public void onItemClick(View v, ChatGroup data) {
                Intent intent = new Intent(Intent.ACTION_VIEW, ParentInfo.getGroupChatUri("g"+data.getId(),data.getName()));
                startActivity(intent);
            }
        });
    }
    private void initData() {
//        RetrofitUtils.getService().getGroups(ParentInfo.uid).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ArrayList<ChatGroup>>() {
//                    @Override
//                    public void accept(ArrayList<ChatGroup> groups) throws Exception {
//                        mAdapter.setData(groups);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(GroupsActivity.this, "错误:"+throwable, Toast.LENGTH_SHORT).show();
//                    }
//                });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContantServes contantServes = retrofit.create(ContantServes.class);
        Call<ArrayList<ChatGroup>> call1 = contantServes.getGroups(ParentInfo.uid);
        call1.enqueue(new Callback<ArrayList<ChatGroup>>() {
            @Override
            public void onResponse(Call<ArrayList<ChatGroup>> call, Response<ArrayList<ChatGroup>> response) {
                mAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ChatGroup>> call, Throwable t) {
                //                        Toast.makeText(GroupsActivity.this, "错误:"+throwable, Toast.LENGTH_SHORT).show();
                        Toast.makeText(GroupsActivity.this, "错误:"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
