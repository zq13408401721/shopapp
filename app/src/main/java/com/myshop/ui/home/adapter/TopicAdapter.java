package com.myshop.ui.home.adapter;

import android.content.Context;

import com.myshop.base.BaseAdapter;

import java.util.List;

public class TopicAdapter extends BaseAdapter {
    public TopicAdapter(Context context, List mDatas) {
        super(context, mDatas);
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object data) {

    }
}
