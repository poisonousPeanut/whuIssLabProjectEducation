package com.example.myapplication.LinkmanActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.myapplication.LogInActivity.LogInActivity;
import com.example.myapplication.Utils.MyApplication;
import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.myapplication.Utils.MyUtils.hideSoftKeyboard;

public class LinkmanActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    public Toolbar getToolbar() {
        return toolbar;
    }

    private Toolbar toolbar;

    public void setToolbarTitle(String titleContent) {
        this.toolbarTitle.setText(titleContent);
    }

    private TextView toolbarTitle;
    private BottomTagFragment3 bottomTag;//主页面底部标签栏

    public Fragment getBottomTag() {
        return bottomTag;
    }
    @Bind(R.id.aelv_contact)
    AnimatedExpandableListView aelvContact;
    @Bind(R.id.search_view)
    MaterialSearchView searchView;
    @Bind(R.id.tv_number)
    TextView tvNumber;
    private ExpandableContactAdapter mAdapter;
    private Gson gson = new Gson();
    private String cachePath;
    private File cacheFile;
    private ArrayList<RosterGroup> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkman);
        ButterKnife.bind(this);
        MyApplication.getInstance().addActivity(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitle=(TextView)findViewById(R.id.toolbarTitle);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbarTitle.setText("联系人");
        setSupportActionBar(toolbar);

        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        bottomTag = new BottomTagFragment3();
        mTransaction.replace(R.id.bottomTag3, bottomTag);
        mTransaction.commit();
        initView();
        loadData();
        setupUI(findViewById(R.id.activity_link));
    }

    @Override
    protected void onResume() {
        bottomTag.getThird_rad().setChecked(true);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.REQURST_SEARCH && resultCode == RESULT_OK) {
            loadDataFromServer();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.rl_friend_request, R.id.rl_my_group})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_friend_request:
                Intent intent = new Intent(this, FriendRequestActivity.class);
                startActivityForResult(intent, Constant.REQURST_SEARCH);
                break;
            case R.id.rl_my_group:
                Intent groupIntent = new Intent(this, GroupsActivity.class);
                startActivity(groupIntent);
                break;
        }
    }

    private void loadDataFromServer() {
//        RetrofitUtils.getService().getContacts(UserInfo.uid)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ArrayList<RosterGroup>>() {
//                    @Override
//                    public void accept(ArrayList<RosterGroup> groups) throws Exception {
//                        mAdapter.setData(groups);
//                        ParentInfo.groupNa-mes.clear();
//                        for (RosterGroup group : groups) {
//                            ParentInfo.groupNames.add(group.getName());
//                        }
//                        writeToCache(groups);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        new AlertDialog.Builder(ContactActivity.this).setMessage("获取数据异常:" + throwable).setPositiveButton("重试", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                loadDataFromServer();
//                            }
//                        }).setNegativeButton("取消", null).show();
//                    }
//                });
//        RetrofitUtils.getService().getRequestCount(UserInfo.uid)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Result>() {
//                    @Override
//                    public void accept(Result result) throws Exception {
//                        tvNumber.setText(result.getCount() + "");
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(ContactActivity.this, "获取好友请求失败"+throwable, Toast.LENGTH_SHORT).show();
//                    }
//                });


        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContantServes contantServes1 = retrofit1.create(ContantServes.class);
        Call<ArrayList<RosterGroup>> call1 = contantServes1.getContacts(ParentInfo.uid);
        call1.enqueue(new Callback<ArrayList<RosterGroup>>() {
            @Override
            public void onResponse(Call<ArrayList<RosterGroup>> call, Response<ArrayList<RosterGroup>> response) {
                mAdapter.setData(response.body());
                ParentInfo.groupNames.clear();
                for(RosterGroup group:response.body()){
                    ParentInfo.groupNames.add(group.getName());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RosterGroup>> call, Throwable t) {
                new AlertDialog.Builder(LinkmanActivity.this).setMessage("获取数据异常:" + t).setPositiveButton("重试", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        loadDataFromServer();
                    }
                }).setNegativeButton("取消", null).show();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContantServes contantServes = retrofit.create(ContantServes.class);
        Call<Result> call = contantServes.getRequestCount(ParentInfo.uid);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                tvNumber.setText(response.body().getCount()+"");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private void initView() {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(LinkmanActivity.this, SearchActivity.class);
                intent.putExtra("query", query);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, Constant.REQURST_SEARCH);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mAdapter = new ExpandableContactAdapter(new ArrayList<RosterGroup>());
        aelvContact.setAdapter(mAdapter);
        aelvContact.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Roster roster = (Roster) mAdapter.getChild(groupPosition, childPosition);
                Intent intent = new Intent(Intent.ACTION_VIEW, ParentInfo.getPrivateChatUri(roster.getUid(), roster.getRemark()));
                startActivity(intent);
                return false;
            }
        });
    }

    public void loadData() {
        cachePath = getCacheDir().getAbsolutePath();
        cacheFile = new File(cachePath + File.separator + getClass().getSimpleName());
        if (cacheFile.exists()){

            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(cacheFile));
                String line ;
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                    data = gson.fromJson(builder.toString(), new TypeToken<ArrayList<RosterGroup>>(){}.getType());
                }
                mAdapter.setData(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        loadDataFromServer();
    }


    private void writeToCache(ArrayList<RosterGroup> groups) {
        data = groups;
        PrintWriter writer = null;
        try {
            writer =new PrintWriter( cacheFile) ;
            writer.print(gson.toJson(groups));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }

    public void setupUI(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LinkmanActivity.this);  //Main.this是我的activity名
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    @Override
    public void onBackPressed() {
//        updateData();
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
           //do nothing 不返回上一个activity
            new AlertDialog.Builder(this).setTitle("确认退出吗？")
                    .setIcon(R.drawable.ic_info_outline_red_500_24dp)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“确认”后的操作
                            //MainActivity.this.finish();
                            //MainActivity.super.onBackPressed();//结束当前activity
                            //System.exit(0);
                            MyApplication.getInstance().exit();
                        }
                    })
                    .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“返回”后的操作,这里不设置没有任何操作
                        }
                    }).show();
        }
//        super.onBackPressed();
    }
}
