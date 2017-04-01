package com.example.myapplication.MainActivity.lookOver;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.myapplication.R;

import static android.content.ContentValues.TAG;

public class RightFragment extends Fragment implements ExpandableListView.OnChildClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

       View view =inflater.inflate(R.layout.right_fragment, container, false);

        // return super.onCreateView(inflater, container, savedInstanceState);
        DemoDockingAdapterDataSource2 listData = prepareData();
        DockingExpandableListView listView
                = (DockingExpandableListView) view.findViewById(R.id.docking_list_view2);
        listView.setGroupIndicator(null);
        listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        listView.setAdapter(new DockingExpandableListViewAdapter(getActivity(), listView, listData));
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (parent.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
                } else {
                    parent.expandGroup(groupPosition);
                }

                return true;
            }
        });
        listView.setOnChildClickListener(this);

        //View headerView = getLayoutInflater().inflate(R.layout.group_view_item, listView, false);
        View headerView = getActivity().getLayoutInflater().inflate(R.layout.group_view_item2, listView, false);
        listView.setDockingHeader(headerView, new IDockingHeaderUpdateListener() {
            @Override
            public void onUpdate(View headerView, int groupPosition, boolean expanded) {
//                String groupTitle = "Group #" + String.valueOf(groupPosition + 1);
                String groupTitle="";
                switch (groupPosition){
                    case 0:
                        groupTitle="正在进行的课程";
                        break;
                    case 1:
                        groupTitle="已经完成的课程";
                        break;
                }
                TextView titleView = (TextView) headerView.findViewById(R.id.group_view_title2);
                titleView.setText(groupTitle);
            }
        });
        return view;
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        if(groupPosition==0&&childPosition==0){
            Log.i(TAG, "onChildClick: I am right!");
        }
        if(groupPosition==0&&childPosition==1){
            Log.i(TAG,"onChildClick: this is the second one");
        }
        return false;
    }

    private DemoDockingAdapterDataSource2 prepareData() {
        DemoDockingAdapterDataSource2 listData
                = new DemoDockingAdapterDataSource2(getActivity());
        listData.addGroup("正在进行的课程")
                .addChild("Dish #1")
                .addChild("Dish #2")
                .addChild("Dish #3")
                .addChild("Dish #4")
                .addChild("Dish #4")
                .addChild("Dish #4")
                .addChild("Dish #4")
                .addChild("Dish #4")
                .addChild("Dish #4")
                .addChild("Dish #4")
                .addChild("Dish #4")
                .addChild("Dish #4")
                .addChild("Dish #4")
                .addGroup("已经完成的课程")
                .addChild("Drink #1")
                .addChild("Drink #2")
                .addChild("Drink #3")
                .addChild("Drink #4")
                .addChild("Drink #5")
                .addChild("Drink #6")
                .addChild("Drink #7")
                .addChild("Drink #8")
                .addChild("Drink #9");


        return listData;
    }
}
