package com.myshop.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.myshop.R;
import com.myshop.base.BaseAdapter;
import com.myshop.model.bean.HomeBean;

import java.util.List;

public class TopicAdapter extends BaseQuickAdapter<HomeBean.DataBean.TopicListBean, BaseViewHolder> {

    public TopicAdapter(int layoutResId, @Nullable List<HomeBean.DataBean.TopicListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.TopicListBean item) {
        Glide.with(mContext).load(item.getItem_pic_url()).into((ImageView) helper.getView(R.id.img_topic));
        helper.setText(R.id.txt_name,item.getTitle());
        helper.setText(R.id.txt_price,"￥"+item.getPrice_info());
        helper.setText(R.id.txt_des,item.getSubtitle());
    }
}
