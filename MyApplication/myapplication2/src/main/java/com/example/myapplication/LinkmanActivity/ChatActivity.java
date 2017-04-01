package com.example.myapplication.LinkmanActivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.myapplication.R;

/**
 * @author way
 */
public class ChatActivity extends AppCompatActivity {
    @Bind(R.id.tv_name)
    TextView tvName;
    private String mTargetId;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        init();

    }

    public void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Uri uri = getIntent().getData();
        mTargetId = uri.getQueryParameter("targetId").toString();
        String title = uri.getQueryParameter("title").toString();
        if (title!=null)
        tvName.setText(title);
    }


    //    private void init() {
//        SmackManager.getConnection().addSyncStanzaListener(new StanzaListener() {
//            @Override
//            public void processPacket(Stanza packet) throws SmackException.NotConnectedException, InterruptedException {
//                Message message = (Message) packet;
//                final String time = getDate();
//                final String content = message.getBody();
//                final boolean isCom = true;
//                Log.e("message", "processMessage: " + content);
//                UiUtils.runInMainThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAdapter.add(new ChatInformation(nickname, time, content, isCom));
//                    }
//                });
//            }
//        }, new StanzaTypeFilter(Message.class));
//    }

    /**
     * 初始化view
     */

}