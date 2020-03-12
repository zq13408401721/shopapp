package com.myshop.ui.home.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.myshop.R;
import com.myshop.model.bean.HomeBean;

import java.util.List;

public class BrandAdpater extends BaseQuickAdapter<HomeBean.DataBean.BrandListBean, BaseViewHolder> {


    public BrandAdpater(int layoutResId, @Nullable List<HomeBean.DataBean.BrandListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.BrandListBean item) {
        Glide.with(mContext).load(item.getList_pic_url()).into((ImageView) helper.getView(R.id.img_brand));
        helper.setText(R.id.txt_name,item.getName());
        helper.setText(R.id.txt_price,"￥"+item.getFloor_price());
    }
}
