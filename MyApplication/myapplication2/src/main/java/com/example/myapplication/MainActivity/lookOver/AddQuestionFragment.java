package com.example.myapplication.MainActivity.lookOver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;

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
 * Created by 60440 on 2016/11/27.
 */

public class AddQuestionFragment extends Fragment {
//    private EditText questionEdit;
    private Button submitButton;
    @Bind(R.id.id_EditText_AddQuestion)
    EditText questionEdit;
    @OnClick(R.id.id_Button_submit_question)
    void setSubmitButton(){
        submit();
    }
    @Bind(R.id.id_add_question_content)
    EditText contentEditView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addquestionfragment,container,false);
        ButterKnife.bind(this,v);
        return v;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        questionEdit.setText("");
        contentEditView.setText("");
        super.onHiddenChanged(hidden);
    }

    private void submit() {
        Retrofit retorfit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.testBaseURL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AddQuestionRequestServes addQuestionRequestServes = retorfit.create(AddQuestionRequestServes.class);
        Call<String> call = addQuestionRequestServes.submitQuestion(questionEdit.getText().toString(),contentEditView.getText().toString(),false,UserUtils.getParam(getContext()).getId());
        Log.i("Addquestion",questionEdit.getText().toString()+contentEditView.getText().toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("addquestion","succeed");
                if(getActivity() instanceof MainActivity){
                    ((MainActivity) getActivity()).setSelect(0);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("addquestion","fail");

            }
        });

    }


}
