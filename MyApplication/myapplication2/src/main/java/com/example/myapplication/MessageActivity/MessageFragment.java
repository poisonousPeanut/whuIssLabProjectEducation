package com.example.myapplication.MessageActivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class MessageFragment extends Fragment {
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
    private ConversationListFragment conversationListFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_message, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitle=(TextView)view.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("消息");
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_friend:
                        //加上加好友的逻辑
                        break;
                    case R.id.scace:
                        //加上扫一扫
                        break;
                }
                return true;
            }
        });
        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        conversationListFragment = new ConversationListFragment();
        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话，该会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//设置群组会话，该会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置群组会话，该会话非聚合显示
                .build();
        conversationListFragment.setUri(uri);
//        mTransaction.replace(R.id.bottomTag2, bottomTag);
        mTransaction.replace(R.id.ConversationList,conversationListFragment);
        mTransaction.commit();
        return view;
    }

}
