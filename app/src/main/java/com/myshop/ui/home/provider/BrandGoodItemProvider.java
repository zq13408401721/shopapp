package com.myshop.ui.home.provider;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.myshop.R;
import com.myshop.model.bean.HomeBean;
import com.myshop.ui.home.adapter.HomeAdapter;

import java.util.List;

public class BrandGoodItemProvider extends BaseItemProvider<HomeBean.BaseData,BaseViewHolder> {
    @Override
    public int viewType() {
        return HomeAdapter.TYPE_BRANDGOOD;
    }

    @Override
    public int layout() {
        return R.layout.layout_item_brand;
    }

    @Override
    public void convert(BaseViewHolder helper, HomeBean.BaseData data, int position) {

        List<HomeBean.DataBean.BrandListBean> list = (List<HomeBean.DataBean.BrandListBean>) data;

        Glide.with(mContext).load(list.get(position).getNew_pic_url()).into((ImageView) helper.getView(R.id.img_brand));

        helper.setText(R.id.txt_name,list.get(position).getName());
        helper.setText(R.id.txt_price,"￥"+list.get(position).getFloor_price()+"元起");
    }
}
