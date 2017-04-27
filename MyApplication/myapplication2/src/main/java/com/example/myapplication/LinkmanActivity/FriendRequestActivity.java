package com.example.myapplication.LinkmanActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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


public class FriendRequestActivity extends AppCompatActivity {

    @Bind(R.id.re_friend_request)
    RecyclerView reFriendRequest;
    @Bind(R.id.toolbarTitle)
    TextView toolbarTitle;
    private RecyclerFriendRequestAdapter mAdapter;
    private ArrayAdapter<String> mArrayAdapter;
    private Spinner mSpinner;
    private EditText mEtRemark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitle.setText("好友请求");
        setSupportActionBar(toolbar);
        initData();
        initView();
    }

    private void initData() {
//        RetrofitUtils.getService().getFriendRequest(UserInfo.uid).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ArrayList<FriendRequest>>() {
//                    @Override
//                    public void accept(ArrayList<FriendRequest> requests) throws Exception {
//                        mAdapter.setData(requests);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContantServes contantServes = retrofit.create(ContantServes.class);
        Call<ArrayList<FriendRequest>> call = contantServes.getFriendRequest(ParentInfo.uid);
        call.enqueue(new Callback<ArrayList<FriendRequest>>() {
            @Override
            public void onResponse(Call<ArrayList<FriendRequest>> call, Response<ArrayList<FriendRequest>> response) {
                if(response.body()!=null){
                    mAdapter.setData(response.body());
                }else{
                    Toast.makeText(FriendRequestActivity.this, Constant.INTERNET_FAIL, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FriendRequest>> call, Throwable t) {
                Toast.makeText(FriendRequestActivity.this, Constant.INTERNET_FAIL+t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        reFriendRequest.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerFriendRequestAdapter(new ArrayList<FriendRequest>());
        reFriendRequest.setAdapter(mAdapter);
        final View dialogView = initDialogView();
        mAdapter.setOnAddFriendListener(new RecyclerFriendRequestAdapter.OnAddFriendListener() {
            @Override
            public void onAddFriend(final FriendRequest info, final RecyclerFriendRequestAdapter.MyViewHolder holder) {
                mEtRemark.setText(info.getNickname());
                new AlertDialog.Builder(FriendRequestActivity.this).setView(dialogView)
                        .setTitle("添加好友")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                holder.showAdding();
                                addFriend(info,holder);
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
    }
    private View initDialogView() {
        View view = View.inflate(this, R.layout.dialog_accept_friend, null);
        mEtRemark = ButterKnife.findById(view, R.id.et_remark);
        final EditText etGroup = ButterKnife.findById(view,R.id.et_add_group);
        mSpinner = ButterKnife.findById(view, R.id.spinner_roster);
        ImageView ivAdd = ButterKnife.findById(view,R.id.iv_add_group);
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ParentInfo.groupNames);
        mSpinner.setAdapter(mArrayAdapter);
        if (ParentInfo.groupNames.size() == 0){
            getGroupNames();
        }
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etGroup.getText().toString();
                if (!TextUtils.isEmpty(str)){
                    mArrayAdapter.add(str);
                    mSpinner.setSelection(mArrayAdapter.getCount()-1);
                }else {
                    Toast.makeText(FriendRequestActivity.this, "请输入组名", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void getGroupNames() {
//        RetrofitUtils.getService().getGroupNames(UserInfo.uid)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ArrayList<String>>() {
//                    @Override
//                    public void accept(ArrayList<String> strings) throws Exception {
//                        UserInfo.groupNames = strings;
//                        mArrayAdapter.addAll(strings);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(FriendRequestActivity.this, "获取组信息失败"+throwable, Toast.LENGTH_SHORT).show();
//                    }
//                });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContantServes contantServes = retrofit.create(ContantServes.class);
        Call<ArrayList<String>> call = contantServes.getGroupNames(ParentInfo.uid);
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                ParentInfo.groupNames = response.body();
                mArrayAdapter.addAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Toast.makeText(FriendRequestActivity.this, "获取组信息失败"+t, Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void addFriend(FriendRequest info, final RecyclerFriendRequestAdapter.MyViewHolder holder) {
        String group = mSpinner.getSelectedItem().toString();
        String s = mEtRemark.getText().toString();
        if (TextUtils.isEmpty(s.trim()))
            s = info.getNickname();
//        RetrofitUtils.getService().addFriend(info.getId(),group,s)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Result>() {
//                    @Override
//                    public void accept(Result result) throws Exception {
//                        if (result.getCode() == 0) {
//                            holder.showAdded();
//                            setResult(RESULT_OK);
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        new AlertDialog.Builder(FriendRequestActivity.this)
//                                .setMessage("添加失败"+throwable)
//                                .setPositiveButton("确定",null)
//                                .show();
//                        holder.showAddFriend();
//                    }
//                });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContantServes contantServes = retrofit.create(ContantServes.class);
        Call<Result> call =  contantServes.addFriend(info.getId(),group,s);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.body().getCode()==0){
                    holder.showAdded();;
                    setResult(RESULT_OK);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                new AlertDialog.Builder(FriendRequestActivity.this)
                        .setMessage("添加失败"+t)
                        .setPositiveButton("确定",null)
                        .show();
                holder.showAddFriend();
            }
        });
    }
}
