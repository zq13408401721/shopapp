package com.myshop.ui.home.provider;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.myshop.R;
import com.myshop.model.bean.HomeBean;
import com.myshop.ui.home.adapter.HomeAdapter;

public class BrandGoodItemProvider extends BaseItemProvider<HomeBean.DataBean.BrandListBean,BaseViewHolder> {
    @Override
    public int viewType() {
        return HomeAdapter.TYPE_BRANDGOOD;
    }

    @Override
    public int layout() {
        return R.layout.layout_item_brand;
    }

    @Override
    public void convert(BaseViewHolder helper, HomeBean.DataBean.BrandListBean data, int position) {
        Glide.with(mContext).load(data.getNew_pic_url()).into((ImageView) helper.getView(R.id.img_brand));

        helper.setText(R.id.txt_name,data.getName());
        helper.setText(R.id.txt_price,"￥"+data.getFloor_price()+"元起");
    }
}
