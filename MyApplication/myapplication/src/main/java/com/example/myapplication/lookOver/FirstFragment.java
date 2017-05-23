package com.example.myapplication.lookOver;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Bean.Student;
import com.example.myapplication.LinkmanActivity.ContantServes;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ImageUtils;
import com.example.myapplication.Utils.ParentInfo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
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

import static com.example.myapplication.Utils.ParentInfo.current;


/**
 * Created by 小妖王 on 2017/2/13.
 */

public class FirstFragment extends Fragment implements View.OnClickListener {
    private Spinner changeStudent;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private Button title_left_btn, title_right_btn;
    private Toolbar toolbar;
    private List<Map<String, Object>> studentList = new ArrayList<>();
    private SimpleAdapter studentAdapter;
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Bitmap> images = new ArrayList<>();

    /**
     * Fragment管理器
     */
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    /**
     * 两个Fragment
     */
    private LeftFragment mLFragment;
    private QuestionFragment mRFragment;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        initChangeStudentData();
        changeStudent = (Spinner) view.findViewById(R.id.changeStudent);
        studentAdapter = new SimpleAdapter(getActivity(), studentList, R.layout.piconly,
                new String[]{"pic", "text"}, new int[]{R.id.imageview, R.id.textview});
        studentAdapter.setDropDownViewResource(R.layout.pic_text);
        changeStudent.setDropDownVerticalOffset(0);
        changeStudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("FirstFragment", "onItemSelected: " + position);
                current = position;
                if (mLFragment != null) {
                    mLFragment.dataPrepare((int) ParentInfo.lookOverIds.get(current));
                    mLFragment.getArrayAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        studentAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView iv = (ImageView) view;
                    iv.setImageBitmap((Bitmap) data);
                    return true;
                } else
                    return false;
            }
        });
        changeStudent.setAdapter(studentAdapter);
        toolbar = (Toolbar) view.findViewById(R.id.first_toolbar);
        initView(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.checkAsTime:
                if (title_left_btn.isEnabled()) {
                    title_left_btn.setEnabled(false);
                    title_right_btn.setEnabled(true);
                    title_right_btn.getBackground().setAlpha(100);
                    title_left_btn.getBackground().setAlpha(100);
                }
                mFragmentManager = getFragmentManager();
                mTransaction = mFragmentManager.beginTransaction();
                mLFragment = (LeftFragment) mFragmentManager.findFragmentByTag("LeftFragment");
                Fragment TimeDay = mFragmentManager.findFragmentByTag("TimeDay");
                if (TimeDay != null) {
                    mTransaction.show(TimeDay);
                }
                if (mLFragment == null) {
                    mLFragment = new LeftFragment();
                    mTransaction.add(R.id.first_fragment_content, mLFragment, "LeftFragment");
                } else {
                    mTransaction.show(mLFragment);
                }
                Fragment tempF = mFragmentManager.findFragmentByTag("RightFragment");
                if (tempF != null) {
                    mTransaction.hide(tempF);
                }

                mTransaction.commit();
                break;

            case R.id.checkAsCourse:
                if (title_right_btn.isEnabled()) {
                    title_left_btn.setEnabled(true);
                    title_right_btn.setEnabled(false);
                }
                mFragmentManager = getFragmentManager();
                mTransaction = mFragmentManager.beginTransaction();
                mRFragment = (QuestionFragment) mFragmentManager.findFragmentByTag("RightFragment");
                Fragment TimeDay1 = mFragmentManager.findFragmentByTag("TimeDay");
                if (TimeDay1 != null) {
                    mTransaction.hide(TimeDay1);
                }
                if (mRFragment == null) {
                    mRFragment = new QuestionFragment();
                    mTransaction.add(R.id.first_fragment_content, mRFragment, "RightFragment");
                } else {
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
        title_left_btn = (Button) view.findViewById(R.id.checkAsTime);
        title_right_btn = (Button) view.findViewById(R.id.checkAsCourse);

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


    private void processChangeStudentData(ArrayList<Student> students, ArrayList<Bitmap> images) {
        for (int i = 0; i < students.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", images.get(i));
            map.put("text", students.get(i).getNickname());
            studentList.add(map);
        }
    }

    private void initChangeStudentData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContantServes contantServes = retrofit.create(ContantServes.class);
        Call<ArrayList<Student>> call = contantServes.getStudentInfo(ParentInfo.id + "");
        call.enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                students = response.body();
                if (response.body().size() != 0) {
                    //Log.e("FirstFragment", "onResponse: the first student is "+response.body().get(0).getNickname());
                    for (Student student : students) {
                        ParentInfo.lookOverIds.add(student.getId());
                    }
                    new Thread(networkTask).start();
                } else {
                    Toast.makeText(getActivity(), "学生获取失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                Toast.makeText(getActivity(), "网络异常，请重试" + t, Toast.LENGTH_SHORT).show();
                Log.i("unpair student", t.toString());
            }
        });


        //// TODO: 2017/2/21

    }

    public Bitmap getBitmapFromServer(String imagePath) {

        HttpGet get = new HttpGet(getResources().getString(R.string.testBaseURL) + imagePath);
        HttpClient client = new DefaultHttpClient();
        Bitmap pic = null;
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            pic = BitmapFactory.decodeStream(is);   // 关键是这句代码

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pic;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                processChangeStudentData(students, images);
                studentAdapter.notifyDataSetChanged();
            }
        }
    };

    /**
     * 网络操作相关的子线程
     */
    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Message message = handler.obtainMessage();
            message.what = 1;
            for (int i = 0; i < students.size(); i++) {
                images.add(ImageUtils.toRoundBitmap(getBitmapFromServer(students.get(i).getImageURL())));
            }
            handler.sendMessage(message);
        }
    };
}
