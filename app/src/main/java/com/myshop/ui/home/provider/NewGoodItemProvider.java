package com.myshop.ui.home.provider;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.myshop.R;
import com.myshop.model.bean.HomeBean;
import com.myshop.ui.home.adapter.HomeAdapter;

import java.util.List;

public class NewGoodItemProvider extends BaseItemProvider<HomeBean.BaseData, BaseViewHolder> {

    @Override
    public int viewType() {
        return HomeAdapter.TYPE_NEWGOOD;
    }

    @Override
    public int layout() {
        return R.layout.layout_item_newgood;
    }

    @Override
    public void convert(BaseViewHolder helper, HomeBean.BaseData data, int position) {

        List<HomeBean.DataBean.NewGoodsListBean> list = (List<HomeBean.DataBean.NewGoodsListBean>) data;
        Glide.with(mContext).load(list.get(position).getList_pic_url()).into((ImageView) helper.getView(R.id.img_newGood));

        helper.setText(R.id.txt_name,list.get(position).getName());
        helper.setText(R.id.txt_price,"￥"+list.get(position).getRetail_price());

    }
}
