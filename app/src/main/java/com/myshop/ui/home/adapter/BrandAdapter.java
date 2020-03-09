package com.myshop.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myshop.R;
import com.myshop.base.BaseAdapter;
import com.myshop.model.bean.HomeBean;

import java.util.List;

public class BrandAdapter extends BaseAdapter {
    public BrandAdapter(Context context, List mDatas) {
        super(context, mDatas);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_brand_item;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object data) {
        HomeBean.DataBean.BrandListBean bean = (HomeBean.DataBean.BrandListBean) data;
        ImageView imgBrand = (ImageView) holder.getViewById(R.id.img_brand);
        TextView txtTitle = (TextView) holder.getViewById(R.id.txt_title);
        TextView txtPrice = (TextView) holder.getViewById(R.id.txt_price);

        Glide.with(mContext).load(bean.getNew_pic_url()).into(imgBrand);
        txtTitle.setText(bean.getName());
        txtPrice.setText(bean.getFloor_price()+"元起");

        if(onClickListener != null){
            txtTitle.setTag(bean);
            txtTitle.setOnClickListener(onClickListener);
            txtPrice.setTag(bean);
            txtPrice.setOnClickListener(onClickListener);
        }

    }
}
