package com.myshop.ui.home.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.myshop.R;
import com.myshop.model.bean.HomeBean;

import java.util.List;

public class HotGoodAdapter extends BaseQuickAdapter<HomeBean.DataBean.HotGoodsListBean, BaseViewHolder> {
    public HotGoodAdapter(int layoutResId, @Nullable List<HomeBean.DataBean.HotGoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.HotGoodsListBean item) {
        Glide.with(mContext).load(item.getList_pic_url()).into((ImageView) helper.getView(R.id.img_hot));
        helper.setText(R.id.txt_name,item.getName());
        helper.setText(R.id.txt_des,item.getGoods_brief());
        helper.setText(R.id.txt_price,"￥"+item.getRetail_price());
    }
}
