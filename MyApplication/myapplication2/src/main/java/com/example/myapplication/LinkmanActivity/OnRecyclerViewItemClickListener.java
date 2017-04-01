package com.example.myapplication.LinkmanActivity;

import android.view.View;

/**
 * Created by fate on 2016/10/30.
 */

public interface OnRecyclerViewItemClickListener<E> {
    void onItemClick(View v, E data);
}
