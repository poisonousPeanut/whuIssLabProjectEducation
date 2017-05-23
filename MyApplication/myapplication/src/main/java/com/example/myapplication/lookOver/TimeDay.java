package com.example.myapplication.lookOver;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.myapplication.Bean.Answer;
import com.example.myapplication.Bean.Issue;
import com.example.myapplication.R;
import com.example.myapplication.Utils.MyUtils;

import static android.content.ContentValues.TAG;

/**
 * Created by 小妖王 on 2017/2/23.
 */

public class TimeDay extends Fragment implements ExpandableListView.OnChildClickListener{
    String[] res = {"今日","2017年2月22日","2017年2月21日","2017年2月20日","2017年2月19日","2017年2月18日","2017年2月17日","2017年2月16日"};
    private int dateSymbol;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.time_day, container, false);

//        timeCourse= (Spinner) view.findViewById(R.id.spinner2);
//        timeQuestion= (Spinner) view.findViewById(R.id.spinner3);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,res);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        timeCourse.setAdapter(arrayAdapter);
//        timeQuestion.setAdapter(arrayAdapter);
//
        dateSymbol= getArguments().getInt("dateSymbol");
        Log.e(TAG, "onCreateView: dateSymbol"+dateSymbol);

        DemoDockingAdapterDataSource listData = prepareData(dateSymbol);
        DockingExpandableListView listView
                = (DockingExpandableListView) view.findViewById(R.id.docking_list_view);
        listView.setGroupIndicator(null);
        listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        listView.setAdapter(new DockingExpandableListViewAdapter(getActivity(), listView, listData));
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (parent.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
//                    Log.e("TimeDay", "onGroupClick:点击了！！！");
                } else {
                    parent.expandGroup(groupPosition);
                }

                return true;
            }
        });
        listView.setOnChildClickListener(this);
        //View headerView = getLayoutInflater().inflate(R.layout.group_view_item, listView, false);
        View headerView = getActivity().getLayoutInflater().inflate(R.layout.group_view_item, listView, false);
        listView.setDockingHeader(headerView, new IDockingHeaderUpdateListener() {
            @Override
            public void onUpdate(View headerView, int groupPosition, boolean expanded) {
//                String groupTitle = "Group #" + String.valueOf(groupPosition + 1);
                String groupTitle="";
                switch (groupPosition){
                    case 0:
                        groupTitle="课程学习情况";
                        break;
                    case 1:
                        groupTitle="提出的问题";
                        break;
                    case 2:
                        groupTitle="回答的问题";
                        break;
                    case 3:
                        groupTitle="与别人的交流";
                        break;
                }
                TextView titleView = (TextView) headerView.findViewById(R.id.group_view_title);
                titleView.setText(groupTitle);
            }
        });

        return view;

    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String text = timeListView.getItemAtPosition(position)+"";
//    }

    private DemoDockingAdapterDataSource prepareData(int dateSymbol) {
        DemoDockingAdapterDataSource listData
                = new DemoDockingAdapterDataSource(getActivity());

//        Log.e("TimeDay", "prepareData: 1"+dateSymbol);
        listData.addGroup("课程学习情况");
        if(TimeStudyData.getDaysCourseLearning().get(dateSymbol).size()!=0) {
            for(CourseLearning courseLearning:TimeStudyData.getDaysCourseLearning().get(dateSymbol)){
            listData.addChild("学习 "+courseLearning.getCourse().getName()+ MyUtils.transTime(courseLearning.getDuration()));
            }
        }
        else{
            listData.addChild("没有课程学习情况");
        }
//        Log.e("TimeDay", "prepareData: 2"+dateSymbol);


        listData.addGroup("提出的问题");
        if(TimeStudyData.getDaysIssue().get(dateSymbol).size()!=0) {
            for(Issue issue:TimeStudyData.getDaysIssue().get(dateSymbol)){
                listData.addChild(issue.getTitle());}
        }else{
            listData.addChild("没有提出问题");
        }

        listData.addGroup("回答的问题");
        if(TimeStudyData.getDaysAnswer().get(dateSymbol).size()!=0) {
            for(Answer answer:TimeStudyData.getDaysAnswer().get(dateSymbol)){
                listData.addChild(answer.getIssue().getTitle());}
        }else{
            listData.addChild("没有回答问题");
        }

        listData.addGroup("与别人的交流");
        if(TimeStudyData.getDaysMessage().get(dateSymbol).size()!=0) {
            for(MessageRecord messageRecord:TimeStudyData.getDaysMessage().get(dateSymbol)){
                listData.addChild("与"+messageRecord.getToUid()+"交流"+messageRecord.getNumber()+"条");}
        }else{
            listData.addChild("没有交流情况");
        }

        return listData;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        if(groupPosition==0&&childPosition==0){
//            Log.i(TAG, "onChildClick: I am right!");
        }
        if(groupPosition==0&&childPosition==1){
//            Log.i(TAG,"onChildClick: this is the second one");
        }
        return false;
    }
}
