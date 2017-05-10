package com.example.myapplication.lookOver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Utils.ParentInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 60440 on 2016/12/8.
 */

public class AddAnswerActivity extends Activity {
//    @OnClick(R.id.id_BackButton)
//    void setBackButton(){
//        finish();
//    }
    private int iid;
    @OnClick(R.id.id_question_submit)
    void setSubmit(){
        submitAnswer(iid, ParentInfo.uid);
        finish();
    }

    @Bind(R.id.editText_introduction)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quesbar01);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        iid = intent.getIntExtra("iid",0);


    }

   private void submitAnswer(final int iid,final String uid){
       // TODO: 2017/2/13
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(getResources().getString(R.string.testBaseURL))
               .addConverterFactory(ScalarsConverterFactory.create())
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       AddAnswer addAnswer = retrofit.create(AddAnswer.class);
       Call<String> call = addAnswer.submitAnswer(editText.getText().toString(),false,iid,uid);
       call.enqueue(new Callback<String>() {
           @Override
           public void onResponse(Call<String> call, Response<String> response) {
               Log.i("add question"," succeed");
               Toast.makeText(AddAnswerActivity.this, "添加问题成功", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
           }
       });

   }


}
