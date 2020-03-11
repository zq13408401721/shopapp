package com.myshop.ui.home.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.myshop.model.bean.HomeBean;
import com.myshop.ui.home.provider.BannderItemProvider;
import com.myshop.ui.home.provider.BrandGoodItemProvider;

public class HomeAdapter extends MultipleItemRvAdapter<HomeBean.BaseData, BaseViewHolder> {

    public static final int TYPE_BANNER = 100;
    public static final int TYPE_BRANDGOOD = 200;
    public static final int TYPE_NEWGOOD = 300;

    public HomeAdapter(){
        super(null);
        finishInitialize();
    }

    @Override
    protected int getViewType(HomeBean.BaseData bean) {
        int type = bean.getType();
        if(type == 1){
            return TYPE_BANNER;
        }else if(type == 2){
            return TYPE_BRANDGOOD;
        }
        return 0;
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(new BannderItemProvider());
        mProviderDelegate.registerProvider(new BrandGoodItemProvider());
    }
}
