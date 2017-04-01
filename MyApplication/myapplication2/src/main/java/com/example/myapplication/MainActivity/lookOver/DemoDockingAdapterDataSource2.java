package com.example.myapplication.MainActivity.lookOver;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by qianxin on 2016/11/21.
 */
public class DemoDockingAdapterDataSource2 implements IDockingAdapterDataSource {
    private Context mContext;

    private HashMap<Integer, String> mGroups = new HashMap<>();
    private SparseArray<List<String>> mGroupData = new SparseArray<>();
    private int mCurrentGroup = -1;

    public DemoDockingAdapterDataSource2(Context context) {
        mContext = context;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (groupPosition < 0 || !mGroups.containsKey(groupPosition)) {
            return null;
        }

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(com.example.myapplication.R.layout.group_view_item2, parent, false);
        }
        TextView titleView = (TextView)convertView.findViewById(R.id.group_view_title2);
        titleView.setText(mGroups.get(groupPosition));
        return convertView;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        List<String> children = mGroupData.get(groupPosition);
        if (children == null || childPosition < 0 || childPosition > children.size()) {
            return null;
        }

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.child_view_item2, parent, false);
        }
        TextView titleView = (TextView)convertView.findViewById(R.id.child_view_title2
        );
        titleView.setText(children.get(childPosition));
        return convertView;
    }

    public int getGroupCount() {
        return mGroups.size();
    }

    public int getChildCount(int groupPosition) {
        if (mGroupData.get(groupPosition) != null) {
            return mGroupData.get(groupPosition).size();
        }

        return 0;
    }

    public List<String> getGroup(int groupPosition) {
        if (mGroupData.get(groupPosition) != null) {
            return mGroupData.get(groupPosition);
        }

        return null;
    }

    public String getChild(int groupPosition, int childPosition) {
        if (mGroupData.get(groupPosition) != null) {
            List<String> group = mGroupData.get(groupPosition);
            if (childPosition >=0 && childPosition < group.size()) {
                return group.get(childPosition);
            }
        }

        return null;
    }

    // Helper method to add group
    public DemoDockingAdapterDataSource2 addGroup(String group) {
        if (!mGroups.containsValue(group)) {
            mCurrentGroup++;
            mGroups.put(mCurrentGroup, group);
            mGroupData.put(mCurrentGroup, new ArrayList<String>());
        }

        return this;
    }

    // Helper method to add child into one group
    public DemoDockingAdapterDataSource2 addChild(String child) {
        if (mGroupData.get(mCurrentGroup) != null) {
            mGroupData.get(mCurrentGroup).add(child);
        }

        return this;
    }
}
