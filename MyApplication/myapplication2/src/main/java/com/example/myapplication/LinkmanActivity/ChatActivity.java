package com.example.myapplication.LinkmanActivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.myapplication.LogInActivity.LogInActivity;
import com.example.myapplication.R;

import static com.example.myapplication.Utils.MyUtils.hideSoftKeyboard;

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
        setupUI(findViewById(R.id.activity_chat));
    }

    public void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Uri uri = getIntent().getData();
        mTargetId = uri.getQueryParameter("targetId").toString();
        String title = uri.getQueryParameter("title").toString();
        if (title!=null)
        tvName.setText(title);
    }

    public void setupUI(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(ChatActivity.this);  //Main.this是我的activity名
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
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