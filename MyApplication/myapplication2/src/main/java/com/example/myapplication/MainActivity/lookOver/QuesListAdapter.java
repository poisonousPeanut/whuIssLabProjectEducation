package com.example.myapplication.MainActivity.lookOver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Bean.QuestionInfo;
import com.example.myapplication.LinkmanActivity.PersonalInfoActivity;
import com.example.myapplication.MainActivity.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * Created by 60440 on 2016/11/23.
 */


public class QuesListAdapter extends RecyclerView.Adapter<QuesListAdapter.NormalItemHolder> {

    private ArrayList<QuestionInfo> questions;
    private Context context;
    private MyItemClickListener myItemClickListener;
    private CardView cardView;
    private ImageView answerButton;
    private Activity activity;

    public QuesListAdapter(ArrayList<QuestionInfo> questionInfos,
                           Context context){
        questions=questionInfos;
        this.context=context;
    }
    public void getActivity(Activity activity){

        this.activity=activity;
    }

    @Override
    public NormalItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview01,parent,false);
        return new NormalItemHolder(view,myItemClickListener);
    }

    @Override
    public void onBindViewHolder(NormalItemHolder holder, final int position) {
        if(holder instanceof NormalItemHolder){
            NormalItemHolder normalItemHolder = (NormalItemHolder)holder;
            normalItemHolder.newsTitle.setText(questions.get(position).title);
            //add other content
//            normalItemHolder.questionTime.setText(questions.get(position).date);
            normalItemHolder.peopleAnswer.setText(questions.get(position).getAnswerNumber()+"人回答");
            new NormalImageLoader().getPicture(context.getResources().getString(R.string.testBaseURL)+questions.get(position).getUser().getImageURL(),normalItemHolder.userIamge);
            normalItemHolder.userName.setText(questions.get(position).getUser().getNickname()+"提出了这个问题");
            answerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(activity instanceof MainActivity){//这里的MainActivity可能有问题
                        Intent intent = new Intent(activity, AddAnswerActivity.class);
                        intent.putExtra("iid",Integer.parseInt(questions.get(position).getId()));
                        activity.startActivity(intent);
                    }
                }
            });
            normalItemHolder.userIamge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, PersonalInfoActivity.class);
                    intent.putExtra("uid","s"+questions.get(position).getUser().getId());
                    activity.startActivity(intent);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.myItemClickListener=listener;
    }

    class NormalItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView newsTitle;
        TextView peopleAnswer;
        TextView questionTime;
        ImageView userIamge;
        TextView userName;
        public NormalItemHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            newsTitle = (TextView)itemView.findViewById(R.id.tv_title);
            answerButton = (ImageView) itemView.findViewById(R.id.ib_answer);
//            classFrom = (TextView)itemView.findViewById(R.id.id_question_from);
            peopleAnswer = (TextView)itemView.findViewById(R.id.tv_discover_number);
//            questionTime=(TextView)itemView.findViewById(R.id.id_question_time);
            userIamge=(ImageView)itemView.findViewById(R.id.iv_image);
            userName=(TextView)itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            if(myItemClickListener!=null){

                myItemClickListener.onItemClick(v,getPosition());
            }

        }

    }
}
