package com.example.myapplication.LinkmanActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

import butterknife.ButterKnife;
import com.example.myapplication.R;

/**
 * Created by fate on 2017/3/5.
 */

public class RecyclerGroupsAdapter extends BaseRecyclerViewAdapter<ChatGroup,RecyclerGroupsAdapter.GroupsViewHolder> {

    public RecyclerGroupsAdapter(ArrayList<ChatGroup> data) {
        super(data);
    }

    @Override
    public GroupsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UiUtils.getContext()).inflate(R.layout.item_recycler_group, parent, false);
        return new GroupsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupsViewHolder holder, int position) {
        ChatGroup group = data.get(position);
        holder.itemView.setTag(group);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null)
                    mListener.onItemClick(v, (ChatGroup) v.getTag());
            }
        });
        holder.tvGroupName.setText(group.getName());
        Glide.with(UiUtils.getContext()).load(Constant.SERVER_URL+group.getImageURL())
                .placeholder(R.drawable.ic_supervisor_account_blue_600_24dp)
                .transform(new GlideRoundTransform(UiUtils.getContext(),Constant.IMAGE_RADIUS))
                .into(holder.ivGroupImage);
    }

    class GroupsViewHolder extends RecyclerView.ViewHolder{
        TextView tvGroupName;
        ImageView ivGroupImage;
        public GroupsViewHolder(View itemView) {
            super(itemView);
            tvGroupName = ButterKnife.findById(itemView, R.id.tv_group_name);
            ivGroupImage = ButterKnife.findById(itemView, R.id.iv_group_image);
        }
    }
}
